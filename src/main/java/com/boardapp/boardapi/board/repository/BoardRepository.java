package com.boardapp.boardapi.board.repository;

import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.boardapp.boardapi.board.entity.Board;

@Repository
public class BoardRepository {
    // Basic SQL Query
    public static final String SELECT_ALL = "SELECT * FROM board";

    NamedParameterJdbcTemplate jdbc;

    private RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);

    public BoardRepository(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Board> findAllBoard() {
        return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
    }
}
