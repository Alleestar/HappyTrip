package com.ssafy.enjoytrip.attraction.model;

import lombok.Data;

@Data
public class AttractionDto {
    int contentId;
    int contentTypeId;
    String categoryCode;
    String title;
    String addr1;
    double latitude;
    double longitude;
}
