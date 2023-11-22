import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function listComment(postId, success, fail) {
  local.get(`qna_comment/findall/${postId}`).then(success).catch(fail);
}

function detailComment(id, success, fail) {
  local.get(`qna_comment/find/${id}`).then(success).catch(fail);
}

function modifyComment(id, params, success, fail) {
  local.patch(`qna_comment/update/${id}`, JSON.stringify(params)).then(success).catch(fail);
}

function savaComment(params, success, fail) {
  local.post(`qna_comment`, JSON.stringify(params)).then(success).catch(fail);
}

function deleteComment(id, success, fail) {
  local.delete(`qna_comment/delete/${id}`).then(success).catch(fail);
}
export { listComment, detailComment, modifyComment, savaComment, deleteComment };
