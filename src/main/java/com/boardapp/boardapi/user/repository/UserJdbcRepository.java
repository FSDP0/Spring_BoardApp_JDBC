package com.boardapp.boardapi.user.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.boardapp.boardapi.user.entity.User;

public class UserJdbcRepository implements UserRepository {
    private final DataSource dataSource;

    public UserJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM user";

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        List<User> userList = new ArrayList<User>();

        try {
            conn = this.dataSource.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                User user = User.builder().id(rs.getString("user_id"))
                        .name(rs.getString("user_nme")).password(rs.getString("user_password"))
                        .createdDate(rs.getDate("created_date"))
                        .modifiedDate(rs.getDate("modified_date")).build();

                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        } finally {
            try {
                psmt.close();
                rs.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("[ ERROR ] \\... Message: Error Occured !");
                System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
            }
        }

        return userList;
    }

    @Override
    public User findUserById(String id) {
        String sql = "SELECT * FROM user WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        User user = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = User.builder().build();
            }
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        } finally {
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("[ ERROR ] \\... Message: Error Occured !");
                System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
            }
        }

        return user;
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO user(";
        sql += "user_id, user_name, user_password, created_date";
        sql += ") VALUES (?, ?, ? ,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getUserPassword());
            pstmt.setDate(4, (Date) user.getCreatedDate());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("[ ERROR ] \\... Message: Error Occured !");
                System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
            }
        }
    }

    @Override
    public void editUser(String id, User user) {
        String sql = "UPDATE user SET ";
        sql += "user_name = ? ,";
        sql += "user_password = ? ,";
        sql += "modified_date = ? ";
        sql += "WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setDate(3, user.getModifiedDate());
            pstmt.setString(4, id);

            int resultSize = pstmt.executeUpdate();

            System.out.println("[ INFO ] \\... Message: Process Complete");
            System.out.println("[ INFO ] \\... Message: " + resultSize);
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("[ ERROR ] \\... Message: Error Occured !");
                System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteUser(String id) {
        String sql = "DELETE FROM user WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("[ ERROR ] \\... Message: Error Occured !");
                System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
            }
        }
    }

}
