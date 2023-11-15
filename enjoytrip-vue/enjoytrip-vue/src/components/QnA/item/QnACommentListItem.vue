<script setup>
defineProps({ comment: Object });
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
        <h6 class="medium m-0 comment-nickname">{{ comment.userNickname }}</h6>
      </div>
    </div>
    <div class="content my-1">
      <p class="medium comment-font mb-0">
        {{ comment.content }}
      </p>
    </div>
    <div class="meta-article-info">
      <p class="light meta-info text-secondary mb-0">{{ comment.date }}</p>
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
