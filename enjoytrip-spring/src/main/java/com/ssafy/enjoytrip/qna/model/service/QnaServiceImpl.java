package com.ssafy.enjoytrip.qna.model.service;

import com.ssafy.enjoytrip.qna.model.QnaDto;
import com.ssafy.enjoytrip.qna.model.QnaListDto;
import com.ssafy.enjoytrip.qna.model.mapper.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

    private final QnaMapper qnaMapper;

    @Override
    @Transactional
    public void writeQna(QnaDto qnaDto) throws Exception {
        qnaMapper.writeQna(qnaDto);
    }

    @Override
    @Transactional(readOnly = true)
    public QnaListDto listQna(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : (String) map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "10" : (String) map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;

        String key = map.get("key") == null ? "" : (String) map.get("key");
        String word = map.get("word") == null ? "" : (String) map.get("word");

        param.put("start", start);
        param.put("listsize", sizePerPage);
        param.put("key", key);
        param.put("word", word);
        System.out.println(param);

        List<QnaDto> list = qnaMapper.listQna(param);
        int totalQnaCount = qnaMapper.getTotalQnaCount(param);
        int totalPageCount = (totalQnaCount - 1) / sizePerPage + 1;
        QnaListDto QnaListDto = new QnaListDto();
        QnaListDto.setQnas(list);
        QnaListDto.setCurrentPage(currentPage);
        QnaListDto.setTotalPageCount(totalPageCount);

        return QnaListDto;
    }

    @Override
    @Transactional
    public QnaDto getQna(int qnaNo) throws Exception {
        QnaDto qnaDto = qnaMapper.getQna(qnaNo);
        qnaDto.setReadCnt(qnaDto.getReadCnt() + 1);
        Map<String, Object> params = new HashMap<>();
        params.put("QnaNo", qnaNo);
        params.put("readCnt", qnaDto.getReadCnt());
        updateHit(params);
        return qnaDto;
    }

    @Override
    @Transactional
    public void updateHit(Map<String, Object> param) throws Exception {
        qnaMapper.updateHit(param);
    }

    @Override
    @Transactional
    public void deleteQna(int QnaNo) throws Exception {
        // TODO : QnaDaoImpl의 deleteQna 호출
        qnaMapper.deleteQna(QnaNo);
    }

    @Override
    public void modifyQna(QnaDto qnaDto) throws Exception {
        qnaMapper.modifyQna(qnaDto);
    }


}
