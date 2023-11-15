package com.ssafy.enjoytrip.qna.model;


import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class QnaListDto {

    private List<QnaDto> Qnas;
    private int currentPage;
    private int totalPageCount;
}
