<script setup></script>

<template>
  <div id="footer">
    <footer class="d-flex justify-content-center">
      <span>&copy; 권송아, 이동훈</span>
    </footer>
  </div>
</template>

<style scoped>
@font-face {
  font-family: "EASTARJET-DemiLight";
  src: url("/fonts/EASTARJET-DemiLight.ttf");
}

#footer {
  /* position: relative; */
  /* bottom: 0px; */
  margin-top: auto;
  width: 100%;
  height: 100px;
}

footer {
  width: 100%;
  height: 100%;
  background-color: black;
  padding: 10px;
}

span {
  font-family: "EASTARJET-DemiLight";
  font-size: 12px;
  text-align: center;
  color: white;
}
</style>
