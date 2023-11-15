package com.ssafy.enjoytrip.qna.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.qna.model.QnaDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface QnaMapper {

    void writeQna(QnaDto boardDto) throws SQLException;

    void modifyQna(QnaDto boardDto) throws SQLException;

    List<QnaDto> listQna(Map<String, Object> param) throws SQLException;

    int getTotalQnaCount(Map<String, Object> param) throws SQLException;

    QnaDto getQna(int QnaNo) throws SQLException;

    void updateHit(Map<String, Object> param) throws SQLException;

    void deleteQna(int QnaNo) throws SQLException;

}


