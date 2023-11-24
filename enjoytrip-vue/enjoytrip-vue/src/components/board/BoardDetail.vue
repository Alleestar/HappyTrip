<script setup>
import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import BoardCommentListItem from "@/components/board/item/BoardCommentListItem.vue";
import { detailBoard, removeBoard } from "@/api/board.js";
import { listComment, savaComment } from "@/api/boardComment.js";

const route = useRoute();
const router = useRouter();
function goToList() {
  router.push({ name: "board-list" });
}
const qid = ref(route.query.qid);
const board = ref({});
const searchComment = ref([]);

function goToDelete() {
  removeBoard(
    qid.value,
    ({ data }) => {
      if (window.confirm("삭제되었습니다.")) {
        goToList();
      }
    },
    (error) => {
      if (window.confirm("오류가 발생했습니다.")) {
        goToList();
      }
    }
  );
}

function goToModify() {
  router.push({
    name: "board-modify",
    state: board.value,
  });
}

detailBoard(
  qid.value,
  ({ data }) => {
    board.value = data;
  },
  (error) => {
    console.log(error);
  }
);

function showCommentList() {
  listComment(
    qid.value,
    ({ data }) => {
      searchComment.value = data;
      console.log(data);
      console.log("댓글 불러오기 성공");
    },
    (error) => {
      console.log("댓글 불러오기 에러 발생");
    }
  );
}
showCommentList();

const commentContent = ref("");
const commentDiv = ref(null);

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

function goToWriteComment() {
  //사용자 입력 값을 체크하기
  let err = false;
  let msg = "";

  !commentContent.value && ((msg = "댓글을 입력해 주세요"), (err = true), commentDiv.value.focus());

  if (err) {
    alert(msg);
  } else {
    const c = {
      postId: qid.value,
      content: commentContent.value,
      writer: userInfo.value.userName,
    };

    savaComment(
      c,
      ({ data }) => {
        console.log("댓글 등록 성공");
        showCommentList();
      },
      (error) => {
        console.log("댓글 등록 실패");
      }
    );

    // console.log("Comment Regist....comments:", comments.value);
    commentContent.value = "";
  }
}
</script>

<template>
  <div class="row container-fluid m-1 d-flex align-content-start justify-content-center">
    <div class="col-10 mt-4">
      <div class="container p-3 border">
        <h5 class="m-0" id="container-title">자유게시판</h5>
      </div>
    </div>
    <div class="col-10 mt-2">
      <div class="container p-3 border">
        <div class="meta-usr-info d-flex">
          <div><img src="@/assets/user.png" style="width: 35px" /></div>
          <div class="ms-2">
            <h6 class="medium m-0 mb-1">{{ board.userNickname }}</h6>
            <p class="light meta-info text-secondary">{{ board.datetime }}</p>
          </div>
        </div>
        <div class="title">
          <h6 class="heavy m-0">{{ board.title }}</h6>
        </div>
        <div class="content my-1">
          <p class="medium content-font my-0">
            {{ board.content }}
          </p>
        </div>
        <div class="meta-article-info">
          <span class="light meta-info">조회</span>
          <span class="light meta-info">{{ board.readCnt }}</span>
          <span class="light meta-info"> ∙ </span>
          <span class="light meta-info">댓글</span>
          <span class="light meta-info">{{ board.commentCnt }}</span>
        </div>
      </div>
      <div v-if="searchComment.length > 0">
        <BoardCommentListItem
          v-for="c in searchComment"
          :key="c.id"
          :comment="c"
          @reload-comment="showCommentList"
        ></BoardCommentListItem>
      </div>

      <!-- qid: questions.value.length,
     cid: 
     content: content.value,
     userNickname: "테스트닉네임",
     date: "2023.11.13 12:12", -->
      <div class="container border border-top-0 container-comment d-flex">
        <div class="col-11">
          <input
            class="form-control"
            id="comment-input"
            type="text"
            v-model="commentContent"
            ref="commentDiv"
            placeholder="댓글을 입력하세요."
          />
        </div>
        <div class="col-1 d-flex justify-content-end">
          <button
            class="btn btn-dark p-1 d-flex justify-content-center align-items-center"
            style="border-radius: 0; height: 30px; width: 30px"
            id="comment-bnt"
            @click="goToWriteComment"
          >
            <img src="@/assets/pen.png" id="comment-img" />
          </button>
        </div>
      </div>
    </div>
    <div class="col-10 mt-4">
      <div class="container d-flex justify-content-end">
        <button class="btn btn-dark mx-1" id="modify-btn" @click="goToModify">수정</button>
        <button class="btn btn-dark mx-1" id="delete-btn" @click="goToDelete">삭제</button>
        <button class="btn btn-dark mx-1" id="list-btn" @click="goToList">목록</button>
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
  font-size: 15px;
}

.meta-info {
  font-size: 15px;
}

.comment-nickname {
  font-size: 15px;
}

.container-comment {
  background-color: #f0f0f0;
  padding: 0;
}

.content-font {
  font-size: 15px;
}

#comment-img {
  height: 12px;
  width: 12px;
}

#comment-input {
  font-family: "EASTARJET-DemiLight";
  font-size: 15px;
  border: none;
  border-radius: 0;
  background-color: #f0f0f0;
}
</style>
