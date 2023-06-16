package com.boardapp.boardapi.common.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// Enabled Transaction
@EnableTransactionManagement
public class JdbcConfig {
    private final String DATABASE_DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
    private final String DATABASE_URL = "jdbc:mariadb://localhost:3306/board";
    private final String DATABASE_USER_NAME = "root";
    private final String DATABASE_USER_PASSWORD = "123456";

    @Bean
    DataSource mariadbDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DATABASE_DRIVER_CLASS_NAME);
        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(DATABASE_USER_NAME);
        dataSource.setPassword(DATABASE_USER_PASSWORD);

        return dataSource;
    }
}
