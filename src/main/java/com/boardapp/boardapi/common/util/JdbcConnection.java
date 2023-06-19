package com.boardapp.boardapi.common.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

public class JdbcConnection {

    private final DataSource dataSource;

    public JdbcConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void JdbcTableCreate(String sql) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = this.dataSource.getConnection();
            stmt = conn.createStatement();

            stmt.executeUpdate(sql);

            System.out.println("[ INFO ] \\... Message: Process Complete");
            System.out.println("[ INFO ] \\... Message: Create Table Success");
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }
    }
    // public Connection getConnection() throws SQLException {
    // return DataSourceUtils.getConnection(dataSource);
    // }

    // public void close(Connection conn) throws SQLException {
    // DataSourceUtils.releaseConnection(conn, dataSource);
    // }

    // public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
    // try {
    // if (rs != null) {
    // rs.close();
    // }
    // } catch (SQLException e) {
    // System.out.println("[ ERROR ] \\... Message: Error Occured !");
    // System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
    // }

    // try {
    // if (pstmt != null) {
    // pstmt.close();
    // }
    // } catch (SQLException e) {
    // System.out.println("[ ERROR ] \\... Message: Error Occured !");
    // System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
    // }

    // try {
    // if (conn != null) {
    // conn.close();
    // }
    // } catch (SQLException e) {
    // System.out.println("[ ERROR ] \\... Message: Error Occured !");
    // System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
    // }
    // }
}

