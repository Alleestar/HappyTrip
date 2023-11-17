<script setup>
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import PlaceList from "@/components/Place/PlaceList.vue";

import { ref } from "vue";

const chargingStations = ref([]);
const selectStation = ref({});

const currentPage = ref(1);
const totalPage = ref(1);
const word = ref("");
const params = {
  pgno: 1,
  spp: import.meta.env.VITE_ATTRACTION_LIST_SIZE,
  word: word.value,
};

const attractions = ref([]);
</script>

<template>
  <div id="body" style="height: 100vh">
    <aside class="d-flex" id="aside" style="height: 100%">
      <PlaceList />
    </aside>
    <div id="map">
      <div class="container text-center mt-3">
        <VKakaoMap :stations="chargingStations" :selectStation="selectStation" />
      </div>
    </div>
  </div>
</template>

<style scoped>
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
</style>
