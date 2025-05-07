package com.rvss.schoolregister.dataservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration

public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private  String jdbcUrl;

    @Value("${spring.datasource.username}")
    private  String jdbcUsername;

    @Value("${spring.datasource.password}")
    private  String jdbcPassword;
    @Value("${spring.datasource.driver-class-name}")
    private   String jdbcDriver;
    // Other configuration beans or methods can be added here

    // Getter methods for JDBC properties
    public    String getJdbcUrl() {
        System.out.println(jdbcUrl);
        return jdbcUrl;
    }

    public   String getJdbcUsername() {
        System.out.println(jdbcUsername);
        return jdbcUsername;
    }

    public   String getJdbcPassword() {
        System.out.println(jdbcPassword);
        return jdbcPassword;
    }
    public  String getJdbcDriver() {
        System.out.println(jdbcDriver);
        return jdbcDriver; }
}
