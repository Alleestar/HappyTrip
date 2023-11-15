<script setup>
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import { ref } from "vue";

const chargingStations = ref([]);
const selectStation = ref({});
</script>

<template>
  <div id="body">
    <aside id="aside">
      <div class="d-flex justify-content-center" id="search-area">
        <form class="w-100 d-flex flex-row justify-content-center pe-0">
          <div class="px-1" style="width: 80%">
            <input
              placeholder="장소를 입력하세요."
              class="form-control input"
              name="search"
              id="search-input-text"
              type="text"
              value=""
            />
          </div>
          <div class="px-1" style="width: 20%">
            <button class="w-100 btn btn-dark" id="search-btn" click="">검색</button>
          </div>
        </form>
      </div>
      <div class="h-100" id="show-area">
        <div
          class="container border p-2 d-flex flex-column justify-content-center h-100"
          id="container-no-content"
        >
          <img class="mx-auto" src="@/assets/exclamation.png" style="width: 30px" />
          <h5 class="text-center heavy my-1">조건에 맞는 장소가 없습니다.</h5>
        </div>
      </div>
    </aside>
    <div id="map">
      <div class="container text-center mt-3">
        <VKakaoMap :stations="chargingStations" :selectStation="selectStation" />
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

.heavy {
  font-family: "EASTARJET-Heavy";
}

.medium {
  font-family: "EASTARJET-Medium";
}

.light {
  font-family: "EASTARJET-DemiLight";
}

#body {
  display: flex;
  flex-direction: row;
  flex: 9;
}

#aside {
  display: flex;
  flex-direction: column;
  flex: 2.5;
}

#map {
  display: flex;
  flex: 7.5;
}

#search-btn {
  font-family: "EASTARJET-Heavy";
  font-size: 11px;
}

#search-input-text {
  font-family: "EASTARJET-DemiLight";
  font-size: 12px;
}
</style>
