<script setup>
import { ref, provide } from "vue";

const comments = ref([]);
provide("comments", comments);
</script>

<template>
  <div id="body">
    <router-view></router-view>
  </div>
</template>

<style scoped>
#body {
  display: flex;
  flex: 9;
}
</style>
