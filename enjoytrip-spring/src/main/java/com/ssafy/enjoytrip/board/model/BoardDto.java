package com.ssafy.enjoytrip.board.model;

import lombok.Data;

@Data
public class BoardDto {

    private int BoardNo, commentCnt, readCnt;
    private String title, content, userNickname;
    private String datetime;
}
