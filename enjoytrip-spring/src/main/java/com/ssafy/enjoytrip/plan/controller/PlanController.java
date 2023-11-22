package com.ssafy.enjoytrip.plan.controller;

import com.ssafy.enjoytrip.attraction.model.AttractionDetailDto;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.AttractionListDto;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;
import com.ssafy.enjoytrip.plan.model.PlanContentDto;
import com.ssafy.enjoytrip.plan.model.PlanMetaDto;
import com.ssafy.enjoytrip.plan.model.PlanMetaListDto;
import com.ssafy.enjoytrip.plan.model.service.PlanService;
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
@RequestMapping("/plan")
@RequiredArgsConstructor
@Api(tags = { "Plan Controller API" })
public class PlanController {
    private final PlanService ps;
    private final String success = "SUCCESS";

    /**
     * PlanMeta: 여행계획 정보
     * */

    @ApiOperation(value = "여행계획 목록", notes = "여행계획의 메타 정보를 반환한다.", response = PlanMetaListDto.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "페이지 없음"),
            @ApiResponse(code = 500, message = "서버 에러") })
    @GetMapping
    public ResponseEntity<?> listPlanMeta(@RequestParam Map<String, String> param) {
        log.info("listPlanMeta param - {}", param);
        try {
            PlanMetaListDto listDto = ps.listPlanMeta(param);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(listDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "여행계획 찾기", notes = "여행계획 ID에 해당하는 계획을 반환한다.", response = AttractionDto.class)
    @GetMapping("/{planId}")
    public ResponseEntity<?> getPlanMetaByPlanId(@PathVariable("planId") Long planId) {
        log.info("getPlanMetaByPlanId - {}", planId);
        try{
            PlanMetaDto planMetaDto = ps.getPlanMetaByPlanId(planId);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(planMetaDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "여행계획 작성", notes = "새로운 여행계획을 만든다.")
    @PostMapping
    public ResponseEntity<?> createPlanMeta(@RequestBody PlanMetaDto param) {
        log.info("createPlanMeta body - {}", param);
        try {
            ps.createPlanMeta(param);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "여행계획 정보 수정", notes = "여행계획 정보를 수정한다.")
    @PatchMapping
    public ResponseEntity<?> modifyPlanMeta(@RequestBody PlanMetaDto param) {
        log.info("modifyPlanMeta body - {}", param);
        try {
            ps.modifyPlanMeta(param);
            return ResponseEntity.ok().body(success);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "여행계획 정보 삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @DeleteMapping("/{planId}")
    public ResponseEntity<?> deletePlanMetaById(@PathVariable Long planId) {
        log.info("deletePlanMeta pathVariable - {}", planId);
        try {
            ps.deletePlanMetaById(planId);
            return ResponseEntity.ok().body(success);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    /**
     * PlanContent: 여행계획 세부정보
     * */
    @ApiOperation(value = "여행계획 세부정보목록", notes = "여행계획의 세부정보를 반환한다.", response = PlanMetaListDto.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "페이지 없음"),
            @ApiResponse(code = 500, message = "서버 에러") })
    @GetMapping("/content")
    public ResponseEntity<?> listPlanContent(@RequestParam Map<String, String> param) {
        log.info("listPlanContent param - {}", param);
        try {
            List<PlanContentDto> listDto = ps.listPlanContent(param);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(listDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "여행계획 세부정보작성", notes = "여행계획에 새부정보를 추가한다.")
    @PostMapping("/content")
    public ResponseEntity<?>  createPlanContent(@RequestBody PlanContentDto param)  {
        log.info("createPlanContent body - {}", param);
        try {
            ps.createPlanContent(param);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "여행계획 세부정보 수정", notes = "여행계획 세부정보를 수정한다.")
    @PatchMapping("/content")
    public ResponseEntity<?>  modifyPlanContent(@RequestBody PlanContentDto param)  {
        log.info("modifyPlanContent body - {}", param);
        try {
            ps.modifyPlanContent(param);
            return ResponseEntity.ok().body(success);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "여행계획 정보 삭제", notes = "여행계획 세부정보를 삭제한다.", response = String.class)
    @DeleteMapping("/content/{planId}")
    public ResponseEntity<?>  deletePlanContentById(@PathVariable Long planId)  {
        log.info("deletePlanMeta pathVariable - {}", planId);
        try {
            ps.deletePlanContentById(planId);
            return ResponseEntity.ok().body(success);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}