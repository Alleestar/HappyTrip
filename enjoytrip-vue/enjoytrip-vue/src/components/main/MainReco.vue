<script setup>
import { ref } from "vue";
import MainRecoItem from "@/components/main/MainRecoItem.vue";
import { listAttraction } from "@/api/attraction.js";

const props = defineProps({
  title: String,
  query: String,
  _color: String,
  _back_ground: String,
  imgSrc: String,
});
const recos = ref([]);
function getReco() {
  const s = {
    pgno: 1,
    spp: 5,
    word: props.query,
    rand: 1,
  };
  listAttraction(
    s,
    ({ data }) => {
      recos.value = data.attractions;
    },
    (error) => {
      console.log(error);
    }
  );
}
getReco();
</script>

<template>
  <div
    class="container px-4 py-3 m-4"
    :style="{ width: '100%', height: '300px', backgroundColor: _back_ground }"
  >
    <div class="mb-2" style="display: flex; align-items: center">
      <h4 class="heavy mb-0 me-2" :style="{ color: _color }">{{ title }}</h4>
      <img :src="imgSrc" width="46" height="46" />
    </div>
    <div style="display: flex" v-if="recos && recos.length > 0">
      <MainRecoItem v-for="r in recos" :key="r.contentId" :attraction="r" />
    </div>
  </div>
</template>

<style scoped></style>
