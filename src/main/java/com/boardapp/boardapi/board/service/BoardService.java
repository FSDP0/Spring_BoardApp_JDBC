package com.boardapp.boardapi.board.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.boardapp.boardapi.board.entity.Board;
import com.boardapp.boardapi.board.model.BoardDto;
import com.boardapp.boardapi.board.repository.BoardRepository;

@Service
public class BoardService {

    // Constructor dependency injection
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    //

    public List<BoardDto> getBoards() {
        List<Board> boardList = this.boardRepository.findAllBoards();

        if (boardList.isEmpty()) {
            return null;
        }

        List<BoardDto> boardDtoList = new ArrayList<BoardDto>();

        // Convert Entities to DTO Model
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
        Board board = this.boardRepository.findBoardById(id);

        if (board == null) {
            return null;
        }

        // Convert Entity to DTO Model
        BoardDto boardDto = BoardDto.builder().num(board.getBoardId())
                .title(board.getBoardTitle()).writeName(board.getBoardAuthor())
                .contents(board.getBoardContent()).writeDate(board.getCreatedDate())
                .modifyDate(board.getModifiedDate()).build();

        return boardDto;
    }

    public void saveBoard(BoardDto boardDto) {
        this.boardRepository.saveBoard(boardDto.toEntity());
    }

    public void modifyBoard(Long id, BoardDto boardDto) {
        // Convert DTO Model to Entity
        Board board = boardDto.toEntity();

        this.boardRepository.editBoard(id, board);
    }

    public void removeBoard(Long id) {
        this.boardRepository.deleteBoard(id);
    }
}
