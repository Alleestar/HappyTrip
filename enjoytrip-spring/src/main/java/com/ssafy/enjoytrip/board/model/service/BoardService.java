package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardListDto;

import java.util.Map;

public interface BoardService {


    void writeBoard(BoardDto boardDto) throws Exception;

    void modifyBoard(BoardDto boardDto)  throws Exception;

    BoardListDto listBoard(Map<String, String> map) throws Exception;

    BoardDto getBoard(int boardNo) throws Exception;

    void updateHit(Map<String, Object> param) throws Exception;

    void deleteBoard(int boardNo) throws Exception;

}
