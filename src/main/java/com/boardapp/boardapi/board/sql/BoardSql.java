package com.boardapp.boardapi.board.sql;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardSql {
    public static String SELECT_ALL = """
            SELECT *
            FROM boards.board
            """;

    public static String SELECT_BY_ID = """
            SELECT *
            FROM boards.board
            WHERE board_id = ?
            """;

    public static String INSERT_BOARD = """
            INSERT INTO boards.board(
                board_title, user_id, board_content, created_date
            ) VALUES (?, ?, ?, ?)
            """;

    public static String UPDATE_BY_ID = """
            UPDATE boards.board SET
            board_title = ?,
            user_id = ?,
            board_content = ?,
            modified_date = ?
            WHERE board_id = ?
            """;

    public static String DELETE_BY_ID = """
            DELETE FROM boards.board
            WHERE board_id = ?
            """;
}
