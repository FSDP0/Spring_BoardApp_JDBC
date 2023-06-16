package com.boardapp.boardapi.board.repository;

import java.sql.Connection;
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

            conn.close();
            psmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }

        return boardList;
    }

    @Override
    public Board findBoardById(Long id) {
        String sql = "SELECT * FROM board WHERE board_id = " + id;

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        Board board = Board.builder().build();

        try {
            conn = this.dataSource.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            if (rs.next()) {
                board = Board.builder().id(rs.getLong("board_id"))
                        .title(rs.getString("board_title")).author(rs.getString("board_author"))
                        .createdDate(rs.getDate("created_date"))
                        .modifiedDate(rs.getDate("modified_date")).build();

            }

            conn.close();
            psmt.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("[ ERROR ] \\... Message: Error Occured !");
            System.out.println("[ ERROR ] \\... Message: " + e.getMessage());
        }

        return board;
    }

    @Override
    public void saveBoard(Board board) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveBoard'");
    }

    @Override
    public void editBoard(Long id, Board board) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editBoard'");
    }

    @Override
    public void deleteBoard(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBoard'");
    }
}
