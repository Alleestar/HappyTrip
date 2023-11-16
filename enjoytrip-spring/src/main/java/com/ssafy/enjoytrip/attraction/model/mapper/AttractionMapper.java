package com.ssafy.enjoytrip.attraction.model.mapper;

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
}
