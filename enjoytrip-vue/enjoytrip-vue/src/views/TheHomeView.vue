<script setup>
import { ref } from "vue";
import VKakaoMap from "../components/common/VKakaoMap.vue";

const attractions = ref([]);
const selectAttraction = ref({});
</script>

<template>
  <div id="body">
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
  /* flex: 1; */
  flex: 1;
}
</style>
