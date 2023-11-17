<script setup>
defineProps({ attraction: Object });
</script>

<template>
  <div>
    <div class="container m-0 p-4 border">
      <router-link
        class="article d-flex justify-content-between"
        :to="{ name: '', query: { aid: attraction.contentId } }"
      >
        <div class="desc col-9">
          <h6 class="medium article-title font-weight-bold">
            <b>{{ attraction.title }}</b>
          </h6>
          <p class="light article-content mb-2">
            {{ attraction.title }}
          </p>
          <div class="info">
            <span class="meta-info text-secondary">{{ attraction.addr1 }}</span>
            <span class="meta-info text-secondary"> ∙ </span>
            <span class="meta-info text-secondary">{{ attraction.addr1 }}</span>
          </div>
        </div>
        <div class="article-img-shape" v-if="attraction.img">
          <img class="article-img" src="{{ attraction.img }}" />
        </div>
        <div>
          <img class="article-img" src="@/assets/pictures.png" />
        </div>
      </router-link>
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

.meta-info {
  font-family: "EASTARJET-DemiLight";
  font-size: 8px;
}

.article {
  text-decoration: none;
  color: inherit;
}

.article-content {
  font-size: 10px;
}

.article-title {
  font-size: 12px;
}

.article-img-shape {
  border-radius: 10%; /* 50%의 원형 경계 생성 */
  overflow: hidden; /* 경계를 벗어나는 부분을 숨김 */
}

.article-img {
  width: 70px;
}
</style>
