<script setup>
import { ref, onMounted, defineProps } from "vue";
import { useRouter, useRoute } from "vue-router";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import VueDatePicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";
import Sortable from "sortablejs";
import { ko } from "date-fns/locale";
import { getPlanMeta, listPlanContent } from "@/api/plan.js";
import MyPlacePlanDetailItem from "@/components/MyPlace/item/MyPlacePlanDetailItem.vue";

const props = defineProps({ id: String });

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
const title = ref("");
const color = ref("");
const date = ref("");
function searchPlan() {
  getPlanMeta(
    props.id,
    ({ data }) => {
      date.value = data.startDate;
      title.value = data.title;
      color.value = data.color;
      searchPlaces();
    },
    (error) => {
      console.log(error);
    }
  );
}

const places = ref([]);
function searchPlaces() {
  const params = {
    planId: props.id,
    date: date.value,
  };

  listPlanContent(
    params,
    ({ data }) => {
      console.log(data);
      places.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
}

searchPlan();
</script>

<template>
  <div class="row container-fluid m-1 d-flex align-content-start justify-content-center">
    <div class="col-10 mt-4">
      <div class="container p-4 border" style="display: flex">
        <div class="card my-card me-3" :style="{ backgroundColor: color }"></div>
        <h5 id="mp-container-title">{{ title }}</h5>
      </div>
    </div>
    <div class="col-10 mt-4">
      <div class="container p-0" style="display: flex; justify-content: space-between; flex: 1">
        <button class="btn day-btn">◀</button>
        <div style="display: flex; flex-direction: column">
          <VueDatePicker
            style="width: 325px"
            v-model="dates"
            range
            :enable-time-picker="false"
            :format-locale="ko"
            :format="dateFormat"
            placeholder="시작일, 종료일을 선택해주세요."
          ></VueDatePicker>

          <h5
            class="heavy mt-3 mb-0"
            style="display: flex; align-items: center; justify-content: center"
          >
            2023.11.21
          </h5>
        </div>

        <button class="btn day-btn">▶</button>
      </div>
    </div>
    <div class="col-10 my-4">
      <div class="container" style="display: flex; flex: 1">
        <div class="mx-1" id="map-area" style="flex: 5; height: 500px">
          <VKakaoMap
            :attractions="attractions"
            :selectAttraction="selectAttraction"
            @show-modal="onShowModal"
          />
        </div>
        <div style="flex: 5">
          <div class="mx-1" id="plan-area" style="overflow: auto; max-height: 470px">
            <table class="table table-hover text-center light">
              <colgroup>
                <col width="5%" />
                <col width="30%" />
                <col width="20%" />
                <col width="40%" />
                <col width="5%" />
              </colgroup>
              <thead class="table-dark">
                <tr>
                  <th></th>
                  <th>장소</th>
                  <th>비용</th>
                  <th>메모</th>
                  <th></th>
                </tr>
              </thead>
              <tbody ref="sortableTable">
                <MyPlacePlanDetailItem
                  v-for="place in places"
                  :key="place.planContentId"
                  :place="place"
                  v-if="places.length > 0"
                />
                <tr v-else>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="mt-2 px-2" style="display: flex; justify-content: space-between">
            <button class="btn btn-dark mx-1" id="save-btn">SAVE</button>
            <div style="display: flex; align-items: center">
              <span class="heavy">total:&nbsp;&nbsp;&nbsp;</span>
              <span class="medium">50000</span>
              <span class="heavy"> <b>&nbsp;₩</b></span>
            </div>
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
.my-card {
  border: none;
  width: 32px;
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
