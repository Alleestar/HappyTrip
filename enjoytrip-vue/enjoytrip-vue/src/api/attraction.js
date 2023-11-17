import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function listAttraction(params, success, fail) {
  local.get("attraction", { params }).then(success).catch(fail);
}

function detailAttraction(params, success, fail) {
  local.get(`attraction/place`, { params }).then(success).catch(fail);
}

function getAddr(params, success, fail) {
  local.get(`attraction/addr`).then(success).catch(fail);
}

export { listAttraction, detailAttraction, getAddr };
