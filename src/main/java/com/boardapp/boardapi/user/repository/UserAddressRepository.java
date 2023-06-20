package com.boardapp.boardapi.user.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.boardapp.boardapi.user.entity.UserAddress;

public class UserAddressRepository {
    private final DataSource dataSource;

    public UserAddressRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<UserAddress> findAllUserAddress() {
        String sql = "SELECT * FROM users.user_address";

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        List<UserAddress> userAddressList = new ArrayList<UserAddress>();

        try {
            conn = this.dataSource.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                UserAddress userAddress = UserAddress.builder()
                        .id(rs.getString("user_id")).address(rs.getString("user_address"))
                        .zipCode(rs.getString("address_zipcode")).build();

                userAddressList.add(userAddress);
            }

            psmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }

        return userAddressList;
    };

    public UserAddress findUserAddressById(String id) {
        String sql = "SELECT * FROM users.user_address WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        UserAddress userAddress = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                userAddress = UserAddress.builder().id(rs.getString("user_id"))
                        .address(rs.getString("user_address"))
                        .zipCode(rs.getString("address_zipcode")).build();
            }

            pstmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }

        return userAddress;
    }

    public void saveUserAddress(UserAddress userAddress) {
        String sql = "INSERT INTO users.user_address(";
        sql += "user_id, user_address, address_zipcode";
        sql += ") VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userAddress.getUserId());
            pstmt.setString(2, userAddress.getUserAddress());
            pstmt.setString(3, userAddress.getAddressZipCode());

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }
    }

    public void editUserAddress(String id, UserAddress userAddress) {
        String sql = "UPDATE users.user SET ";
        sql += "user_address = ?";
        sql += "address_zipcode = ?";
        sql += "WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userAddress.getUserAddress());
            pstmt.setString(2, userAddress.getAddressZipCode());
            pstmt.setString(3, id);

            int resultSize = pstmt.executeUpdate();

            System.out.println("[ INFO ] \\... Message: Process Complete");
            System.out.println("[ INFO ] \\... Message: " + resultSize);

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }
    }
}
