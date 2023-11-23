<script setup>
import {ref} from 'vue';
const props = defineProps({ place: Object });
const cost = ref(0);
const prev = ref(0);
const memo = ref("");
prev.value = props.place.cost;
cost.value = props.place.cost;
memo.value = props.place.memo;

const emit = defineEmits(["mod", "del"]);
function mod(){
  emit("mod", prev, cost, memo, place);
  prev.value = cost.value;
}
const place = ref(props.place);

const isAvailable = ref(true);
function del(){
  isAvailable.value = false;
  emit("del", props.place.planContentId);
}
</script>

<template>
  <tr v-if="isAvailable">
    <td>{{ place.title }}</td>
    <td>
      <input class="table-input-text light" type="text" placeholder="비용" v-model="cost" @keyup.enter="mod" />
    </td>
    <td>
      <input class="table-input-text light" type="text" placeholder="메모" v-model="memo" @keyup.enter="mod" />
    </td>
    <td><button class="btn delete-btn" @click="del"><svg xmlns="http://www.w3.org/2000/svg" width="21" height="20" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
  <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"/>
</svg></button></td>
  </tr>
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

.delete-btn {
  width: 100%;
  height: 100%;
  font-size: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

td {
  font-family: "EASTARJET-Medium";
  font-size: 15px;
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

.table-input-text {
  border: none;
  width: 100%;
}
</style>
