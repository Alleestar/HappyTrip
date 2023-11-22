import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function listPlanMeta(params, success, fail) {
  local.get("plan", { params }).then(success).catch(fail);
}

function getPlanMeta(id, success, fail) {
  console.log(id);
  local.get(`plan/${id}`).then(success).catch(fail);
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

export { listPlanMeta, createPlanMeta, createPlanContent, getPlanMeta, listPlanContent };
