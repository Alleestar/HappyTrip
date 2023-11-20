<script setup>
import { ref, watch } from "vue";
import { describeAttraction } from "@/api/attraction.js";

const props = defineProps({ attraction: Object, category: String });
const desc = ref("");
const available = ref("false");

const currentColor = ref("gray");
watch(props, () => {
  available.value = true;
  currentColor.value = "gray";
  const params = {
    aid: props.attraction.contentId,
  };
  getDesc(params);
});

const getDesc = (params) => {
  describeAttraction(
    params,
    ({ data }) => {
      desc.value = data.detail;
    },
    (error) => {
      console.log(error);
    }
  );
};

const isOpen = ref(false);
function closeHeart() {
  isOpen.value = false;
}

function setHeart(color) {
  currentColor.value = color;
  closeHeart();
}

function openHeart() {
  isOpen.value = true;
}
</script>

<template>
  <div class="modal" id="placeDetail">
    <div class="modal-dialog">
      <div class="modal-content" v-if="available">
        <div class="modal-header p-0 mt-4 mx-4" style="display: flex; flex-direction: column">
          <div class="img-area" style="display: flex; align-items: center" v-if="attraction.img1">
            <div class="img-shape mb-3">
              <img style="width: 100%" :src="attraction.img1" />
            </div>
          </div>
          <div class="info-area">
            <div>
              <h4 class="medium my-0 me-2" style="font-size: 22px">{{ attraction.title }}</h4>
            </div>
            <div class="d-flex align-items-end">
              <p class="light m-0" style="font-size: 13px">{{ category }}</p>
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
          <div class="dropdown">
            <button
              class="btn btn-heart dropdown-toggle"
              @click="openHeart"
              data-bs-toggle="dropdown"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="21"
                height="20"
                :fill="currentColor"
                class="bi bi-heart"
                viewBox="0 0 16 16"
              >
                <path
                  fill-rule="evenodd"
                  d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"
                />
              </svg>
            </button>
            <ul
              v-if="isOpen"
              class="dropdown-menu"
              style="display: flex; justify-content: space-evenly"
            >
              <li>
                <a
                  class="ball"
                  style="background-color: #f23557"
                  @click="setHeart('#F23557')"
                  href="#"
                ></a>
              </li>
              <li>
                <a
                  class="ball"
                  style="background-color: #f0d43a"
                  @click="setHeart('#F0D43A')"
                  href="#"
                ></a>
              </li>
              <li>
                <a
                  class="ball"
                  style="background-color: #22b2da"
                  @click="setHeart('#22B2DA')"
                  href="#"
                ></a>
              </li>
              <li>
                <a
                  class="ball"
                  style="background-color: #3b4a6b"
                  @click="setHeart('#3B4A6B')"
                  href="#"
                ></a>
              </li>
              <li>
                <a class="ball" href="#" @click="closeHeart"
                  ><svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="16"
                    height="16"
                    fill="black"
                    class="bi bi-plus"
                    viewBox="0 0 16 16"
                  >
                    <path
                      d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"
                    />
                  </svg>
                </a>
              </li>
            </ul>
          </div>

          <button class="btn-close" data-bs-dismiss="modal"></button>
        </div>
      </div>
    </div>
  </div>
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

.detail-area {
  max-height: 200px;
  overflow-y: scroll;
}

.img-shape {
  overflow: hidden;
  width: 250px;
  height: 250px;
  display: flex;
  align-items: center;
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

.btn-heart {
  display: flex;
  align-items: center;
  color: gray;
  margin: 4px;
  padding: 4px;
}
</style>
