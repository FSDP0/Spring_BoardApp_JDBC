package com.boardapp.boardapi.common.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
// Enabled Transaction
@EnableTransactionManagement
public class JdbcConfig {
    @Value("${db.driver-class-name}")
    private String DATABASE_DRIVER_CLASS_NAME;

    @Value("${db.url}")
    private String DATABASE_URL;

    @Value("${db.username}")
    private String DATABASE_USER_NAME;

    @Value("${db.password}")
    private String DATABASE_USER_PASSWORD;

    @Bean
    DataSource mariadbDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Basic setting for Spring JDBC connection pool
        dataSource.setDriverClassName(DATABASE_DRIVER_CLASS_NAME); // DataSource Driver Class Name
        dataSource.setUrl(DATABASE_URL); // DataSource Database URL
        dataSource.setUsername(DATABASE_USER_NAME); // DataSource Database User Name
        dataSource.setPassword(DATABASE_USER_PASSWORD); // DataSource Database User Password

        return dataSource;
    }
}


