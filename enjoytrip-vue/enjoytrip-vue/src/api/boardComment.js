import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function listComment(postId, success, fail) {
  local.get(`board_comment/findall/${postId}`).then(success).catch(fail);
}

function detailComment(id, success, fail) {
  local.get(`board_comment/find/${id}`).then(success).catch(fail);
}

function modifyComment(id, params, success, fail) {
  local.patch(`board_comment/update/${id}`, JSON.stringify(params)).then(success).catch(fail);
}

function savaComment(params, success, fail) {
  local.post(`board_comment`, JSON.stringify(params)).then(success).catch(fail);
}

function deleteComment(id, success, fail) {
  local.delete(`board_comment/delete/${id}`).then(success).catch(fail);
}
export { listComment, detailComment, modifyComment, savaComment, deleteComment };
