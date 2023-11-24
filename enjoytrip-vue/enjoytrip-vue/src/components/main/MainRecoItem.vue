<script setup>
defineProps({ attraction: Object });
console.log("in");
</script>

<template>
  <div class="card mx-2" style="width: 20%">
    <img
      v-if="attraction.img1"
      class="card-img"
      :src="attraction.img1"
      alt="Card image"
      style="max-height: 140px"
    />
    <div style="height: 140px; display: flex; justify-content: center; align-items: center" v-else>
      <img class="card-img" src="@/assets/pictures.png" alt="Card image" style="max-width: 100px" />
    </div>

    <div class="card-body">
      <h6 class="card-title medium mb-1">{{ attraction.title }}</h6>
    </div>
  </div>
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

.heavy {
  font-family: "EASTARJET-Heavy";
}

.medium {
  font-family: "EASTARJET-Medium";
}

.light {
  font-family: "EASTARJET-DemiLight";
}
</style>
