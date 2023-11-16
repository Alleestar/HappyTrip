package com.ssafy.enjoytrip.board.model;


import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BoardListDto {

    private List<BoardDto> Boards;
    private int currentPage;
    private int totalPageCount;
}
