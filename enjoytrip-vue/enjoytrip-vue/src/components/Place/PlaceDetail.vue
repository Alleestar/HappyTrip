<script setup>
import { ref } from "vue";
import { describeAttraction } from "@/api/attraction.js";

const props = defineProps({ attraction: Object, category: String });
const params = {
  aid: props.attraction.contentId,
};

const desc = ref("");
describeAttraction(
  params,
  ({ data }) => {
    desc.value = data.detail;
  },
  (error) => {
    console.log(error);
  }
);
</script>

<template>
  <div class="modal" id="placeDetail">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header p-0" style="display: flex; flex-direction: column">
          <div class="img-area" style="display: flex" v-if="attraction.img1">
            <div class="img-shape">
              <img style="width: 100%" :src="attraction.img1" />
            </div>
            <div class="img-shape" v-if="attraction.img2">
              <img style="width: 100%" :src="attraction.img2" />
            </div>
          </div>
          <div class="info-area">
            <div>
              <h4 class="medium my-0 me-2">{{ attraction.title }}</h4>
            </div>
            <div class="d-flex align-items-end">
              <p class="light m-0" style="font-size: 15px">{{ category }}</p>
            </div>
          </div>
        </div>
        <div class="modal-body detail-area">
          <p class="light" style="margin-bottom: 0px" v-if="desc">
            {{ desc }}
          </p>
          <p class="light" style="margin-bottom: 0px" v-else>장소에 대한 설명이 없습니다.</p>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer" style="display: flex; justify-content: space-between">
          <button class="btn btn-heart" data-bs-dismiss="modal">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="21"
              height="20"
              fill="gray"
              class="bi bi-heart"
              viewBox="0 0 16 16"
            >
              <path
                d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"
              />
            </svg>
          </button>
          <button class="btn-close" data-bs-dismiss="modal"></button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.modal-dialog {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
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

.img-shape {
  overflow: hidden;
  width: 200px;
  height: 200px;
}

.info-area {
  display: flex;
  margin-bottom: 10px;
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
.title {
}

.btn-heart {
  display: flex;
  align-items: center;
  color: gray;
  margin: 4px;
  padding: 4px;
}
</style>
