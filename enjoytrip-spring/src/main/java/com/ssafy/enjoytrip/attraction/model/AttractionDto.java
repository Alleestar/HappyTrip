package com.ssafy.enjoytrip.attraction.model;

import lombok.Data;

@Data
public class AttractionDto {
    int contentId;
    int contentTypeId;
    String cat1;
    String cat2;
    String cat3;
    String title;
    String addr1;
    String img1;
    String img2;
    double latitude;
    double longitude;
}
