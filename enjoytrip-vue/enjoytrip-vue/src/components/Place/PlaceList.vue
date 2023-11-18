<script setup>
import { ref, watch } from "vue";
import PlacePageNavigation from "@/components/Place/PlacePageNavigation.vue";
import PlaceListItem from "@/components/Place/item/PlaceListItem.vue";
import PlaceDetail from "@/components/Place/PlaceDetail.vue";
import { listAttraction, getAddr } from "@/api/attraction.js";

const init = ref(true);
const currentPage = ref(1);
const totalPage = ref(1);
const word = ref("");
const addrInfo = ref([]);
getAddr(
  ({ data }) => {
    addrInfo.value = data;
  },
  (error) => {
    console.log(error);
  }
);

const sido = ref("");
const selectedCity = ref([]);
watch(sido, () => {
  selectedCity.value = addrInfo.value.filter((item) => item.sidoCode == sido.value);
});
const gugun = ref("");

const params = {
  pgno: 1,
  spp: import.meta.env.VITE_ATTRACTION_LIST_SIZE,
  word: word.value,
  gugunCode: 0,
  sidoCode: 0,
};

const attractions = ref([]);
function searchList() {
  params.word = word.value;
  params.sidoCode = sido.value;
  params.gugunCode = gugun.value;
  listAttraction(
    params,
    ({ data }) => {
      attractions.value = data.attractions;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
      init.value = false;
      onShowMap(attractions.value);
      params.pgno = 1;
    },
    (error) => {
      console.log(error);
      params.pgno = 1;
    }
  );
}

function onSetCategory(value) {
  params.contentTypeId = value;
  searchList();
}

function onPageChange(value) {
  params.pgno = value;
  searchList();
}

const modalAttraction = ref({})
const modalCategory = ref("")
function onShowModal(attraction, category, available){
  modalAttraction.value = attraction;
  modalCategory.value = category.value;
}


const emit = defineEmits(["showMap"]);
function onShowMap(attractions) {
  emit("showMap", attractions);
}

</script>

<template>
  <div id="root">
    <div class="my-row" id="search-area">
      <div style="display: flex">
        <select v-model="sido" class="form-control" style="width: 50%">
          <option disabled value="">시·도</option>
          <option value="1">서울</option>
          <option value="2">인천</option>
          <option value="3">대전</option>
          <option value="4">대구</option>
          <option value="5">광주</option>
          <option value="6">부산</option>
          <option value="7">부산</option>
          <option value="8">세종</option>
          <option value="31">경기</option>
          <option value="32">강원</option>
          <option value="33">충북</option>
          <option value="34">충남</option>
          <option value="35">경북</option>
          <option value="36">경남</option>
          <option value="37">전북</option>
          <option value="38">전남</option>
          <option value="39">제주</option>
        </select>
        <select v-model="gugun" class="form-control" style="width: 50%">
          <option disabled value="">구·군</option>
          <option v-for="city in selectedCity" :value="city.gugunCode">
            {{ city.gugunName }}
          </option>
        </select>
      </div>
      <div style="display: flex">
        <div class="p-0" style="flex: 8">
          <input
            placeholder="장소를 입력하세요."
            class="form-control input"
            name="search"
            id="search-input-text"
            type="text"
            v-model="word"
          />
        </div>
        <div class="p-0" style="flex: 2">
          <button
            class="w-100 btn btn-dark form-control"
            id="search-btn"
            @click="onSetCategory(null)"
          >
            검색
          </button>
        </div>
      </div>
      <div class="my-row">
        <div style="display: flex; justify-content: space-evenly">
          <button class="btn btn-outline-dark search-category-btn me-1" @click="onSetCategory(12)">
            관광
          </button>
          <button class="btn btn-outline-dark search-category-btn me-1" @click="onSetCategory(14)">
            문화
          </button>
          <button class="btn btn-outline-dark search-category-btn" @click="onSetCategory(15)">
            축제·공연
          </button>
        </div>
        <div style="display: flex; justify-content: space-evenly">
          <button
            class="btn btn-outline-dark search-category-btn me-1"
            @click="onSetCategory(this.value)"
            value="25"
          >
            여행
          </button>
          <button class="btn btn-outline-dark search-category-btn me-1" @click="onSetCategory(28)">
            레포츠
          </button>
          <button class="btn btn-outline-dark search-category-btn" @click="onSetCategory(32)">
            숙박
          </button>
        </div>
        <div style="display: flex; justify-content: space-evenly">
          <button class="btn btn-outline-dark search-category-btn me-1" @click="onSetCategory(38)">
            쇼핑
          </button>
          <button class="btn btn-outline-dark search-category-btn me-1" @click="onSetCategory(39)">
            음식점
          </button>
          <button
            class="btn btn-outline-dark search-category-btn"
            style="visibility: hidden"
          ></button>
        </div>
      </div>
    </div>

    <div class="my-row container border px-0" id="show-area">
      <div class="container border p-2 container-no-content" v-if="init">
        <h5 class="text-center heavy my-1">
          어느 겨울날 내가 아침에 버스에 올라타서 "두명이요"하고 교통카드를 찍자 아저씨가
          의아해하면서 너 혼자 탔는데 왜 두명 몫을 찍냐고 묻는거야 그래서 난 이렇게 말했지 "내 가슴
          속에는 동훈이라는 한 사람이 더있거든요.." 추운 아침이었지만 나의 뜨거운 가슴으로 버스안을
          훈훈하게 데웠던 순간이었다
        </h5>
      </div>
      <div class="container p-0 container-content" v-else-if="attractions.length > 0">
        <PlaceListItem
          v-for="attraction in attractions"
          :key="attraction.contentId"
          :attraction="attraction"
          @show-modal="onShowModal"
        ></PlaceListItem>
        <PlaceDetail :attraction="modalAttraction" :category="modalCategory" @hide-modal="onHideModal"/>
        
        
        <PlacePageNavigation
          :currentPage="currentPage"
          :totalPage="totalPage"
          @page-change="onPageChange"
        ></PlacePageNavigation>
      </div>
      <div class="container border p-2 container-no-content" v-else>
        <img class="mx-auto" src="@/assets/exclamation.png" style="width: 30px" />
        <h5 class="text-center heavy my-1">검색 결과가 없습니다.</h5>
      </div>
    </div>
  </div>
</template>

<style scoped>
#root {
  display: flex;
  flex-direction: column;
  flex: 1;
}

#search-area {
  display: flex;
  flex-direction: column;
  flex: 1.5;
}

#show-area {
  display: flex;
  flex-direction: column;
  flex: 8.5;
}

.my-row {
  display: flex;
  flex-direction: column;
  flex: 1;
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

.container-no-content {
  display: flex;
  flex-direction: column;
  flex: 1;
  justify-content: center;
}

.container-content {
  display: flex;
  flex-direction: column;
  flex: 1;
  justify-content: start;
  max-height: 70vh;
  overflow-x: hidden;
  overflow-y: auto;
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

#search-btn {
  font-family: "EASTARJET-Heavy";
  font-size: 10px;
  min-width: 45px;
}

#search-input-text {
  font-family: "EASTARJET-DemiLight";
  font-size: 12px;
}

.search-category-btn {
  font-family: "EASTARJET-Heavy";
  text-align: center;
  font-size: 11px;
  min-width: 70px;
  flex: 3;
}

select {
  font-family: "EASTARJET-Medium";
  font-size: 12px;
}
</style>