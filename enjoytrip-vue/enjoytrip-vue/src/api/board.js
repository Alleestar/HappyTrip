import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function listBoard(params, success, fail) {
  local.get("board", { params }).then(success).catch(fail);
}

function detailBoard(board, success, fail) {
  local.get(`board/${board}`).then(success).catch(fail);
}

function modifyBoard(board, success, fail) {
  local.put(`board`, JSON.stringify(board)).then(success).catch(fail);
}

function registBoard(board, success, fail) {
  local.post(`board`, JSON.stringify(board)).then(success).catch(fail);
}

function removeBoard(board, success, fail) {
  local.delete(`board/${board}`).then(success).catch(fail);
}
export { listBoard, detailBoard, registBoard, removeBoard, modifyBoard };
