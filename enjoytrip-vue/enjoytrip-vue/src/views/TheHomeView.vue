<script setup>
import { ref} from "vue";
import { useRouter } from "vue-router";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import MainReco from "@/components/main/MainReco.vue";
import MainImage from "@/components/main/MainImage.vue";


const attractions = ref([]);
const selectAttraction = ref({});

const ROOT = "/src/assets/mainPage/main-";
const imgs = ref([]);

const router = useRouter();
function goToSearch() {
  router.push({ name: "place" });
}

const title1 = ref("#캠핑 #글램핑 #11월 ");
const query1 = ref("캠핑");
const title2 = ref("#바다 #해수욕장 ");
const query2 = ref("해수욕장");

const white = ref("white");
const black = ref("black");
for (let i = 2; i < 6; ++i) {
    imgs.value.push(ROOT + i + ".jpg");
}





</script>

<template>
  <div id="body">
    <div id="list">
      <div class="col-10 mx-auto">
        <div class="container" style="width: 100%; height: 600px">
          <div id="demo" class="carousel slide" data-bs-ride="carousel" data-interval="6000">
            <!-- The slideshow/carousel -->
            <div class="carousel-inner" style="background-color: black">
              <div class="carousel-item active" style ="{display: flex; justify-content: center}">
                <img
                  :src="ROOT + 1 + '.jpg'"
                  class="d-block"
                  style="width: 100%; height: 600px; object-fit: fill"
                />
                <div class="carousel-caption">
                  <h3 class="heavy">우린 팀이니까...</h3>
                  <p class="medium">끝까지 한다... 포기하지 않아...</p>
                </div>
              </div>
              <MainImage v-for="img in imgs" :key="img" :img="img" />
            </div>
          </div>
        </div>
      </div>
      <div class="col-10 mx-auto">
        <div class="container p-4 my-4" style="width: 100%; height: 250px;">
          <div id="content-logo-area" style="display: flex; justify-content: center;">
            <img class="me-1" src="/src/assets/EnjoyTrip-logo.png" width="50" height="50" />
            <p class="heavy m-0" style="font-size: 28px">EnjoyTrip</p>
          </div>
          <div class="my-3" style="display: flex; justify-content: center;">
            <span class="medium me-1" style="font-size: 17px;">여행지를 찾고 저장할 수 있어요.</span>
          </div>
          <div style="display: flex; justify-content: center;">
            <button class="btn btn-dark heavy" style="font-size: 14px" @click="goToSearch">검색하러 가기</button>
          </div>
        </div>
      </div>
      <div class="col-10 mx-auto">
        <MainReco :title="title1" :query="query1"  :_color="white" :_back_ground="black" />
      </div>
      <div class="col-10 mx-auto">
        <MainReco :title="title2" :query="query2" :_color="black" :_back_ground="white" />
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
