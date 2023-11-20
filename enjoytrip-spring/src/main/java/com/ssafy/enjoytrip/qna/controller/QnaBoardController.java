package com.ssafy.enjoytrip.qna.controller;

import com.ssafy.enjoytrip.qna.model.QnaDto;
import com.ssafy.enjoytrip.qna.model.QnaListDto;
import com.ssafy.enjoytrip.qna.model.service.QnaService;
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
@RequestMapping("/qna")
@RequiredArgsConstructor
@CrossOrigin(origins = { "*" })
@Api(tags = { "QnaBoard Controller API" })
public class QnaBoardController {
    private final QnaService qnaService;

    @ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다.")
    @PostMapping
    public ResponseEntity<?> writeQnA(
            @RequestBody @ApiParam(value = "게시글 정보.", required = true) QnaDto qnaDto) {
        log.info("writeQnA QnADto - {}", qnaDto);
        try {
            qnaService.writeQna(qnaDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")
    @PutMapping
    public ResponseEntity<?> modifyQnA(
            @RequestBody @ApiParam(value = "게시글 정보.", required = true) QnaDto qnaDto) {
        log.info("modifyQnA QnADto - {}", qnaDto);
        try {
            qnaService.modifyQna(qnaDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
            @ApiResponse(code = 500, message = "서버에러!!") })
    @GetMapping
    public ResponseEntity<?> listQnA(
            @RequestParam(required=false) Map<String, String> map) {

        log.info("listQnA map - {}", map);
        try {
            QnaListDto qnaListDto = qnaService.listQna(map);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(qnaListDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = QnaDto.class)
    @GetMapping("/{Qnano}")
    public ResponseEntity<QnaDto> getQnA(
            @PathVariable("Qnano") @ApiParam(value = "얻어올 글의 글번호.", required = true) int Qnano)
            throws Exception {
        log.info("getQnA - 호출 : " + Qnano);
        return new ResponseEntity<QnaDto>(qnaService.getQna(Qnano), HttpStatus.OK);
    }


    @ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @DeleteMapping("/{QnAno}")
    public ResponseEntity<String> deleteQnA(@PathVariable("QnAno") @ApiParam(value = "살제할 글의 글번호.", required = true) int QnAno) throws Exception {
        log.info("deleteQnA - 호출");
        qnaService.deleteQna(QnAno);
        return ResponseEntity.ok().build();

    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}