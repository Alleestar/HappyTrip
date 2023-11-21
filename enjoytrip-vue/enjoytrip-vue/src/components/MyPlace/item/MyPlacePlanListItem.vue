<script setup>
defineProps({plan: Object})
</script>

<template>
    <td>
        <router-link class="card"
            style="width: 200px; height: 200px; background-color: black; text-decoration: none"
            :to="{ name: 'mp-plan' }" v-if="plan"
        >
            <div class="card-body p-0 d-flex justify-content-center align-items-center">
            <h4 class="card-title m-0 heavy list-title">{{plan.title}}</h4>
            </div>
        </router-link>
    </td>
</template>

<style scoped>
.list-title {
  font-family: "EASTARJET-Medium";
  color: white;
}

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

tr {
  display: flex;
  flex: 1;
}

td {
  flex: 2.5;
  display: flex;
  justify-content: center;
}
</style>