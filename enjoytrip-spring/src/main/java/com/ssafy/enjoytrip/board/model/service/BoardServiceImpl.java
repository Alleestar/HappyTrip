package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardListDto;
import com.ssafy.enjoytrip.board.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    @Transactional
    public void writeBoard(BoardDto boardDto) throws Exception {
        boardMapper.writeBoard(boardDto);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardListDto listBoard(Map<String, String> map) throws Exception {
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

        List<BoardDto> list = boardMapper.listBoard(param);
        int totalBoardCount = boardMapper.getTotalBoardCount(param);
        int totalPageCount = (totalBoardCount - 1) / sizePerPage + 1;
        BoardListDto boardListDto = new BoardListDto();
        boardListDto.setBoards(list);
        boardListDto.setCurrentPage(currentPage);
        boardListDto.setTotalPageCount(totalPageCount);

        return boardListDto;
    }

    @Override
    @Transactional
    public BoardDto getBoard(int boardNo) throws Exception {
        BoardDto boardDto = boardMapper.getBoard(boardNo);
        boardDto.setReadCnt(boardDto.getReadCnt() + 1);
        Map<String, Object> params = new HashMap<>();
        params.put("BoardNo", boardNo);
        params.put("readCnt", boardDto.getReadCnt());
        updateHit(params);
        return boardDto;
    }

    @Override
    @Transactional
    public void updateHit(Map<String, Object> param) throws Exception {
        boardMapper.updateHit(param);
    }

    @Override
    @Transactional
    public void deleteBoard(int boardNo) throws Exception {
        boardMapper.deleteBoard(boardNo);
    }

    @Override
    public void modifyBoard(BoardDto boardDto) throws Exception {
        boardMapper.modifyBoard(boardDto);
    }


}
