<script setup>
import { ref } from "vue";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import MainImage from "@/components/main/MainImage.vue";

const attractions = ref([]);
const selectAttraction = ref({});

const ROOT = "/src/assets/mainPage/main-";
const imgs = ref([]);

for (let i = 2; i < 6; ++i) {
  imgs.value.push( ROOT + i + ".jpg");
}
</script>

<template>
  <div id="body">
    <div id="list">
      <div class="col-10 mx-auto">
        <div class="container" style="width: 100%; height: 650px">
          <div id="demo" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
              <button
                type="button"
                data-bs-target="#demo"
                data-bs-slide-to="0"
                class="active"
              ></button>
              <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
              <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
              <button type="button" data-bs-target="#demo" data-bs-slide-to="3"></button>
              <button type="button" data-bs-target="#demo" data-bs-slide-to="4"></button>
            </div>

            <div class="carousel-item d-flex justify-content-center alive">
              <img
                :src="ROOT + 1 + '.jpg'"
                class="d-block"
                style="width: 100%; height: 650px; object-fit: none"
              />
              <div class="carousel-caption">
                <h3 class="heavy">우린 팀이니까...</h3>
                <p class="medium">끝까지 한다... 포기하지 않아...</p>
              </div>
            </div>
            <!-- The slideshow/carousel -->
            <div class="carousel-inner" style="background-color: black">
              <MainImage v-for="img in imgs" :key="img" :img="img" />
            </div>
          </div>
        </div>
      </div>
    </div>

    <VKakaoMap
      :attractions="attractions"
      :selectAttraction="selectAttraction"
      style="visibility: hidden"
    />
  </div>
</template>

<style scoped>
#body {
  display: flex;
  flex-direction: column;
  height: 100vh;
  background-color: aqua;
  flex: 1;
}

#list {
  flex: 1;
}

#qna-container-content {
  font-family: "EASTARJET-Medium";
  font-size: 12px;
  text-decoration: underline;
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
