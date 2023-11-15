package com.ssafy.enjoytrip.attraction.model;


import com.ssafy.enjoytrip.board.model.BoardDto;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class AttractionListDto {

    private List<AttractionDto> attractions;
    private int currentPage;
    private int totalPageCount;
}
