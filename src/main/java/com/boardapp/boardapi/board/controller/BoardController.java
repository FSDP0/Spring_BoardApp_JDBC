package com.boardapp.boardapi.board.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boardapp.boardapi.board.model.BoardDto;
import com.boardapp.boardapi.board.service.BoardService;

@RestController
@RequestMapping("boards")
public class BoardController {

    // Constructor Dependency Injection
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    //

    @GetMapping
    private List<BoardDto> findAllBoards() {
        return this.boardService.getBoards();
    }

    @GetMapping("/:{id}")
    private void findBoardById() {

    }

    @PostMapping
    private void createBoard() {

    }

    @PutMapping("/:{id}")
    private void editBoard() {

    }

    @DeleteMapping("/:{id}")
    private void deleteBoard() {

    }
}
