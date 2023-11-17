import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function listAttraction(params, success, fail) {
  local.get("attraction", { params }).then(success).catch(fail);
}

function describeAttraction(params, success, fail) {
  local.get(`attraction/place/describe`, { params }).then(success).catch(fail);
}

function getAddr(success, fail) {
  local.get(`attraction/addr`).then(success).catch(fail);
}

export { listAttraction, describeAttraction, getAddr };
