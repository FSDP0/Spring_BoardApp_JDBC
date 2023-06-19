package com.boardapp.boardapi.board.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private BoardDto findBoardById(@PathVariable Long id) {
        return this.boardService.getBoardById(id);
    }

    @PostMapping
    private void createBoard(@RequestBody BoardDto dto) {
        this.boardService.saveBoard(dto);
    }

    @PutMapping("/:{id}")
    private void editBoard(@PathVariable Long id, @RequestBody BoardDto dto) {
        this.boardService.modifyBoard(id, dto);
    }

    @DeleteMapping("/:{id}")
    private void deleteBoard(@PathVariable Long id) {
        this.boardService.removeBoard(id);
    }
}
