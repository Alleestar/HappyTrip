<script setup>
import { RouterView } from "vue-router";
import TheHeadingNavbar from "@/components/layout/TheHeadingNavbar.vue";
import TheFooter from "@/components/layout/TheFooter.vue";
</script>

<template>
  <TheHeadingNavbar />
  <RouterView />
  <TheFooter />
</template>

<style scoped></style>
