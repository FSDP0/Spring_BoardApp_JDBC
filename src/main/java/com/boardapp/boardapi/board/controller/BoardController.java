package com.boardapp.boardapi.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("board")
public class BoardController {

    @GetMapping
    private void findAllBoards() {

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
