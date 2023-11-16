package com.ssafy.enjoytrip.board.model.mapper;

import com.ssafy.enjoytrip.board.model.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@Mapper
public interface BoardMapper {

    void writeBoard(BoardDto boardDto) throws SQLException;

    void modifyBoard(BoardDto boardDto) throws SQLException;

    List<BoardDto> listBoard(Map<String, Object> param) throws SQLException;

    int getTotalBoardCount(Map<String, Object> param) throws SQLException;

    BoardDto getBoard(int boardNo) throws SQLException;

    void updateHit(Map<String, Object> param) throws SQLException;

    void deleteBoard(int boardNo) throws SQLException;

}


