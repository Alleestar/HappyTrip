<script setup>
import { ref, onMounted, defineProps } from "vue";
import { useRouter } from "vue-router";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import VueDatePicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";
import Sortable from "sortablejs";
import { ko } from "date-fns/locale";
import {
  removePlanMeta,
  removePlanContent,
  modifyPlanMeta,
  modifyPlanContent,
  getPlanMeta,
  listPlanContent,
} from "@/api/plan.js";
import MyPlacePlanDetailItem from "@/components/MyPlace/item/MyPlacePlanDetailItem.vue";
import PlaceDetail from "@/components/Place/PlaceDetail.vue";

const props = defineProps({ id: String });

const date = ref("");
const category = ref("");
const selectAttraction = ref({});
const dateFormat = (date) => {
  try {
    const sd = date.getDate();
    const sm = date.getMonth() + 1;
    const sy = date.getFullYear();
    return `${sy}/${sm}/${sd}`;
  } catch {}
};

function onShowModal(modalAttraction, modalCategory) {
  selectAttraction.value = modalAttraction;
  category.value = modalCategory;
}

const sortableTable = ref(null);
onMounted(() => {
  const sortable = new Sortable(sortableTable.value, {
    onStart: (event) => {
      event.item.classList.add("selected");
    },
    onEnd: (event) => {
      event.item.classList.remove("selected");
      const movedPlace = places.value.splice(event.oldIndex, 1)[0];
      places.value.splice(event.newIndex, 0, movedPlace);
    },
  });
});

const title = ref("");
const color = ref("");
const totalCost = ref(0);
function searchPlan() {
  getPlanMeta(
    props.id,
    ({ data }) => {
      title.value = data.title;
      color.value = data.color;
      date.value = data.date;
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
  };

  listPlanContent(
    params,
    ({ data }) => {
      console.log(data);
      places.value = data;
      data.forEach((p) => {
        totalCost.value += p.cost;
      });
    },
    (error) => {
      console.log(error);
    }
  );
}

function mod(prev, cost, memo, place) {
  if (!prev.value) prev.value = "0";
  if (!cost.value) cost.value = "0";
  if (prev.value != cost.value) {
    totalCost.value -= parseInt(prev.value);
    totalCost.value += parseInt(cost.value);
  }

  place.value.cost = cost.value;
  place.value.memo = memo.value;
}
searchPlan();
const deleteList = ref([]);
const showDate = ref("");

function del(planContentId) {
  deleteList.value.push(planContentId);
}
function save() {
  const params = {
    planId: props.id,
    title: title.value,
    date: date.value,
  };

  modifyPlanMeta(
    params,
    ({ data }) => {
      alert("저장이 완료되었습니다.");
    },
    (error) => {
      alert("오류가 발생했습니다.");
      console.log(error);
    }
  );

  places.value.forEach((place, i) => {
    const pc = {
      planContentId: place.planContentId,
      cost: place.cost,
      memo: place.memo,
      sortingMetaInfo: i,
    };

    modifyPlanContent(
      pc,
      ({ data }) => {},
      (error) => {
        alert("오류가 발생했습니다.");
        console.log(error);
      }
    );
  });

  deleteList.value.forEach((planId) => {
    removePlanContent(
      planId,
      ({ data }) => {},
      (error) => {
        alert("오류가 발생했습니다.");
        console.log(error);
      }
    );
  });
}
const router = useRouter();
function delList() {
  removePlanMeta(
    props.id,
    ({ data }) => {
      router.push({ name: "my-place" });
    },
    (error) => {
      alert("오류가 발생했습니다.");
      console.log(error);
    }
  );
}
</script>

<template>
  <div class="row container-fluid m-1 d-flex align-content-start justify-content-center">
    <div class="col-10 mt-4">
      <div class="container p-4 border" style="display: flex">
        <div class="card my-card me-3" :style="{ backgroundColor: color }"></div>
        <input type="text" id="mp-container-title" v-model="title" />
      </div>
    </div>
    <div class="col-10 mt-4">
      <div class="container p-0" style="display: flex; justify-content: center; flex: 1">
        <div style="display: flex">
          <VueDatePicker
            style="width: 325px"
            v-model="date"
            :enable-time-picker="false"
            :format-locale="ko"
            :format="dateFormat"
            placeholder="날짜를 선택해주세요."
          ></VueDatePicker>
          <button class="btn btn-outline-dark mx-1" id="save-btn" @click="save">SAVE</button>
          <button class="btn btn-outline-dark" @click="delList">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="21"
              height="20"
              fill="currentColor"
              class="bi bi-trash-fill"
              viewBox="0 0 16 16"
            >
              <path
                d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"
              />
            </svg>
          </button>
        </div>
      </div>
    </div>
    <div class="col-10 my-4">
      <div class="container" style="display: flex; flex: 1">
        <div class="mx-1" id="map-area" style="flex: 5; height: 500px">
          <VKakaoMap
            :attractions="places"
            :selectAttraction="selectAttraction"
            @show-modal="onShowModal"
          />
          <PlaceDetail :attraction="selectAttraction" :category="category" />
        </div>
        <div style="flex: 5">
          <div class="mb-2" v-if="showDate">
            <h5
              class="heavy mb-0"
              style="display: flex; align-items: center; justify-content: center"
            >
              {{ showDate }}
            </h5>
          </div>
          <div class="mx-1" id="plan-area" style="overflow: auto; max-height: 470px">
            <table class="table table-hover text-center light">
              <colgroup>
                <col width="40%" />
                <col width="20%" />
                <col width="40%" />
                <col width="5%" />
              </colgroup>
              <thead class="table-dark">
                <tr>
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
                  @mod="mod"
                  @del="del"
                  v-if="places.length > 0"
                />
                <tr v-else>
                  <td colspan="5">장소를 등록해주세요</td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="mt-2 px-2" style="display: flex; justify-content: end">
            <div style="display: flex; align-items: center" v-if="places.length > 0">
              <span class="heavy">total:&nbsp;&nbsp;&nbsp;</span>
              <span class="medium">{{ totalCost }}</span>
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

#delete-btn,
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
  font-size: 16px;
  border: none;
  width: 100%;
}
#mp-container-content {
  font-family: "EASTARJET-Medium";
  font-size: 12px;
  text-decoration: underline;
}
</style>
