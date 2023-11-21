<script setup>
import { ref } from "vue";
import PageNavigation from "@/components/common/PageNavigation.vue";
import QnAListItem from "@/components/QnA/item/QnAListItem.vue";
import { useRouter } from "vue-router";
import { listQna } from "@/api/qna.js";

const questions = ref([]);

// 화면 이동
const router = useRouter();
function goToMap() {
  router.push({ name: "place" });
}

// 페이지 Change
const currentPage = ref(1);
const totalPage = ref(1);
const key = ref("");
const word = ref("");

const params = {
  pgno: 1,
  spp: import.meta.env.VITE_ARTICLE_LIST_SIZE,
  key: key.value,
  word: word.value,
};

const searchedList = ref([]);
function searchList() {
  params.key = key.value;
  params.word = word.value;
  // listQna(
  //   params,
  //   ({ data }) => {
  //     questions.value = data.qnas;
  //     currentPage.value = data.currentPage;
  //     totalPage.value = data.totalPageCount;
  //   },
  //   (error) => {
  //     console.log(error);
  //   }
  // );
}

function refresh() {
  key.value = "";
  word.value = "";
  params.key = "";
  params.word = "";
  params.pgno = 1;
  // listQna(
  //   params,
  //   ({ data }) => {
  //     questions.value = data.qnas;
  //     currentPage.value = data.currentPage;
  //     totalPage.value = data.totalPageCount;
  //   },
  //   (error) => {
  //     console.log(error);
  //   }
  // );
}

function onPageChange(value) {
  params.pgno = value;
  searchList();
}

searchList();
</script>

<template>
  <!-- class="row container-fluid m-1 d-flex align-items-start flex-column" -->
  <div id="list">
    <div class="col-10 my-1 mx-auto">
      <div class="container p-4 border">
        <h5 id="mp-container-title">My Place</h5>
        <p id="mp-container-content" class="text-secondary">저장한 여행지를 볼 수 있어요</p>
      </div>
    </div>
    <div class="col-8 my-4 mx-auto">
      <ul class="nav nav-tabs">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="21"
              height="20"
              fill="dark"
              class="bi bi-heart"
              viewBox="0 0 16 16"
            >
              <path
                fill-rule="evenodd"
                d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"
              />
            </svg>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#"
            ><svg
              xmlns="http://www.w3.org/2000/svg"
              width="21"
              height="20"
              fill="dark"
              class="bi bi-airplane-fill"
              viewBox="0 0 16 16"
            >
              <path
                d="M6.428 1.151C6.708.591 7.213 0 8 0s1.292.592 1.572 1.151C9.861 1.73 10 2.431 10 3v3.691l5.17 2.585a1.5 1.5 0 0 1 .83 1.342V12a.5.5 0 0 1-.582.493l-5.507-.918-.375 2.253 1.318 1.318A.5.5 0 0 1 10.5 16h-5a.5.5 0 0 1-.354-.854l1.319-1.318-.376-2.253-5.507.918A.5.5 0 0 1 0 12v-1.382a1.5 1.5 0 0 1 .83-1.342L6 6.691V3c0-.568.14-1.271.428-1.849Z"
              />
            </svg>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="21"
              height="20"
              fill="dark"
              class="bi bi-pencil-square"
              viewBox="0 0 16 16"
            >
              <path
                d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"
              />
              <path
                fill-rule="evenodd"
                d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"
              />
            </svg>
          </a>
        </li>
      </ul>
    </div>
    <div class="col-10 my-2 mx-auto">
      <div class="row">
        <div class="d-flex flex-row justify-content-center">
          <div class="col-1 m-1">
            <select v-model="key" class="form-select">
              <option disabled value="">검색조건</option>
              <option value="tc">제목+내용</option>
              <option value="user_nickname">작성자</option>
            </select>
          </div>
          <div class="col-6 m-1">
            <input
              placeholder="질문을 검색하세요."
              class="form-control input"
              name="search"
              id="search-input-text"
              type="text"
              v-model="word"
              @keyup.enter="searchList"
            />
          </div>
          <div class="col-1 m-1">
            <button class="btn btn-dark" id="search-btn" @click="searchList">검색</button>
          </div>
        </div>
      </div>
    </div>
    <div class="col-8 my-1 mx-auto" v-if="searchedList.length >= 0">
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
            <td>
              <router-link
                class="card"
                style="width: 200px; height: 200px; background-color: black; text-decoration: none"
                :to="{ name: 'mp-plan', query: { mpid: 0 } }"
              >
                <div class="card-body p-0 d-flex justify-content-center align-items-center">
                  <h4 class="card-title m-0 heavy list-title">Plane</h4>
                </div>
              </router-link>
            </td>
            <td>
              <router-link
                class="card"
                style="width: 200px; height: 200px; background-color: black; text-decoration: none"
                :to="{ name: 'mp-love', query: { mpid: 0 } }"
              >
                <div class="card-body p-0 d-flex justify-content-center align-items-center">
                  <h4 class="card-title m-0 heavy list-title">하트</h4>
                </div>
              </router-link>
            </td>
            <td>
              <div class="card" style="width: 200px; height: 200px; background-color: black">
                <div class="card-body p-0 d-flex justify-content-center align-items-center">
                  <h4 class="card-title m-0 heavy list-title">John Doe</h4>
                </div>
              </div>
            </td>
            <td>
              <div class="card" style="width: 200px; height: 200px; background-color: black">
                <div class="card-body p-0 d-flex justify-content-center align-items-center">
                  <h4 class="card-title m-0 heavy list-title">John Doe</h4>
                </div>
              </div>
            </td>
          </tr>
          <tr class="my-4">
            <td>
              <div class="card" style="width: 200px; height: 200px; background-color: black">
                <div class="card-body p-0 d-flex justify-content-center align-items-center">
                  <h4 class="card-title m-0 heavy list-title">John Doe</h4>
                </div>
              </div>
            </td>
            <td>
              <div class="card" style="width: 200px; height: 200px; background-color: black">
                <div class="card-body p-0 d-flex justify-content-center align-items-center">
                  <h4 class="card-title m-0 heavy list-title">John Doe</h4>
                </div>
              </div>
            </td>
            <td>
              <div class="card" style="width: 200px; height: 200px; background-color: black">
                <div class="card-body p-0 d-flex justify-content-center align-items-center">
                  <h4 class="card-title m-0 heavy list-title">John Doe</h4>
                </div>
              </div>
            </td>
            <td>
              <div class="card" style="width: 200px; height: 200px; background-color: black">
                <div class="card-body p-0 d-flex justify-content-center align-items-center">
                  <h4 class="card-title m-0 heavy list-title">John Doe</h4>
                </div>
              </div>
            </td>
          </tr>
        </table>

        <!-- <QnAListItem
          v-for="question in questions"
          :key="question.qnaNo"
          :question="question"
        ></QnAListItem> -->
        <PageNavigation
          :currentPage="currentPage"
          :total-page="totalPage"
          @page-change="onPageChange"
        ></PageNavigation>
      </div>
    </div>
    <div class="col-8 my-1 mx-auto" v-else>
      <div
        class="container border p-4 d-flex flex-column justify-content-center"
        id="container-no-content"
      >
        <h5 id="container-no-content-subject" class="text-center my-1">
          등록된 리스트가 없습니다.
        </h5>
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
