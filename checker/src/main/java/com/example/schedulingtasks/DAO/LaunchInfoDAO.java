package com.example.schedulingtasks.DAO;
import java.sql.Timestamp;
import java.util.List;

import com.example.schedulingtasks.model.*;

public interface LaunchInfoDAO {
	public List<LaunchInfo> launchInfo(Timestamp timestamp);
}
