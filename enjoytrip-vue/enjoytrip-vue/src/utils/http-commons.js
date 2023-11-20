import axios from "axios";
const { VITE_API_BASE_URL, VITE_TRIP_URL } = import.meta.env;

function localAxios() {
  const instance = axios.create({
    baseURL: VITE_API_BASE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function publicAxios() {
  const instance = axios.create({
    baseURL: VITE_TRIP_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

export { localAxios, publicAxios };
