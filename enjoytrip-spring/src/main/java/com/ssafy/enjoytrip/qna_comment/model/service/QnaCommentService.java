package com.ssafy.enjoytrip.qna_comment.model.service;

import com.ssafy.enjoytrip.qna_comment.model.QnaCommentRequestDto;
import com.ssafy.enjoytrip.qna_comment.model.QnaCommentResponseDto;
import com.ssafy.enjoytrip.qna_comment.model.mapper.QnaCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaCommentService {

    private final QnaCommentMapper qnaCommentMapper;

    /**
     * 댓글 저장
     * @param params - 댓글 정보
     * @return Generated PK
     */
    @Transactional
    public Long saveComment(final QnaCommentRequestDto params) {
        qnaCommentMapper.save(params);
        return params.getId();
    }

    /**
     * 댓글 상세정보 조회
     * @param id - PK
     * @return 댓글 상세정보
     */
    public QnaCommentResponseDto findCommentById(final Long id) {
        return qnaCommentMapper.findById(id);
    }

    /**
     * 댓글 수정
     * @param params - 댓글 정보
     * @return PK
     */
    @Transactional
    public Long updateComment(final QnaCommentRequestDto params) {
        qnaCommentMapper.update(params);
        return params.getId();
    }

    /**
     * 댓글 삭제
     * @param id - PK
     * @return PK
     */
    @Transactional
    public Long deleteComment(final Long id) {
        qnaCommentMapper.deleteById(id);
        return id;
    }

    /**
     * 댓글 리스트 조회
     * @param postId - 게시글 번호 (FK)
     * @return 특정 게시글에 등록된 댓글 리스트
     */
    public List<QnaCommentResponseDto> findAllComment(final Long postId) {
        return qnaCommentMapper.findAll(postId);
    }

}