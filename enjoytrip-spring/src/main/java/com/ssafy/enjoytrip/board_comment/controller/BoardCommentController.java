package com.ssafy.enjoytrip.board_comment.controller;

import com.ssafy.enjoytrip.board_comment.model.BoardCommentRequestDto;
import com.ssafy.enjoytrip.board_comment.model.BoardCommentResponseDto;
import com.ssafy.enjoytrip.board_comment.model.service.BoardCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board_comment")
@Api(tags = { "Board_Comment Controller API" })
@RequiredArgsConstructor
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    // 신규 댓글 생성
    @ApiOperation(value = "댓글 등록", notes = "새로운 댓글을 입력한다.")
    @PostMapping
    public BoardCommentResponseDto saveComment(@RequestBody BoardCommentRequestDto params) {
        Long id = boardCommentService.saveComment(params);
        return boardCommentService.findCommentById(id);
    }

    // 모든 댓글 찾아주기
    @ApiOperation(value = "댓글 등록", notes = "새로운 댓글을 입력한다.")
    @GetMapping("/findall/{postId}")
    public List<BoardCommentResponseDto> findAllComment(@PathVariable final Long postId) {
        return boardCommentService.findAllComment(postId);
    }

    // 댓글 상세정보를 알려준다.
    @ApiOperation(value = "댓글 상세정보", notes = "해당 댓글의 상세정보를 알려준다.")
    @GetMapping("/find/{id}")
    public BoardCommentResponseDto findCommentById(@PathVariable final Long id) {
        return boardCommentService.findCommentById(id);
    }


    // 기존 댓글 수정
    @ApiOperation(value = "댓글 수정", notes = "기존의 댓글을 수정한다.")
    @PatchMapping("/update/{id}")
    public BoardCommentResponseDto updateComment(@PathVariable final Long id, @RequestBody final BoardCommentRequestDto params) {
        boardCommentService.updateComment(params);
        return boardCommentService.findCommentById(id);
    }

    // 기존 댓글 삭제하기
    @ApiOperation(value = "댓글 삭제", notes = "가존의 댓글을 삭제한다.")
    @DeleteMapping("/delete/{id}")
    public Long deleteComment(@PathVariable final Long id) {
        return boardCommentService.deleteComment(id);
    }

}