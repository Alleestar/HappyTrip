<script setup>
import { ref, watch } from "vue";
import { describeAttraction } from "@/api/attraction.js";
import PlacePlanListItem from "@/components/Place/item/PlacePlanListItem.vue";
import { listPlanMeta, createPlanMeta } from "@/api/plan.js";

const attr = ref({});
const props = defineProps({ attraction: Object, category: String });
const desc = ref("");
const available = ref("false");
const planList = ref([]);
const isPlaneOpen = ref(false);

watch(props, () => {
  attr.value = {};
  desc.value = "";
  isPlaneOpen.value = false;
  available.value = true;
  attr.value = props.attraction;
  const params = {
    aid: props.attraction.contentId,
  };
  getDesc(params);
});

const getDesc = (params) => {
  describeAttraction(
    params,
    ({ data }) => {
      desc.value = data.detail;
    },
    (error) => {
      console.log(error);
    }
  );
};

function resetModal() {
  attr.value = {};
  desc.value = "";
  isPlaneOpen.value = false;
}

// function setPlane(color) {
//   currentPlaneColor.value = color;
//   closePlane();
// }

function planeBtn() {
  isPlaneOpen.value = !isPlaneOpen.value;
}

function makeNewPlanList() {
  const planMeta = {
    userId: 1,
  };
  createPlanMeta(
    planMeta,
    ({ data }) => {
      loadPlanList();
    },
    (error) => {
      console.log("error");
    }
  );
}
loadPlanList();
function loadPlanList() {
  const planMeta = {
    userId: 1,
  };
  listPlanMeta(
    planMeta,
    ({ data }) => {
      planList.value = data.plans;
    },
    (error) => {
      console.log("error");
    }
  );
}

function getToday() {
  let today = new Date();
  let year = today.getFullYear();
  let month = ("0" + (today.getMonth() + 1)).slice(-2);
  let day = ("0" + today.getDate()).slice(-2);

  let dateString = year + "/" + month + "/" + day;
  return dateString;
}
</script>

<template>
  <div class="modal" id="placeDetail">
    <div class="modal-dialog">
      <div class="modal-content" v-if="available">
        <div class="modal-header p-0 mt-4 mx-4" style="display: flex; flex-direction: column">
          <div class="img-area" style="display: flex; align-items: center" v-if="attr.img1">
            <div class="img-shape mb-3">
              <img style="width: 100%" :src="attr.img1" />
            </div>
          </div>
          <div class="info-area">
            <div>
              <h4 class="medium my-0 me-2" style="font-size: 22px">{{ attr.title }}</h4>
            </div>
            <div class="d-flex align-items-end">
              <p class="light m-0" style="font-size: 13px">{{ category }}</p>
            </div>
          </div>
        </div>
        <div class="modal-body detail-area">
          <p class="light" style="margin-bottom: 0px" v-if="desc">
            {{ desc }}
          </p>
          <p class="light" style="margin-bottom: 0px" v-else>장소에 대한 설명이 없습니다.</p>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer" style="display: flex; justify-content: space-between">
          <div id="list-area">
            <div class="dropdown">
              <button
                class="btn btn-plane dropdown-toggle"
                @click="planeBtn"
                data-bs-toggle="dropdown"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="21"
                  height="20"
                  fill="gray"
                  class="bi bi-airplane-fill"
                  viewBox="0 0 16 16"
                >
                  <path
                    d="M6.428 1.151C6.708.591 7.213 0 8 0s1.292.592 1.572 1.151C9.861 1.73 10 2.431 10 3v3.691l5.17 2.585a1.5 1.5 0 0 1 .83 1.342V12a.5.5 0 0 1-.582.493l-5.507-.918-.375 2.253 1.318 1.318A.5.5 0 0 1 10.5 16h-5a.5.5 0 0 1-.354-.854l1.319-1.318-.376-2.253-5.507.918A.5.5 0 0 1 0 12v-1.382a1.5 1.5 0 0 1 .83-1.342L6 6.691V3c0-.568.14-1.271.428-1.849Z"
                  />
                </svg>
              </button>
              <ul
                v-if="isPlaneOpen"
                class="dropdown-menu px-2"
                style="display: flex; justify-content: start"
              >
                <PlacePlanListItem
                  v-for="plan in planList"
                  :key="plan.planId"
                  :plan="plan"
                  :attraction="attraction"
                />
                <li class="mx-1">
                  <a class="ball" @click="makeNewPlanList">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="21"
                      height="20"
                      fill="gray"
                      class="bi bi-plus-circle"
                      viewBox="0 0 16 16"
                    >
                      <path
                        d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"
                      />
                      <path
                        d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"
                      />
                    </svg>
                  </a>
                </li>
              </ul>
            </div>
          </div>

          <button class="btn-close" data-bs-dismiss="modal" @click="resetModal"></button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.ball {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
}

.modal-dialog {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

/* @font-face {
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
} */

#list-area {
  display: flex;
}

.detail-area {
  max-height: 200px;
  overflow-y: scroll;
}

.img-shape {
  overflow: hidden;
  width: 250px;
  height: 250px;
  display: flex;
  align-items: center;
}

.info-area {
  display: flex;
  margin-bottom: 10px;
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

.btn-heart {
  display: flex;
  align-items: center;
  color: gray;
  margin: 4px;
  padding: 4px;
}

.btn-plane {
  display: flex;
  align-items: center;
  color: gray;
  margin: 4px;
  padding: 4px;
}
</style>
