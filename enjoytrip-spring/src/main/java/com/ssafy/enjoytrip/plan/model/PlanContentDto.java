package com.ssafy.enjoytrip.plan.model;

import lombok.Data;

@Data
public class PlanContentDto {

    private Long planContentId;
    private Long planId;
    private int cost;
    private String memo;
    private String date;
    private int sortingMetaInfo;
    int contentId;
    int contentTypeId;
    String cat1;
    String cat2;
    String cat3;
    String title;
    String addr1;
    double latitude;
    double longitude;
}
