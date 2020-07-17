package com.example.schedulingtasks.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.example.schedulingtasks.DAO.*;

@Configuration
@ComponentScan(basePackages="com.example.schedulingtasks")
public class AppConfig {
	@Bean()
    public DataSource getDataSource1() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/C2C?serverTimezone=UTC&useLegacyDatetimeCode=false");
        dataSource.setUsername("pmauser");
        dataSource.setPassword("alvin147");
         
        return dataSource;
    }
	@Bean()
    public DataSource getDataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/extractor?serverTimezone=UTC&useLegacyDatetimeCode=false");
        dataSource.setUsername("pmauser");
        dataSource.setPassword("alvin147");
         
        return dataSource;
    }
     
    @Bean(name="sql1")
    public LaunchInfoDAO getLaunchInfoDAO() {
        return new LaunchInfoDAOimpl(getDataSource1());
    }
    @Bean(name="sql2")
    public TimeRecordDAO getTimeRecordDAO() {
    	return new TimeRecordDAOimpl(getDataSource2());
    }
}
