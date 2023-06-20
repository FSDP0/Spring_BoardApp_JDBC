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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Board API", description = "Board CRUD methods")
@RestController
@RequestMapping("boards")
public class BoardController {

    // Constructor Dependency Injection
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    //

    @Operation(summary = "모든 게시글 조회", description = "등록되어있는 모든 게시글들을 조회합니다.")
    @GetMapping
    private List<BoardDto> findAllBoards() {
        return this.boardService.getBoards();
    }

    @Operation(summary = "특정 게시글 조회", description = "등록되어있는 특정 게시글을 번호로 조회합니다.")
    @GetMapping("/:{id}")
    private BoardDto findBoardById(@PathVariable Long id) {
        return this.boardService.getBoardById(id);
    }

    @Operation(summary = "게시글 등록", description = "추가적으로 게시글을 등록합니다.")
    @PostMapping
    private void createBoard(@RequestBody BoardDto dto) {
        this.boardService.saveBoard(dto);
    }

    @Operation(summary = "게시글 수정", description = "등록되어있는 특정 게시글을 수정합니다.")
    @PutMapping("/:{id}")
    private void editBoard(@PathVariable Long id, @RequestBody BoardDto dto) {
        this.boardService.modifyBoard(id, dto);
    }

    @Operation(summary = "게시글 삭제", description = "등록되어있는 특정 게시글을 삭제합니다.")
    @DeleteMapping("/:{id}")
    private void deleteBoard(@PathVariable Long id) {
        this.boardService.removeBoard(id);
    }
}
