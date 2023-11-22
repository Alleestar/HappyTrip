<script setup>
const props = defineProps({ plan: Object, attraction: Object });
import { createPlanContent } from "@/api/plan.js";

function savePlanContent() {
  const pc = {
    planId: props.plan.planId,
    contentId: props.attraction.contentId,
    date: props.plan.startDate,
  };
  createPlanContent(
    pc,
    ({ data }) => {
      alert("해당 리스트에 등록되었습니다.");
    },
    (error) => {
      alert("해당 리스트에 등록된 여행지입니다.");
      console.log(error);
    }
  );
}
</script>

<template>
  <li class="mx-1">
    <a class="ball" :style="{ backgroundColor: plan.color }" @click="savePlanContent"></a>
  </li>
</template>

<style scoped>
.ball {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
}
</style>
