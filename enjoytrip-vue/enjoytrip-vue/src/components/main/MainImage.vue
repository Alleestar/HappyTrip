<script setup>
const props = defineProps({ img: String });
</script>

<template>
  <div class="carousel-item d-flex justify-content-center">
    <img :src="img" class="d-block" style="width: 100%; height: 650px; object-fit: fill" />
    <div class="carousel-caption">
      <h3 class="heavy">우린 팀이니까...</h3>
      <p class="medium">끝까지 한다... 포기하지 않아...</p>
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
</style>
