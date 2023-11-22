package com.ssafy.enjoytrip.plan.model.service;

import com.ssafy.enjoytrip.plan.model.PlanContentDto;
import com.ssafy.enjoytrip.plan.model.PlanMetaDto;
import com.ssafy.enjoytrip.plan.model.PlanMetaListDto;

import java.util.List;
import java.util.Map;

public interface PlanService {
    /**
     * PlanMeta: 여행계획 정보
     * */
    PlanMetaListDto listPlanMeta(Map<String, String> param)  throws Exception;
    PlanMetaDto getPlanMetaByPlanId(Long planId)  throws Exception;
    void createPlanMeta(PlanMetaDto param)  throws Exception;
    void modifyPlanMeta(PlanMetaDto param) throws Exception;
    void deletePlanMetaById(Long planId)  throws Exception;

    /**
     * PlanContent: 여행계획 세부정보
     * */
    List<PlanContentDto> listPlanContent(Map<String, String> param)  throws Exception;
    void createPlanContent(PlanContentDto param)  throws Exception;
    void modifyPlanContent(PlanContentDto param)  throws Exception;
    void deletePlanContentById(Long planContentId) throws Exception;
}
