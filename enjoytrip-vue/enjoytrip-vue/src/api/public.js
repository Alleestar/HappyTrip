import { publicAxios } from "@/utils/http-commons";

const publicAPI = publicAxios();

function getCategory(params, success, fail) {
  publicAPI.get(`categoryCode1`, { params: params }).then(success).catch(fail);
}

export { getCategory };
