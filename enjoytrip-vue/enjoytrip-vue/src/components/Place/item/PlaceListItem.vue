<script setup>
import { ref, onMounted } from "vue";
import { getCategory } from "@/api/public.js";

const props = defineProps({ attraction: Object });

const { VITE_TRIP_SERVICE_KEY } = import.meta.env;

const category = ref("");
const param = ref({
  numOfRows: 1,
  pageNo: 1,
  MobileOS: "ETC",
  MobileApp: "EnjoyTrip",
  contentTypeId: props.attraction.contentTypeId,
  cat1: props.attraction.cat1,
  cat2: props.attraction.cat2,
  cat3: props.attraction.cat3,
  _type: "json",
  serviceKey: VITE_TRIP_SERVICE_KEY,
});

onMounted(() => {
  getCat();
});

const getCat = () => {
  getCategory(
    param.value,
    ({ data }) => {
      if (data.response.body.items.item[0].name) {
        category.value = data.response.body.items.item[0].name;
      }
    },
    (error) => {
      console.log(error);
    }
  );
};

const emit = defineEmits(["showModal"])
function onShowModal(){
  const attraction = props.attraction;
  emit("showModal", attraction, category);
}
</script>

<template>
  <div>
    <div class="container m-0 p-3 border">
      <button @click="onShowModal" class="article" data-bs-toggle="modal" data-bs-target="#placeDetail">
        <div class="desc mx-1">
          <h6 class="medium article-title font-weight-bold">
            <b>{{ attraction.title }}</b>
          </h6>
          <p class="light article-content m-0" v-if="category">
            {{ category }}
          </p>
          <span class="meta-info text-secondary">{{ attraction.addr1 }}</span>
        </div>
        <div class="image mx-1">
          <div class="article-img-shape" v-if="attraction.img1">
            <img class="article-img" :src="attraction.img1" />
          </div>
          <div class="article-img-shape" v-else>
            <img class="article-img" src="@/assets/pictures.png" />
          </div>
        </div>
      </button>
      <!-- <PlaceDetail :attraction="attraction" :category="category" /> -->
    </div>
  </div>
</template>

<style scoped>
/* @font-face {
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
} */

.heavy {
  font-family: "EASTARJET-Heavy";
}

.medium {
  font-family: "EASTARJET-Medium";
}

.light {
  font-family: "EASTARJET-DemiLight";
}

.meta-info {
  font-family: "EASTARJET-DemiLight";
  font-size: 10px;
  text-align: left;
}

.article {
  
  text-decoration: none;
  color: inherit;
  display: flex;
  background-color: white;
  border: none;
  width: 100%;
  justify-content: start;
}

.article-content {
  font-size: 12px;
}

.article-title {
  text-align: left;
  font-size: 14px;
  margin-bottom: 2px;
}

.image {
  flex: 3;
  display: flex;
  justify-content: center;
}

.desc {
  display: flex;
  flex-direction: column;
  flex: 7;
  height: 70px;
  align-items: start;
  justify-content: center;
}

.article-img-shape {
  border-radius: 10%;
  overflow: hidden;
  width: 70px;
  height: 70px;
}

.article-img {
  width: 100%;
  height: 100%;
}
</style>
