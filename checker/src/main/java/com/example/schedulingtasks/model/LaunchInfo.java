package com.example.schedulingtasks.model;

import java.sql.Timestamp;

public class LaunchInfo {
	private int id;
	private Timestamp timestamp;
	private String omniform_id;
	public LaunchInfo() {
		
	}
	public LaunchInfo(int id, Timestamp timestamp, String omniform_id) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.omniform_id = omniform_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getOmniform_id() {
		return omniform_id;
	}
	public void setOmniform_id(String omniform_id) {
		this.omniform_id = omniform_id;
	}
	
}
