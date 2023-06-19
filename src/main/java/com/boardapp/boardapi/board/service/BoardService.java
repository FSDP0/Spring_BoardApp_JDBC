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
            BoardDto boardDto = BoardDto.builder().num(board.getBoardId())
                    .title(board.getBoardTitle()).writeName(board.getBoardAuthor())
                    .contents(board.getBoardContent()).writeDate(board.getCreatedDate())
                    .modifyDate(board.getModifiedDate()).build();

            boardDtoList.add(boardDto);
        }

        return boardDtoList;
    }

    public BoardDto getBoardById(Long id) {
        Board board = this.boardJdbcRepository.findBoardById(id);

        if (board == null) {
            return null;
        }

        BoardDto boardDto = BoardDto.builder().num(board.getBoardId()).title(board.getBoardTitle())
                .writeName(board.getBoardAuthor()).contents(board.getBoardContent())
                .writeDate(board.getCreatedDate()).modifyDate(board.getModifiedDate()).build();

        return boardDto;
    }

    public void saveBoard(BoardDto boardDto) {
        this.boardJdbcRepository.saveBoard(boardDto.toEntity());
    }

    public void modifyBoard(Long id, BoardDto boardDto) {
        Board board = boardDto.toEntity();

        this.boardJdbcRepository.editBoard(id, board);
    }

    public void removeBoard(Long id) {
        this.boardJdbcRepository.deleteBoard(id);
    }
}
