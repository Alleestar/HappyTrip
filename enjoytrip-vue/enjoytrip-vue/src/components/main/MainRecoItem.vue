<script setup>
defineProps({attraction : Object})
console.log("in");
</script>

<template>
    <div class="card mx-2" style="width:20%">
              <img v-if="attraction.img1" class="card-img" :src="attraction.img1" alt="Card image" style="max-height: 140px;">
              <img v-else class="card-img" src="@/assets/pictures.png" alt="Card image" style="max-height: 140px;">
              <div class="card-body">
                <h6 class="card-title medium mb-1">{{ attraction.title }}</h6>
              </div>
            </div>
</template>

<style scoped>

</style>