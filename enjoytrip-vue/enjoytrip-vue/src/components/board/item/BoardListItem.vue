<script setup>
defineProps({ board: Object });
</script>

<template>
  <div class="container m-1 p-4 border">
    <router-link
      class="article d-flex justify-content-between"
      :to="{ name: 'board-detail', query: { qid: board.boardNo } }"
    >
      <div class="desc col-9">
        <h6 class="medium article-title font-weight-bold">
          <b>{{ board.title }}</b>
        </h6>
        <p class="light article-content mb-2">
          {{ board.content }}
        </p>
        <div>
          <span class="meta-info text-secondary">{{ board.datetime }}</span>
          <span class="meta-info text-secondary"> ∙ </span>
          <span class="meta-info text-secondary">{{ board.userNickname }}</span>
        </div>
      </div>
      <div class="article-img-shape" v-if="board.img">
        <img class="article-img" src="{{board.img}}" />
      </div>
      <div>
        <img class="article-img" src="@/assets/pictures.png" />
      </div>
    </router-link>
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

.meta-info {
  font-family: "EASTARJET-DemiLight";
  font-size: 15px;
}

.article {
  text-decoration: none;
  color: inherit;
}

.article-content {
  font-size: 15px;
}

.article-title {
  font-size: 15px;
}

.article-img-shape {
  border-radius: 10%; /* 50%의 원형 경계 생성 */
  overflow: hidden; /* 경계를 벗어나는 부분을 숨김 */
}

.article-img {
  width: 70px;
}
</style>
