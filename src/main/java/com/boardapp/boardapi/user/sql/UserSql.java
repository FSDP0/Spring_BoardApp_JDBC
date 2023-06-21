package com.boardapp.boardapi.user.sql;

public class UserSql {
    public static String SELECT_ALL = """
            SELECT A.*, B.user_address, B.address_zipcode
            FROM users.user A
            LEFT JOIN users.address B
            ON A.user_id = B.user_id
            """;

    public static String SELECT_BY_USER_ID = """
            SELECT A.*, B.user_address, B.address_zipcode
            FROM users.user A
            LEFT JOIN users.address B
            ON A.user_id = B.user_id
            WHERE A.user_id = ?
            """;

    public static String INSERT_USER = """
            INSERT INTO users.user(
                user_id,
                user_name,
                user_password,
                user_tel,
                created_date
            ) VALUES (?, ?, ?, ?, ?)
            """;

    public static String INSERT_ADDRESS = """
            INSERT INTO users.address(
                user_id,
                user_address,
                address_zipcode
            ) VALUES (?, ?, ?)
            """;

    public static String UPDATE_USER_BY_USER_ID = """
            UPDATE users.user
            SET user_name = ?,
                user_password = ?,
                user_tel = ?,
                modified_date = ?
            WHERE user_id = ?
            """;

    public static String UPDATE_ADDRESS_BY_USER_ID = """
            UPDATE users.address
            SET user_address = ?,
                address_zipcode = ?
            WHERE user_id = ?
            """;

    public static String DELETE_BY_USER_ID = """
            DELETE
            FROM users.user
            WHERE user_id = ?
            """;
}
