package com.ssafy.enjoytrip.attraction.controller;

import com.ssafy.enjoytrip.attraction.model.AddrDto;
import com.ssafy.enjoytrip.attraction.model.AttractionDetailDto;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.AttractionListDto;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;
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
@RequestMapping("/attraction")
@RequiredArgsConstructor
@CrossOrigin(origins = { "*" })
@Api(tags = { "Attraction Controller API" })
public class AttractionController {
    private final AttractionService as;

    @ApiOperation(value = "여행지 목록", notes = "모든 여행지의 정보를 반환한다.", response = AttractionListDto.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "페이지 없음"),
            @ApiResponse(code = 500, message = "서버 에러") })
    @GetMapping
    public ResponseEntity<?> listAttraction(
            @RequestParam(required=false) Map<String, String> map) {

        log.info("listAttraction map - {}", map);
        try {
            AttractionListDto listDto = as.listAttraction(map);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(listDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "여행지 찾기", notes = "글번호에 해당하는 장소의 설명을 반환한다.", response = AttractionDto.class)
    @GetMapping("/place")
    public ResponseEntity<?> getAttractionDetail(@ApiParam(value = "여행지의 아이디 입력", required = true)
                                                      @RequestParam String aid) {
        log.info("getAttractionDetail - {}", aid);
        try{
            AttractionDetailDto attractionDto = as.getAttractionDetail(aid);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(attractionDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "주소정보 가져오기", notes = "dropbox의 주소 정보를 가져온다.", response = AttractionDto.class)
    @GetMapping("/addr")
    public ResponseEntity<?> getAddr() {
        log.info("getAddr");
        try{
            List<AddrDto> addrDto = as.getAddr();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(addrDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}