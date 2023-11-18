package com.ssafy.enjoytrip.attraction.model.service;

import com.ssafy.enjoytrip.attraction.model.AddrDto;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.AttractionListDto;
import com.ssafy.enjoytrip.attraction.model.AttractionDetailDto;
import java.util.List;
import java.util.Map;

public interface AttractionService {
    AttractionListDto listAttraction(Map<String, String> param) throws Exception;
    AttractionDetailDto getAttractionDetail(String aid) throws Exception;
    List<AddrDto> getAddr() throws Exception;
}
