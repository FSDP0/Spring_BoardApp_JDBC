package com.boardapp.boardapi.board.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.boardapp.boardapi.board.entity.Board;
import com.boardapp.boardapi.board.model.BoardDto;
import com.boardapp.boardapi.board.repository.BoardJdbcRepository;

@Service
public class BoardService {

    // Constructor dependency injection
    private final BoardJdbcRepository boardJdbcRepository;

    public BoardService(BoardJdbcRepository boardJdbcRepository) {
        this.boardJdbcRepository = boardJdbcRepository;
    }
    //

    public List<BoardDto> getBoards() {
        List<Board> boardList = this.boardJdbcRepository.findAllBoards();

        if (boardList.isEmpty()) {
            return null;
        }

        List<BoardDto> boardDtoList = new ArrayList<BoardDto>();

        for (Board board : boardList) {
            BoardDto boardDto = BoardDto.builder().id(board.getBoardId())
                    .title(board.getBoardTitle()).writeName(board.getBoardAuthor())
                    .contents(board.getBoardContent()).writeDate(board.getCreatedDate())
                    .modifyDate(board.getModifiedDate()).build();

            boardDtoList.add(boardDto);
        }

        return boardDtoList;
    }

    public void getBoardById() {

    }

    public void saveBoard() {

    }

    public void modifyBoard() {

    }

    public void removeBoard() {

    }
}
