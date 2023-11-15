package com.ssafy.enjoytrip.qna.model;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class QnaDto {

    private int QnaNo, commentCnt, readCnt;
    private String title, content, userNickname;
    private String datetime;
}
