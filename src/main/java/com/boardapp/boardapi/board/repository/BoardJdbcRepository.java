package com.boardapp.boardapi.board.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import com.boardapp.boardapi.board.entity.Board;

@Repository
public class BoardJdbcRepository implements BoardRepository {
    private final DataSource dataSource;

    public BoardJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public List<Board> findAllBoards() {
        String sql = "SELECT * FROM board";

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
                        .title(rs.getString("board_title")).author(rs.getString("board_author"))
                        .createdDate(rs.getDate("created_date"))
                        .modifiedDate(rs.getDate("modified_date")).build();

                boardList.add(board);
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
                e.printStackTrace();
            }
        }

        return boardList;
    }

    @Override
    public Board findBoardById(Long id) {
        String sql = "SELECT * FROM board WHERE board_id = " + id;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Board board = Board.builder().build();

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                board = Board.builder().id(rs.getLong("board_id"))
                        .title(rs.getString("board_title")).author(rs.getString("board_author"))
                        .createdDate(rs.getDate("created_date"))
                        .modifiedDate(rs.getDate("modified_date")).build();

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
                e.printStackTrace();
            }
        }

        return board;
    }

    @Override
    public void saveBoard(Board board) {
        String sql = "INSERT INTO board(";
        sql += "board_title, board_author, board_contents, createdDate, modifiedDate";
        sql += ") VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(0, board.getBoardTitle());
            pstmt.setString(1, board.getBoardAuthor());
            pstmt.setString(2, board.getBoardContent());
            pstmt.setDate(3, (Date) board.getCreatedDate());
            pstmt.setDate(4, (Date) board.getModifiedDate());
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }
    }

    @Override
    public void editBoard(Long id, Board board) {
        String sql = "UPDATE board SET";
        sql += "board_title = ?";
        sql += "board_author = ?";
        sql += "board_content = ?";
        sql += "WHERE board_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(0, board.getBoardTitle());
            pstmt.setString(1, board.getBoardAuthor());
            pstmt.setString(2, board.getBoardContent());
            pstmt.setLong(3, id);

            int resultSize = pstmt.executeUpdate();

            System.out.println(resultSize);

        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteBoard(Long id) {
        String sql = "DELETE FROM board WHERE board_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = this.dataSource.getConnection();

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(0, id);

            int resultSize = pstmt.executeUpdate();

            System.out.println(resultSize);
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("[ ERROR  ] \\... Message: Error");
            }
        }
    }
}
