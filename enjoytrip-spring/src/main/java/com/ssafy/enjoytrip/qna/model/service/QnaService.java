package com.ssafy.enjoytrip.qna.model.service;

import com.ssafy.enjoytrip.qna.model.QnaDto;
import com.ssafy.enjoytrip.qna.model.QnaListDto;

import java.util.Map;

public interface QnaService {


    void writeQna(QnaDto qnaDto) throws Exception;

    void modifyQna(QnaDto qnaDto)  throws Exception;

    QnaListDto listQna(Map<String, String> map) throws Exception;

    QnaDto getQna(int qnaNo) throws Exception;

    void updateHit(Map<String, Object> param) throws Exception;

    void deleteQna(int QnaNo) throws Exception;

}
