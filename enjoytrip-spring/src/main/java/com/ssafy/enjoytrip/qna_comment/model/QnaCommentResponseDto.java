package com.ssafy.enjoytrip.qna_comment.model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class QnaCommentResponseDto {

    private Long id;                       // 댓글 번호 (PK)
    private Long postId;                   // 게시글 번호 (FK)
    private String content;                // 내용
    private String writer;                 // 작성자
//    private Boolean deleteYn;              // 삭제 여부
    private String createdDate;     // 생성일시
    private String modifiedDate;    // 최종 수정일시

}