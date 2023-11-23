package com.ssafy.enjoytrip.plan.model;

import lombok.Data;

@Data
public class PlanMetaDto {

    private Long planId;
    private String userId;
    private String title;
    private String date;
    private String color;
}
