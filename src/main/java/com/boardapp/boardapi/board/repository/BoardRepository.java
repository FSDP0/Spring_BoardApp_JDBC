package com.boardapp.boardapi.board.repository;

import java.util.List;
import com.boardapp.boardapi.board.entity.Board;

public interface BoardRepository {
    public List<Board> findAllBoards();

    public Board findBoardById(Long id);

    public void saveBoard(Board board);

    public void editBoard(Long id, Board board);

    public void deleteBoard(Long id);
}
