package com.boardapp.boardapi.common.util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcConnection {

    private final DataSource dataSource;

    public JdbcConnection(DataSource dataSource) {
        this.dataSource = dataSource;

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = this.getConnection();
            stmt = conn.createStatement();

            String sql = "CREATE TABLE board(";
            sql += "board_id BIGINT(20) NOT NULL AUTOINCREMENT,";
            sql += "board_title VARCHAR(50) NOT NULL ,";
            sql += "board_author VARCHAR(30) NOT NULL ,";
            sql += "board_content VARCHAR(200) NOT NULL ,";
            sql += "created_date DATETIME NOT NULL";
            sql += "modified_date DATETIME NOT NULL";
            sql += "PRIMARY KEY (board_id))";

            stmt.executeUpdate(sql);

            System.out.println("[ INFO ] \\... Message: Created table [board]");

            sql = "CREATE TABLE user(";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DataSourceUtils.getConnection(dataSource);
    }

    public void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }
    }
}

