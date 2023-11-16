package com.ssafy.enjoytrip.qna.model;

import lombok.Data;

@Data
public class QnaDto {

    private int QnaNo, commentCnt, readCnt;
    private String title, content, userNickname;
    private String datetime;
}
