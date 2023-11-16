package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardListDto;
import com.ssafy.enjoytrip.board.model.service.BoardService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
@CrossOrigin(origins = { "*" })
@Api(tags = { "Board Controller API" })
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다.")
    @PostMapping
    public ResponseEntity<?> writeBoard(@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) {
        log.info("writeBoard BoardDto - {}", boardDto);
        try {
            boardService.writeBoard(boardDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")
    @PutMapping
    public ResponseEntity<?> modifyBoard(@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) {
        log.info("modifyBoard BoardDto - {}", boardDto);
        try {
            boardService.modifyBoard(boardDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
            @ApiResponse(code = 500, message = "서버에러!!") })
    @GetMapping
    public ResponseEntity<?> listBoard(
            @RequestParam(required=false) Map<String, String> map) {

        log.info("listBoard map - {}", map);
        try {
            BoardListDto boardListDto = boardService.listBoard(map);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(boardListDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
    @GetMapping("/{Boardno}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable("Boardno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int Boardno)
            throws Exception {
        log.info("getBoard - 호출 : " + Boardno);
        return new ResponseEntity<BoardDto>(boardService.getBoard(Boardno), HttpStatus.OK);
    }


    @ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @DeleteMapping("/{Boardno}")
    public ResponseEntity<String> deleteBoard(@PathVariable("Boardno") @ApiParam(value = "살제할 글의 글번호.", required = true) int Boardno) throws Exception {
        log.info("deleteBoard - 호출");
        boardService.deleteBoard(Boardno);
        return ResponseEntity.ok().build();

    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}