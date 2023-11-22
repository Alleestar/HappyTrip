<script setup>
defineProps({ place: Object });
</script>

<template>
  <tr>
    <td><button class="btn loc-day-btn">◀</button></td>
    <td>{{ place.title }}</td>
    <td>
      <input class="table-input-text light" type="text" placeholder="비용" :place="place.cost" />
    </td>
    <td>
      <input class="table-input-text light" type="text" placeholder="메모" :place="place.memo" />
    </td>
    <td><button class="btn loc-day-btn">▶</button></td>
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

.loc-day-btn {
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
