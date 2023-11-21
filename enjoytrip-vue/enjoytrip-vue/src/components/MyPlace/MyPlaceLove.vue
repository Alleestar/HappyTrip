<script setup>
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import VueDatePicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";
import Sortable from "sortablejs";
import { ko } from "date-fns/locale";

const dates = ref([]);
const route = useRoute();
const router = useRouter();

const attractions = ref([]);
const selectAttraction = ref({});
const dateFormat = (dates) => {
  try {
    const sd = dates[0].getDate();
    const sm = dates[0].getMonth() + 1;
    const sy = dates[0].getFullYear();

    const ed = dates[1].getDate();
    const em = dates[1].getMonth() + 1;
    const ey = dates[1].getFullYear();
    return `${sy}/${sm}/${sd} -  ${ey}/${em}/${ed}`;
  } catch {}
};

function onShowModal() {
  console.log("hi");
}

const sortableTable = ref(null);
onMounted(() => {
  const sortable = new Sortable(sortableTable.value, {
    onStart: (event) => {
      event.item.classList.add("selected");
    },
    onEnd: (event) => {
      event.item.classList.remove("selected");
    },
  });
});
</script>

<template>
  <div class="row container-fluid m-1 d-flex align-content-start justify-content-center">
    <div class="col-10 mt-4">
      <div class="container p-4 border" style="display: flex">
        <div class="card me-3" style="background-color: blue; border: none; width: 32px"></div>
        <h5 id="mp-container-title">[[My Place: 좋아하는 여행지]]</h5>
      </div>
    </div>

    <div class="col-10 mt-4">
      <div class="container" style="display: flex">
        <div class="mx-1" id="map-area" style="flex: 5; width: 500px; height: 500px">
          <VKakaoMap
            :attractions="attractions"
            :selectAttraction="selectAttraction"
            @show-modal="onShowModal"
          />
        </div>
        <div
          class="mx-1"
          id="plan-area"
          style="flex: 5; overflow: auto; max-width: 500px; max-height: 470px"
        >
          <div>
            <table class="table table-hover text-center light">
              <colgroup>
                <col width="95%" />
                <col width="5%" />
              </colgroup>
              <thead class="table-dark">
                <tr>
                  <th>장소</th>
                  <th></th>
                </tr>
              </thead>
              <tbody ref="sortableTable">
                <tr>
                  <td>장소명1</td>
                  <td>
                    <button class="btn loc-day-btn">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="21"
                        height="20"
                        fill="black"
                        class="bi bi-trash3-fill"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"
                        />
                      </svg>
                    </button>
                  </td>
                </tr>
                <tr>
                  <td>장소명1</td>
                  <td>
                    <button class="btn loc-day-btn">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="21"
                        height="20"
                        fill="black"
                        class="bi bi-trash3-fill"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"
                        />
                      </svg>
                    </button>
                  </td>
                </tr>
                <tr>
                  <td>장소명1</td>
                  <td>
                    <button class="btn loc-day-btn">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="21"
                        height="20"
                        fill="black"
                        class="bi bi-trash3-fill"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"
                        />
                      </svg>
                    </button>
                  </td>
                </tr>
                <tr>
                  <td>장소명1</td>
                  <td>
                    <button class="btn loc-day-btn">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="21"
                        height="20"
                        fill="black"
                        class="bi bi-trash3-fill"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"
                        />
                      </svg>
                    </button>
                  </td>
                </tr>
                <tr>
                  <td>장소명1</td>
                  <td>
                    <button class="btn loc-day-btn">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="21"
                        height="20"
                        fill="black"
                        class="bi bi-trash3-fill"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"
                        />
                      </svg>
                    </button>
                  </td>
                </tr>
                <tr>
                  <td>장소명1</td>
                  <td>
                    <button class="btn loc-day-btn">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="21"
                        height="20"
                        fill="black"
                        class="bi bi-trash3-fill"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"
                        />
                      </svg>
                    </button>
                  </td>
                </tr>
                <tr>
                  <td>장소명1</td>
                  <td>
                    <button class="btn loc-day-btn">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="21"
                        height="20"
                        fill="black"
                        class="bi bi-trash3-fill"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"
                        />
                      </svg>
                    </button>
                  </td>
                </tr>
                <tr>
                  <td>장소명1</td>
                  <td>
                    <button class="btn loc-day-btn">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="21"
                        height="20"
                        fill="black"
                        class="bi bi-trash3-fill"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"
                        />
                      </svg>
                    </button>
                  </td>
                </tr>
                <tr>
                  <td>장소명1</td>
                  <td>
                    <button class="btn loc-day-btn">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="21"
                        height="20"
                        fill="black"
                        class="bi bi-trash3-fill"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"
                        />
                      </svg>
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="mt-2 px-2" style="display: flex; justify-content: space-between">
            <button class="btn btn-dark mx-1" id="save-btn">SAVE</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@font-face {
  font-family: "EASTARJET-Heavy";
  src: url("/fonts/EASTARJET-Heavy.ttf");
}

@font-face {
  font-family: "EASTARJET-Medium";
  src: url("/fonts/EASTARJET-Medium.ttf");
}

@font-face {
  font-family: "EASTARJET-DemiLight";
  src: url("/fonts/EASTARJET-DemiLight.ttf");
}

#save-btn {
  font-family: "EASTARJET-Heavy";
  font-size: 12px;
}

.loc-day-btn {
  width: 100%;
  height: 100%;
  font-size: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.day-btn {
  width: 75px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.table-input-text {
  border: none;
  width: 100%;
}

td {
  font-family: "EASTARJET-Medium";
  font-size: 15px;
}

#container-title {
  font-family: "EASTARJET-Heavy";
  font-size: 20px;
}

#list-btn,
#modify-btn,
#delete-btn {
  font-family: "EASTARJET-Heavy";
  font-size: 12px;
}

.heavy {
  font-family: "EASTARJET-Heavy";
}

.medium {
  font-family: "EASTARJET-Medium";
}

.light {
  font-family: "EASTARJET-DemiLight";
}

.comment-font {
  font-size: 10px;
}

.meta-info {
  font-size: 8px;
}

.comment-nickname {
  font-size: 10px;
}

.container-comment {
  background-color: #f0f0f0;
  padding: 0;
}

.content-font {
  font-size: 12px;
}

#comment-img {
  height: 12px;
  width: 12px;
}

#comment-input {
  font-family: "EASTARJET-DemiLight";
  font-size: 12px;
  border: none;
  border-radius: 0;
  background-color: #f0f0f0;
}

#mp-container-title {
  font-family: "EASTARJET-Heavy";
}
#mp-container-content {
  font-family: "EASTARJET-Medium";
  font-size: 12px;
  text-decoration: underline;
}
</style>
