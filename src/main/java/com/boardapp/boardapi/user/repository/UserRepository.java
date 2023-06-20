package com.boardapp.boardapi.user.repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import com.boardapp.boardapi.user.entity.UserInfo;

@Repository
public class UserRepository {
    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<UserInfo> findAllUserInfo() {
        String sql = "SELECT A.*, B.user_address, B.address_zipcode ";
        sql += "FROM users.user A ";
        sql += "LEFT JOIN users.user_address B ";
        sql += "ON A.user_id = B.user_id ";

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        List<UserInfo> userInfoList = new ArrayList<UserInfo>();

        try {
            conn = this.dataSource.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                UserInfo user = UserInfo.builder().id(rs.getString("user_id"))
                        .name(rs.getString("user_name"))
                        .password(rs.getString("user_password"))
                        .phoneNumber(rs.getString("user_tel"))
                        .address(rs.getString("user_address"))
                        .zipcode(rs.getString("address_zipcode"))
                        .createdDate(rs.getTimestamp("created_date"))
                        .modifiedDate(rs.getTimestamp("modified_date")).build();

                userInfoList.add(user);
            }

            psmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }

        return userInfoList;
    }

    public UserInfo findUserInfoById(String id) {
        String sql = "SELECT A.*, B.user_address, B.address_zipcode ";
        sql += "FROM users.user A ";
        sql += "LEFT JOIN users.user_address AS B ";
        sql += "ON A.user_id = B.user_id ";
        sql += "WHERE A.user_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        UserInfo userInfo = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                userInfo = UserInfo.builder().id(rs.getString("user_id"))
                        .name(rs.getString("user_name"))
                        .password(rs.getString("user_password"))
                        .phoneNumber(rs.getString("user_tel"))
                        .address(rs.getString("user_address"))
                        .zipcode(rs.getString("address_zipcode"))
                        .createdDate(rs.getTimestamp("created_date"))
                        .modifiedDate(rs.getTimestamp("modified_date")).build();
            }

            pstmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }

        return userInfo;
    }

    public void saveUser(UserInfo userInfo) {
        String userSql = "INSERT INTO users.user(";
        userSql += "user_id, user_name, user_password, user_tel, created_date";
        userSql += ") VALUES (?, ?, ?, ?, ?)";

        String userAddressSql = "INSERT INTO users.user_address(";
        userAddressSql += "user_id, user_address, address_zipcode";
        userAddressSql += ") VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(userSql);

            pstmt.setString(1, userInfo.getUserId());
            pstmt.setString(2, userInfo.getUserName());
            pstmt.setString(3, userInfo.getUserPassword());
            pstmt.setString(4, userInfo.getUserPhoneNumber());
            pstmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));

            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(userAddressSql);

            pstmt.setString(1, userInfo.getUserId());
            pstmt.setString(2, userInfo.getUserAddress());
            pstmt.setString(3, userInfo.getAddressZipCode());

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }
    }

    public void editUser(String id, UserInfo userInfo) {
        String userSql = "UPDATE users.user SET ";
        userSql += "user_name = ? ,";
        userSql += "user_password = ? ,";
        userSql += "user_tel = ? ,";
        userSql += "modified_date = ? ";
        userSql += "WHERE user_id = ?";

        String userAddressSql = "UPDATE users.user_address SET ";
        userAddressSql += "user_address = ? ,";
        userAddressSql += "address_zipcode = ? ";
        userAddressSql += "WHERE user_id = ?";


        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(userSql);

            pstmt.setString(1, userInfo.getUserName());
            pstmt.setString(2, userInfo.getUserPassword());
            pstmt.setString(3, userInfo.getUserPhoneNumber());
            pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(5, id);

            int resultSize = pstmt.executeUpdate();

            System.out.println("[ INFO ] \\... Message: Process Complete");
            System.out.println("[ INFO ] \\... Message: " + resultSize);

            pstmt = conn.prepareStatement(userAddressSql);

            pstmt.setString(1, userInfo.getUserAddress());
            pstmt.setString(2, userInfo.getAddressZipCode());
            pstmt.setString(3, id);

            resultSize = pstmt.executeUpdate();

            System.out.println("[ INFO ] \\... Message: Process Complete");
            System.out.println("[ INFO ] \\... Message: " + resultSize);

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }
    }

    public void deleteUser(String id) {
        String sql = "DELETE FROM users.user WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, id);

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }
    }
}
