<script setup>
import { ref, provide } from "vue";

const comments = ref([]);
provide("comments", comments);
</script>

<template>
  <div class="container text-center mt-3">
    <!-- GIF를 추가하는 부분 -->

    <img src="@/assets/admaru.gif" alt="Loading GIF" />
    <br />

    <!-- 기존의 내용 -->
    <router-view></router-view>
  </div>
</template>

<style>
mark.orange {
  background: linear-gradient(to top, rgb(243, 164, 18) 20%, transparent 30%);
}
</style>
