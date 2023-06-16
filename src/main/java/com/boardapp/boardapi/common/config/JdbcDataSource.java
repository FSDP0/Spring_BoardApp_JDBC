package com.boardapp.boardapi.common.config;

public class JdbcDataSource {
    public final String DATABASE_DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";
    // public final String DATABASE_URL = "jdbc:mariadb://${IP.DATABASE_IP}:3306/board";
    public final String DATABASE_URL = "jdbc:mariadb://localhost:3306/board";
    public final String DATABASE_USER_NAME = "root";
    public final String DATABASE_USER_PASSWORD = "123456";
}
