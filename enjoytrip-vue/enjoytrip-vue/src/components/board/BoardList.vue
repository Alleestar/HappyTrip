<script setup>
import { ref, onMounted } from "vue";
import PageNavigation from "@/components/common/PageNavigation.vue";
import BoardListItem from "@/components/Board/item/BoardListItem.vue";
import { useRouter } from "vue-router";
import { listBoard } from "@/api/board.js";

const boards = ref([]);

// 화면 이동
const router = useRouter();
function goToWrite() {
  router.push({ name: "board-write" });
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

const searchList = async () => {
  params.key = key.value;
  params.word = word.value;
  await listBoard(
    params,
    ({ data }) => {
      console.log(data);
      boards.value = data.boards;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      console.log(error);
    }
  );
};

function refresh() {
  key.value = "";
  word.value = "";
  params.key = "";
  params.word = "";
  params.pgno = 1;
  listBoard(
    params,
    ({ data }) => {
      boards.value = data.Boards;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      console.log(error);
    }
  );
}

function onPageChange(value) {
  params.pgno = value;
  searchList();
}

onMounted(() => {
  searchList();
});
</script>

<template>
  <div class="row container-fluid m-1 d-flex align-items-start flex-column">
    <div class="col-10 my-1 mx-auto">
      <div class="container p-4 border">
        <h5 id="qna-container-title">자유게시판</h5>
        <p id="qna-container-content" class="text-secondary">30살 이상 사용가능</p>
      </div>
    </div>
    <div class="col-10 my-2 mx-auto">
      <div class="row">
        <div class="d-flex justify-content-center">
          <div class="m-1">
            <select v-model="key" class="form-select" style="font-size: 15px; width: 120px">
              <option disabled value="">검색조건</option>
              <option value="tc">제목+내용</option>
              <option value="user_nickname">작성자</option>
            </select>
          </div>
          <div class="m-1">
            <input
              placeholder="질문을 검색하세요."
              class="form-control input"
              name="search"
              id="search-input-text"
              type="text"
              v-model="word"
              @keyup.enter="searchList"
              style="width: 350px"
            />
          </div>
          <div class="m-1">
            <button
              class="btn btn-dark"
              id="search-btn"
              @click="searchList"
              style="margin-right: 30px"
            >
              검색
            </button>
            <button class="btn btn-outline-dark mx-1" id="no-content-write-btn" @click="goToWrite">
              글 작성
            </button>
          </div>
        </div>
      </div>
    </div>
    <div class="col-10 my-1 mx-auto" v-if="boards.length > 0">
      <div class="container px-0 d-flex flex-column justify-content-center">
        <div class="mx-auto w-100"></div>
        <BoardListItem v-for="board in boards" :key="board.boardNo" :board="board"></BoardListItem>
        <PageNavigation
          :currentPage="currentPage"
          :total-page="totalPage"
          @page-change="onPageChange"
        ></PageNavigation>
      </div>
    </div>
    <div class="col-10 my-1 mx-auto" v-else>
      <div
        class="container border p-4 d-flex flex-column justify-content-center"
        id="container-no-content"
      >
        <h5 id="container-no-content-subject" class="text-center my-1">글이 없습니다.</h5>
        <!-- <p id="container-no-content-content" class="text-center text-secondary my-1">
          첫 글을 작성해보세요.
        </p> -->
        <div class="d-flex justify-content-center my-4">
          <button class="btn btn-dark mx-1" id="no-content-write-btn" @click="refresh">목록</button>
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
  font-size: 15px;
}

#search-btn,
#no-content-write-btn {
  font-family: "EASTARJET-Heavy";
  font-size: 15px;
  /* min-width: 48px; */
  /* max-height: 32px; */
}

#search-input-text {
  font-family: "EASTARJET-DemiLight";
  font-size: 15px;
}

#qna-container-title {
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

#qna-container-content {
  font-family: "EASTARJET-Medium";
  font-size: 15px;
  text-decoration: underline;
}

#container-no-content {
  height: 500px;
}
</style>
