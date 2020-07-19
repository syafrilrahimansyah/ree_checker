/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.schedulingtasks;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.schedulingtasks.DAO.LaunchInfoDAO;
import com.example.schedulingtasks.DAO.TimeRecordDAO;
import com.example.schedulingtasks.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class ScheduledTasks {
	@Autowired
	@Qualifier("sql1")
	private LaunchInfoDAO launchInfoDAO;
	@Autowired
	@Qualifier("sql2")
	private TimeRecordDAO timeRecordDAO;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("Checking New Launch");
		Timestamp timeStamp = timeRecordDAO.timestamp();
		List<LaunchInfo> launchInfo = launchInfoDAO.launchInfo(timeStamp);
		if(launchInfo.isEmpty()) {
			log.info("Launch Not Found.");
		}else {
			log.info("New Launch Found.");
			List<OmniformID> listOmniformID = new ArrayList<>();
			for(LaunchInfo launch : launchInfo) {
				OmniformID omniformID = new OmniformID();
				omniformID.setId(launch.getOmniform_id());
				listOmniformID.add(omniformID);
				timeRecordDAO.updateTimestamp(launch.getTimestamp());
			}
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForObject("http://localhost:8080/collector/collect", listOmniformID, OmniformID.class);
			
		}
	}
}
