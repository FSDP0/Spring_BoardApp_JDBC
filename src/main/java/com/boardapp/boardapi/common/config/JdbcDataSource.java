package com.boardapp.boardapi.common.config;

public abstract class JdbcDataSource {
    public final String DATABASE_DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
    public final String DATABASE_URL = "jdbc:mariadb://localhost:3306/board";
    public final String DATABASE_USER_NAME = "root";
    public final String DATABASE_USER_PASSWORD = "123456";
}
