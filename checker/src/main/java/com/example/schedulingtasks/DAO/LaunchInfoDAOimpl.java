package com.example.schedulingtasks.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.schedulingtasks.model.LaunchInfo;

public class LaunchInfoDAOimpl implements LaunchInfoDAO {
	private JdbcTemplate jdbcTemplate;
	 
	public LaunchInfoDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<LaunchInfo> launchInfo(Timestamp timestamp) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM launch_info WHERE timestamp > '"+timestamp+"'";
		List<LaunchInfo> listLaunchInfo = jdbcTemplate.query(sql, new RowMapper<LaunchInfo>() {
			 
	        @Override
	        public LaunchInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	            LaunchInfo launchInfo = new LaunchInfo();
	            launchInfo.setId(rs.getInt("id"));
	            launchInfo.setTimestamp(rs.getTimestamp("timestamp"));
	            launchInfo.setOmniform_id(rs.getString("omniform_id"));
	            return launchInfo;
	        }
	 
	    });
		return listLaunchInfo;
	}
	
}
