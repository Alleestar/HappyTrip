<script setup>
import { ref } from "vue";
import PageNavigation from "@/components/common/PageNavigation.vue";
import { useRouter } from "vue-router";
import MyPlacePlanListItem from "@/components/MyPlace/item/MyPlacePlanListItem.vue";
import { listPlanMeta, createPlanMeta } from "@/api/plan.js";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
const memberStore = useMemberStore();
const { isLogin, userInfo } = storeToRefs(memberStore);
// 화면 이동
const router = useRouter();
function goToPlan() {
  router.push({ name: "mp-plan" });
}

// 페이지 Change
const currentPage = ref(1);
const totalPage = ref(1);
const word = ref("");

const searchedPlanRow1 = ref([]);
const searchedPlanRow2 = ref([]);

const params = {
  pgno: 1,
  spp: import.meta.env.VITE_SAVED_TRIP_LIST_SIZE,
  userId: userInfo.value.userId,
  word: word.value,
};

function searchPlan() {
  params.word = word.value;
  listPlanMeta(
    params,
    ({ data }) => {
      searchedPlanRow1.value = data.plans.slice(0, 4);
      searchedPlanRow2.value = data.plans.slice(4);

      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
      params.pgno = totalPage.value;
    },
    (error) => {
      console.log(error);
      params.pgno = 1;
    }
  );
}

function onPageChange(value) {
  params.pgno = value;
  searchPlan();
}

function makeNewPlanList() {
  const planMeta = {
    userId: userInfo.value.userId,
  };
  createPlanMeta(
    planMeta,
    ({ data }) => {
      searchPlan();
    },
    (error) => {
      console.log("error");
    }
  );
}

searchPlan();
</script>

<template>
  <div>
    <div class="row">
      <div class="d-flex flex-row justify-content-center">
        <div class="col-6 m-1">
          <input
            placeholder="제목을 입력하세요."
            class="form-control input"
            name="search"
            id="search-input-text"
            type="text"
            v-model="word"
            @keyup.enter="searchPlan"
          />
        </div>
        <div class="col-1 m-1">
          <button class="btn btn-dark" id="search-btn" @click="searchPlan">검색</button>
        </div>
      </div>
    </div>
  </div>
  <div v-if="searchedPlanRow1.length >= 0">
    <div class="container px-0 d-flex flex-column justify-content-center">
      <!-- <div style="display: flex; justify-content: start">
          <div class="d-flex justify-content-start mx-1 mb-2">
            <button class="btn btn-outline-dark" id="write-btn" @click="goToMakeList">
              리스트 추가
            </button>
          </div>
        </div> -->

      <table class="my-1" id="card-area">
        <tr class="my-4">
          <MyPlacePlanListItem v-for="plan in searchedPlanRow1" :key="plan.planId" :plan="plan">
          </MyPlacePlanListItem>
          <td v-if="searchedPlanRow1.length < 4">
            <button
              class="card"
              style="
                display: flex;
                justify-content: center;
                align-items: center;
                width: 200px;
                height: 200px;
                background-color: white;
                text-decoration: none;
              "
              @click="makeNewPlanList"
            >
              <div class="card-body p-0 d-flex justify-content-center align-items-center">
                <h4 class="card-title m-0 medium" style="font-size: 60px">+</h4>
              </div>
            </button>
          </td>
          <td
            v-for="num in 3 - searchedPlanRow1.length"
            :key="num"
            v-if="searchedPlanRow1.length < 4"
          ></td>
        </tr>
        <tr class="my-4" v-if="searchedPlanRow1.length == 4 || searchedPlanRow2.length > 0">
          <MyPlacePlanListItem v-for="plan in searchedPlanRow2" :key="plan.planId" :plan="plan">
          </MyPlacePlanListItem>
          <td>
            <button
              class="card"
              style="
                display: flex;
                justify-content: center;
                align-items: center;
                width: 200px;
                height: 200px;
                background-color: white;
                text-decoration: none;
              "
              @click="makeNewPlanList"
            >
              <div class="card-body p-0 d-flex justify-content-center align-items-center">
                <h4 class="card-title m-0 medium" style="font-size: 60px">+</h4>
              </div>
            </button>
          </td>
          <td
            v-for="num in 3 - searchedPlanRow2.length"
            :key="num"
            v-if="searchedPlanRow2.length < 3"
          ></td>
        </tr>
      </table>

      <!-- <QnAListItem
          v-for="question in questions"
          :key="question.qnaNo"
          :question="question"
        ></QnAListItem> -->
      <PageNavigation
        :currentPage="currentPage"
        :totalPage="totalPage"
        @page-change="onPageChange"
      ></PageNavigation>
    </div>
  </div>
  <div v-else>
    <div
      class="container border p-4 d-flex flex-column justify-content-center"
      id="container-no-content"
    >
      <h5 id="container-no-content-subject" class="text-center my-1">등록된 리스트가 없습니다.</h5>
      <!-- <p id="container-no-content-content" class="text-center text-secondary my-1">
          첫 글을 작성해보세요.
        </p> -->
      <div class="d-flex justify-content-center my-4">
        <button class="btn btn-dark mx-1" id="no-content-write-btn" @click="goToMap">
          여행지 찾으러가기
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
#list {
  flex: 1;
}
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

tr {
  display: flex;
  flex: 1;
}

.fixed-col {
  position: sticky;
  left: 0;
}

td {
  flex: 2.5;
  display: flex;
  justify-content: center;
}

select {
  font-family: "EASTARJET-Medium";
  font-size: 10px;
}

#register-button-img {
  height: 15px;
}

#register-btn {
  /* height: 31px; */
  width: 31px;
}

#write-btn {
  font-family: "EASTARJET-Heavy";
  font-size: 12px;
}

#search-btn,
#no-content-write-btn {
  font-family: "EASTARJET-Heavy";
  font-size: 12px;
  /* min-width: 48px; */
  /* max-height: 32px; */
}

#search-input-text {
  font-family: "EASTARJET-DemiLight";
  font-size: 10px;
}

#mp-container-title {
  font-family: "EASTARJET-Heavy";
}

#container-no-content-subject {
  font-family: "EASTARJET-Heavy";
  font-size: 24px;
}

#container-no-content-content {
  font-family: "EASTARJET-Medium";
  font-size: 18px;
}

#mp-container-content {
  font-family: "EASTARJET-Medium";
  font-size: 12px;
  text-decoration: underline;
}

#container-no-content {
  height: 500px;
}

.list-title {
  font-family: "EASTARJET-Medium";
  color: white;
}
</style>
