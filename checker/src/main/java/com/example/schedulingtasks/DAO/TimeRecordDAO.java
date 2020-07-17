package com.example.schedulingtasks.DAO;

import java.sql.Timestamp;

public interface TimeRecordDAO {
	public Timestamp timestamp();
	public void updateTimestamp(Timestamp timestamp);
}
