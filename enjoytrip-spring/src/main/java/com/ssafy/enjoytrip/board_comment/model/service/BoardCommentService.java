package com.ssafy.enjoytrip.board_comment.model.service;

import com.ssafy.enjoytrip.board_comment.model.BoardCommentRequestDto;
import com.ssafy.enjoytrip.board_comment.model.BoardCommentResponseDto;
import com.ssafy.enjoytrip.board_comment.model.mapper.BoardCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardCommentMapper boardCommentMapper;

    /**
     * 댓글 저장
     * @param params - 댓글 정보
     * @return Generated PK
     */
    @Transactional
    public Long saveComment(final BoardCommentRequestDto params) {
        boardCommentMapper.save(params);
        return params.getId();
    }

    /**
     * 댓글 상세정보 조회
     * @param id - PK
     * @return 댓글 상세정보
     */
    public BoardCommentResponseDto findCommentById(final Long id) {
        return boardCommentMapper.findById(id);
    }

    /**
     * 댓글 수정
     * @param params - 댓글 정보
     * @return PK
     */
    @Transactional
    public Long updateComment(final BoardCommentRequestDto params) {
        boardCommentMapper.update(params);
        return params.getId();
    }

    /**
     * 댓글 삭제
     * @param id - PK
     * @return PK
     */
    @Transactional
    public Long deleteComment(final Long id) {
        boardCommentMapper.deleteById(id);
        return id;
    }

    /**
     * 댓글 리스트 조회
     * @param postId - 게시글 번호 (FK)
     * @return 특정 게시글에 등록된 댓글 리스트
     */
    public List<BoardCommentResponseDto> findAllComment(final Long postId) {
        return boardCommentMapper.findAll(postId);
    }

}