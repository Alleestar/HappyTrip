<script setup>
import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import QnACommentListItem from "@/components/QnA/item/QnACommentListItem.vue";
import { detailQna, removeQna } from "@/api/qna.js";
import { listComment, savaComment } from "@/api/qnaComment.js";

const route = useRoute();
const router = useRouter();
function goToList() {
  router.push({ name: "qna-list" });
}
const qid = ref(route.query.qid);
const question = ref({});
const searchComment = ref([]);

function goToDelete() {
  removeQna(
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
    name: "qna-modify",
    state: question.value,
  });
}

detailQna(
  qid.value,
  ({ data }) => {
    question.value = data;
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
      id: 454321,
      content: commentContent.value,
      writer: "닉네임뭐하지",
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
        <h5 class="m-0" id="container-title">QnA</h5>
      </div>
    </div>
    <div class="col-10 mt-2">
      <div class="container p-3 border">
        <div class="meta-usr-info d-flex">
          <div><img src="@/assets/user.png" style="width: 35px" /></div>
          <div class="ms-2">
            <h6 class="medium m-0 mb-1">{{ question.userNickname }}</h6>
            <p class="light meta-info text-secondary">{{ question.datetime }}</p>
          </div>
        </div>
        <div class="title">
          <h6 class="heavy m-0">{{ question.title }}</h6>
        </div>
        <div class="content my-1">
          <p class="medium content-font my-0">
            {{ question.content }}
          </p>
        </div>
        <div class="meta-article-info">
          <span class="light meta-info">조회</span>
          <span class="light meta-info">{{ question.readCnt }}</span>
          <span class="light meta-info"> ∙ </span>
          <span class="light meta-info">댓글</span>
          <span class="light meta-info">{{ question.commentCnt }}</span>
        </div>
      </div>
      <div v-if="searchComment.length > 0">
        <QnACommentListItem
          v-for="c in searchComment"
          :key="c.id"
          :comment="c"
        ></QnACommentListItem>
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
  font-size: 15x;
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
