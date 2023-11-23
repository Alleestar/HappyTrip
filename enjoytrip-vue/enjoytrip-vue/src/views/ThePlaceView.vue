<script setup>
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import PlaceList from "@/components/Place/PlaceList.vue";
import PlaceDetail from "@/components/Place/PlaceDetail.vue";
import { ref } from "vue";

// const chargingStations = ref([]);
const selectAttraction = ref({});

const currentPage = ref(1);
const totalPage = ref(1);
const word = ref("");
const params = {
  pgno: 1,
  spp: import.meta.env.VITE_ATTRACTION_LIST_SIZE,
  word: word.value,
};

const attractions = ref([]);
function onShowMap(val) {
  attractions.value = val;
}

const as = ref([]);
const s = ref({});

const attraction = ref({});
const category = ref("");
function onShowModal(modalAttraction, modalCategory) {
  attraction.value = modalAttraction;
  selectAttraction.value = modalAttraction;
  category.value = modalCategory.value;
}
</script>

<template>
  <div id="body" style="height: 100vh">
    <aside class="d-flex" id="aside" style="height: 100%">
      <PlaceList @show-map="onShowMap" @show-modal="onShowModal" />
    </aside>
    <div id="map">
      <div class="container text-center mt-3">
        <VKakaoMap
          :attractions="attractions"
          :selectAttraction="selectAttraction"
          @show-modal="onShowModal"
        />
      </div>
    </div>
    <!-- modal -->
    <PlaceDetail :attraction="attraction" :category="category" />
  </div>
</template>

<style scoped>
#body {
  display: flex;
  flex-direction: row;
  flex: 1;
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
</style>
