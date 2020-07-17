package com.example.schedulingtasks.DAO;

import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class TimeRecordDAOimpl implements TimeRecordDAO{
	JdbcTemplate jdbcTemplate;
	
	public TimeRecordDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Timestamp timestamp() {
		String sql = "SELECT timestamp FROM timerecord WHERE id = 1";
		Timestamp result = jdbcTemplate.queryForObject(sql, Timestamp.class);
		return result;
	}

	@Override
	public void updateTimestamp(Timestamp timestamp) {
		// TODO Auto-generated method stub
		String sql = "UPDATE timerecord SET timestamp = '"+timestamp+"' WHERE id = 1";
		jdbcTemplate.update(sql);
	}

}
