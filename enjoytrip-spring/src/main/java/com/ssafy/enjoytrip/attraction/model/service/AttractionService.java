package com.ssafy.enjoytrip.attraction.model.service;

import com.ssafy.enjoytrip.attraction.model.AddrDto;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.AttractionListDto;

import java.util.List;
import java.util.Map;

public interface AttractionService {
    AttractionListDto listAttraction(Map<String, String> param) throws Exception;
    AttractionDto getAttraction(Map<String, String> param) throws Exception;
    List<AddrDto> getAddr() throws Exception;
}
