package com.boardapp.boardapi.board.repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import com.boardapp.boardapi.board.entity.Board;
import com.boardapp.boardapi.board.sql.BoardSql;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoardRepository {
    private final DataSource dataSource;

    public BoardRepository(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    public List<Board> findAllBoards() {
        String sql = BoardSql.SELECT_ALL;

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        List<Board> boardList = new ArrayList<Board>();

        try {
            conn = this.dataSource.getConnection();

            psmt = conn.prepareStatement(sql);

            rs = psmt.executeQuery();

            while (rs.next()) {
                Board board = Board.builder().id(rs.getLong("board_id"))
                        .title(rs.getString("board_title"))
                        .author(rs.getString("user_id"))
                        .content(rs.getString("board_content"))
                        .createdDate(rs.getDate("created_date"))
                        .modifiedDate(rs.getDate("modified_date")).build();

                boardList.add(board);
            }

            psmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            log.error("Error", e);
        }

        return boardList;
    }

    public Board findBoardById(Long id) {
        String sql = BoardSql.SELECT_BY_ID;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Board board = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            // Setting SQL parameter
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                board = Board.builder().id(rs.getLong("board_id"))
                        .title(rs.getString("board_title"))
                        .author(rs.getString("user_id"))
                        .content(rs.getString("board_content"))
                        .createdDate(rs.getDate("created_date"))
                        .modifiedDate(rs.getDate("modified_date")).build();

            }

            pstmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            log.error("error", e);
        }

        return board;
    }

    public void saveBoard(Board board) {
        String sql = BoardSql.INSERT_BOARD;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            // Setting SQL parameter
            pstmt.setString(1, board.getBoardTitle());
            pstmt.setString(2, board.getBoardAuthor());
            pstmt.setString(3, board.getBoardContent());

            pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }
    }

    public void editBoard(Long id, Board board) {
        String sql = BoardSql.UPDATE_BY_ID;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            // Setting SQL parameter
            pstmt.setString(1, board.getBoardTitle());
            pstmt.setString(2, board.getBoardAuthor());
            pstmt.setString(3, board.getBoardContent());
            pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setLong(5, id);

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

    public void deleteBoard(Long id) {
        String sql = BoardSql.DELETE_BY_ID;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            // Setting SQL parameter
            pstmt.setLong(1, id);

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }
    }
}
