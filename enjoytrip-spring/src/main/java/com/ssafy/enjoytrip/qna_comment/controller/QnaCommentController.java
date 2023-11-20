package com.ssafy.enjoytrip.qna_comment.controller;

import com.ssafy.enjoytrip.qna_comment.model.QnaCommentRequestDto;
import com.ssafy.enjoytrip.qna_comment.model.QnaCommentResponseDto;
import com.ssafy.enjoytrip.qna_comment.model.service.QnaCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qna_comment")
@Api(tags = { "Qna_Comment Controller API" })
@RequiredArgsConstructor
public class QnaCommentController {

    private final QnaCommentService qnaCommentService;

    // 신규 댓글 생성
    @ApiOperation(value = "댓글 등록", notes = "새로운 댓글을 입력한다.")
    @PostMapping
    public QnaCommentResponseDto saveComment(@RequestBody QnaCommentRequestDto params) {
        Long id = qnaCommentService.saveComment(params);
        return qnaCommentService.findCommentById(id);
    }

    // 모든 댓글 찾아주기
    @ApiOperation(value = "댓글 등록", notes = "새로운 댓글을 입력한다.")
    @GetMapping("/findall/{postId}")
    public List<QnaCommentResponseDto> findAllComment(@PathVariable final Long postId) {
        return qnaCommentService.findAllComment(postId);
    }

    // 댓글 상세정보를 알려준다.
    @ApiOperation(value = "댓글 상세정보", notes = "해당 댓글의 상세정보를 알려준다.")
    @GetMapping("/find/{id}")
    public QnaCommentResponseDto findCommentById(@PathVariable final Long id) {
        return qnaCommentService.findCommentById(id);
    }


    // 기존 댓글 수정
    @ApiOperation(value = "댓글 수정", notes = "기존의 댓글을 수정한다.")
    @PatchMapping("/update/{id}")
    public QnaCommentResponseDto updateComment(@PathVariable final Long id, @RequestBody final QnaCommentRequestDto params) {
        qnaCommentService.updateComment(params);
        return qnaCommentService.findCommentById(id);
    }

    // 기존 댓글 삭제하기
    @ApiOperation(value = "댓글 삭제", notes = "가존의 댓글을 삭제한다.")
    @DeleteMapping("/delete/{id}")
    public Long deleteComment(@PathVariable final Long id) {
        return qnaCommentService.deleteComment(id);
    }

}