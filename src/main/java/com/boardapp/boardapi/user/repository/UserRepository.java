package com.boardapp.boardapi.user.repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import com.boardapp.boardapi.user.entity.UserInfo;
import com.boardapp.boardapi.user.sql.UserSql;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserRepository {
    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<UserInfo> findAllUserInfo() {
        String sql = UserSql.SELECT_ALL;

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
            log.error("Error occured : ", e);
        }

        return userInfoList;
    }

    public UserInfo findUserInfoById(String id) {
        String sql = UserSql.SELECT_BY_USER_ID;

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
            log.error("Error occured : ", e);
        }

        return userInfo;
    }

    public void saveUser(UserInfo userInfo) {
        String userSql = UserSql.INSERT_USER;
        String userAddressSql = UserSql.INSERT_ADDRESS;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(userSql);

            // Set Transaction
            conn.setAutoCommit(false);

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

            // Transaction Commit
            conn.commit();
        } catch (SQLException e) {
            log.error("Error occured : ", e);
        }


    }

    public void editUser(String id, UserInfo userInfo) {
        String userSql = UserSql.UPDATE_USER_BY_USER_ID;

        String userAddressSql = UserSql.UPDATE_ADDRESS_BY_USER_ID;

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

            log.info("Successful process : Update user table");
            log.info("Result size : " + resultSize);

            pstmt = conn.prepareStatement(userAddressSql);

            pstmt.setString(1, userInfo.getUserAddress());
            pstmt.setString(2, userInfo.getAddressZipCode());
            pstmt.setString(3, id);

            resultSize = pstmt.executeUpdate();

            log.info("Successful process : Update address table");
            log.info("Result size : " + resultSize);

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            log.error("Error occured : ", e);
        }
    }

    public void deleteUser(String id) {
        String sql = UserSql.DELETE_BY_USER_ID;

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
            log.error("Error occured : ", e);
        }
    }
}
