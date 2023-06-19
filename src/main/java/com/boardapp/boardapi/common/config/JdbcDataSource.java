package com.boardapp.boardapi.common.config;


public abstract class JdbcDataSource {
    // @Value("${database.driver-class-name}")
    // protected String DATABASE_DRIVER_CLASS_NAME;

    // @Value("${database.url}")
    // protected String DATABASE_URL;

    // @Value("${database.username}")
    // protected String DATABASE_USER_NAME;

    // @Value("${database.password}")
    // protected String DATABASE_USER_PASSWORD;

    protected final String DATABASE_DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
    protected final String DATABASE_URL = "jdbc:mariadb://localhost:3306";
    protected final String DATABASE_USER_NAME = "root";
    protected final String DATABASE_USER_PASSWORD = "123456";
}
