package com.boardapp.boardapi.common.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
// Enabled Transaction
@EnableTransactionManagement
public class JdbcConfig extends JdbcDataSource {
    @Bean
    DataSource mariadbDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Basic setting for Spring JDBC connection pool
        dataSource.setDriverClassName(DATABASE_DRIVER_CLASS_NAME); // DataSource Driver Class Name
        dataSource.setUrl(DATABASE_URL); // DataSource Database URL
        dataSource.setUsername(DATABASE_USER_NAME); // DataSource Database User Name
        dataSource.setPassword(DATABASE_USER_PASSWORD); // DataSource Database User Password

        // Amount of connection pool
        return dataSource;
    }
}

