import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function listQna(params, success, fail) {
  local.get("qna", { params }).then(success).catch(fail);
}

function detailQna(qno, success, fail) {
  local.get(`qna/${qno}`).then(success).catch(fail);
}

function modifyQna(qna, success, fail) {
  local.put(`qna`, JSON.stringify(qna)).then(success).catch(fail);
}

function registQna(qna, success, fail) {
  local.post(`qna`, JSON.stringify(qna)).then(success).catch(fail);
}

function removeQna(qna, success, fail) {
  local.delete(`qna/${qna}`).then(success).catch(fail);
}
export { listQna, detailQna, registQna, removeQna, modifyQna };
