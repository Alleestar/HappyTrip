package com.ssafy.enjoytrip.attraction.model;

import lombok.Data;

@Data
public class AttractionDto {
    int contentId;
    int contentTypeId;
    String categoryCode;
    String title;
    String addr1;
    String img1;
    String img2;
    double latitude;
    double longitude;
}
