<script setup>
const currComment = defineProps({ comment: Object });
import { modifyComment, deleteComment } from "@/api/boardComment.js";

function goToModifyComment() {
  const c = {
    boardId: currComment.comment.postId,
    id: currComment.comment.id,
    content: "권송아",
    writer: "닉네임뭐하지",
  };
  modifyComment(
    currComment.comment.postId,
    c,
    console.log(c),
    ({ data }) => {
      console.log("댓글 수정 성공");
      //showCommentList();
    },
    (error) => {
      console.log("댓글 수정 실패");
    }
  );
}

function goToDeleteComment() {
  deleteComment(
    currComment.comment.id,
    ({ data }) => {
      console.log("댓글 삭제 성공");
    },
    (error) => {
      console.log("댓글 삭제 에러 발생");
    }
  );
}
</script>

<!-- qid: questions.value.length,
     cid: 
     content: content.value,
     userNickname: "테스트닉네임",
     date: "2023.11.13 12:12", -->
<template>
  <div class="container p-3 border border-top-0">
    <div class="meta-usr-info d-flex">
      <div class="d-flex align-items-start">
        <img src="@/assets/user.png" style="width: 10px" />
      </div>
      <div class="ms-1 d-flex align-items-center">
        <h6 class="medium m-0 comment-nickname">{{ comment.writer }}</h6>
      </div>
    </div>
    <div class="content my-1">
      <p class="medium comment-font mb-0">
        {{ comment.content }}
      </p>
    </div>
    <div class="meta-article-info">
      <p class="light meta-info text-secondary mb-0">{{ comment.createdDate }}</p>
    </div>
    <div class="container d-flex justify-content-end">
      <button class="btn btn-dark mx-1" id="modify-btn" @click="goToModifyComment">수정</button>
      <button class="btn btn-dark mx-1" id="delete-btn" @click="goToDeleteComment">삭제</button>
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

.heavy {
  font-family: "EASTARJET-Heavy";
}

.medium {
  font-family: "EASTARJET-Medium";
}

.light {
  font-family: "EASTARJET-DemiLight";
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

.comment-font {
  font-size: 10px;
}

.meta-info {
  font-size: 8px;
}
</style>
