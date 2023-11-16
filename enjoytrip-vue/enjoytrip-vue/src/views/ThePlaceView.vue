<script setup>
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import PlaceListItem from "@/components/Place/PlaceListItem.vue";
import { ref } from "vue";

const chargingStations = ref([]);
const selectStation = ref({});

const place = {
  qnaNo: "1",
  title: "test",
  content: "con-tent",
  datetime: "datetimeDFSDF",
  userNickname: "슈웃",
  img: "imsdmfksdfmldksjfxafsjklfds",
};
const places = ref([
  place,
  place,
  place,
  place,
  place,
  place,
  place,
  place,
  place,
  place,
  place,
  place,
  place,
  place,
  place,
]);
</script>

<template>
  <div id="body" style="height: 100vh">
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
      <div class="h-100" id="show-area" v-if="true">
        <div class="container border p-0 d-flex flex-column justify-content-start h-100">
          <div>
            <div class="row">
              <button class="col-4 btn btn-outline-dark search-category-btn" value="12">
                관광
              </button>
              <button class="col-4 btn btn-outline-dark search-category-btn" value="14">
                문화
              </button>
              <button class="col-4 btn btn-outline-dark search-category-btn" value="15">
                축제·공연
              </button>
            </div>
            <div class="row">
              <button class="col-4 btn btn-outline-dark search-category-btn" value="25">
                여행
              </button>
              <button class="col-4 btn btn-outline-dark search-category-btn" value="28">
                레포츠
              </button>
              <button class="col-4 btn btn-outline-dark search-category-btn" value="32">
                숙박
              </button>
            </div>
            <div class="row">
              <button class="col-4 btn btn-outline-dark search-category-btn" value="38">
                쇼핑
              </button>
              <button class="col-4 btn btn-outline-dark search-category-btn" value="39">
                음식점
              </button>
            </div>
          </div>
          <div
            class="container p-0 d-flex flex-column justify-content-start h-100"
            style="overflow: scroll"
          >
            <PlaceListItem
              v-for="place in places"
              :key="place.qnaNo"
              :place="place"
            ></PlaceListItem>
          </div>
        </div>
      </div>
      <div class="h-100" id="show-area" v-else>
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
  flex: 2;
}

#map {
  display: flex;
  flex: 8;
}

#search-btn {
  font-family: "EASTARJET-Heavy";
  font-size: 11px;
}

#search-input-text {
  font-family: "EASTARJET-DemiLight";
  font-size: 12px;
}

.search-category-btn {
  font-family: "EASTARJET-Heavy";
  font-size: 11px;
}
</style>
