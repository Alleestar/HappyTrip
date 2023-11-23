package com.ssafy.enjoytrip.plan.model.service;

import com.ssafy.enjoytrip.plan.model.PlanContentDto;
import com.ssafy.enjoytrip.plan.model.PlanMetaDto;
import com.ssafy.enjoytrip.plan.model.PlanMetaListDto;
import com.ssafy.enjoytrip.plan.model.mapper.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{

    private final PlanMapper pm;
    private final Random random;

    private String makeRandomColor(){
        return "#"+String.format("%06X", random.nextInt(Integer.parseInt("FFFFFF", 16)+1));
    }

    @Override
    @Transactional(readOnly = true)
    public PlanMetaListDto listPlanMeta(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "7" : map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;
        String word = map.get("word") == null ? "" : map.get("word");

        param.put("start", start);
        param.put("listsize", sizePerPage);
        param.put("word", word);
        param.put("id", map.get("userId"));

        List<PlanMetaDto> list = pm.listPlanMeta(param);
        int totalPlanCount = pm.getTotalPlanMeta(param);
        int totalPageCount = (totalPlanCount - 1) / sizePerPage + 1;
        PlanMetaListDto planMetaListDto = new PlanMetaListDto();
        planMetaListDto.setPlans(list);
        planMetaListDto.setCurrentPage(currentPage);
        planMetaListDto.setTotalPageCount(totalPageCount);

        return planMetaListDto;
    }

    @Override
    @Transactional(readOnly = true)
    public PlanMetaDto getPlanMetaByPlanId(Long planId) throws Exception {
        return pm.getPlanMetaByPlanId(planId);
    }

    @Override
    @Transactional
    public void createPlanMeta(PlanMetaDto param) throws Exception {
        param.setColor(makeRandomColor());
        pm.createPlanMeta(param);
    }

    @Override
    @Transactional
    public void modifyPlanMeta(PlanMetaDto param) throws Exception {
        pm.modifyPlanMeta(param);
    }

    @Override
    @Transactional
    public void deletePlanMetaById(Long planId) throws Exception {
        pm.deletePlanMetaById(planId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlanContentDto> listPlanContent(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        if(map.get("planId") == null){
            throw new RuntimeException("Send planId ");
        }
        long planId = Long.parseLong(map.get("planId"));
        param.put("planId", planId);

        return pm.listPlanContent(param);
    }

    @Override
    @Transactional
    public void createPlanContent(PlanContentDto param) throws Exception {
        pm.createPlanContent(param);
    }

    @Override
    @Transactional
    public void modifyPlanContent(PlanContentDto param) throws Exception {
        pm.modifyPlanContent(param);
    }

    @Override
    @Transactional
    public void deletePlanContentById(Long planContentId) throws Exception {
        pm.deletePlanContentById(planContentId);
    }
}
