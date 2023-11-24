package com.ssafy.enjoytrip.plan.model;


import lombok.Data;

import java.util.List;

@Data
public class PlanMetaListDto {

    private List<PlanMetaDto> plans;
    private int currentPage;
    private int totalPageCount;
}
