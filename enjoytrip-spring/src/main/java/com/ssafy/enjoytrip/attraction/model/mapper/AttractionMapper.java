package com.ssafy.enjoytrip.attraction.model.mapper;

import com.ssafy.enjoytrip.attraction.model.AttractionListDto;
import com.ssafy.enjoytrip.attraction.model.AttractionDetailDto;
import com.ssafy.enjoytrip.attraction.model.AddrDto;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface AttractionMapper {
    List<AttractionDto> listAttraction(Map<String, Object> param) throws SQLException;

    int getTotalAttractionCount(Map<String, Object> param) throws SQLException;

    AttractionDto getAttraction(Map<String, Object> param) throws SQLException;
    AttractionDetailDto getAttractionDetail(int id) throws SQLException;
    List<AddrDto> getAddr() throws SQLException;
}
