import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function listPlanMeta(params, success, fail) {
  local.get("plan", { params }).then(success).catch(fail);
}

function modifyPlanMeta(planMeta, success, fail) {
  local.patch(`plan`, JSON.stringify(planMeta)).then(success).catch(fail);
}

function getPlanMeta(id, success, fail) {
  local.get(`plan/${id}`).then(success).catch(fail);
}
function removePlanMeta(planId, success, fail) {
  local.delete(`plan/${planId}`).then(success).catch(fail);
}
function createPlanMeta(planMeta, success, fail) {
  console.log(JSON.stringify(planMeta));
  local.post("plan", JSON.stringify(planMeta)).then(success).catch(fail);
}

function createPlanContent(planMeta, success, fail) {
  console.log(JSON.stringify(planMeta));
  local.post("plan/content", JSON.stringify(planMeta)).then(success).catch(fail);
}

function listPlanContent(params, success, fail) {
  local.get("plan/content", { params }).then(success).catch(fail);
}

function modifyPlanContent(planContent, success, fail) {
  local.patch(`plan/content`, JSON.stringify(planContent)).then(success).catch(fail);
}
function removePlanContent(planId, success, fail) {
  local.delete(`plan/content/${planId}`).then(success).catch(fail);
}
export { removePlanMeta, removePlanContent, modifyPlanContent, listPlanMeta, modifyPlanMeta, createPlanMeta, createPlanContent, getPlanMeta, listPlanContent };
