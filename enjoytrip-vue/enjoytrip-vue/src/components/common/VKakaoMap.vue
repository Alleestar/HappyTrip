<script setup>
import { ref, watch, onMounted } from "vue";
import { getCategory } from "@/api/public.js";
const { VITE_TRIP_SERVICE_KEY } = import.meta.env;
var map;
const positions = ref([]);
const markers = ref([]);
const infoWindows = ref([]);

const props = defineProps({ attractions: Array, selectStation: Object });

const emit = defineEmits(["showModal"]);
function onShowModal(modalAttraction) {
  const param = ref({
    numOfRows: 1,
    pageNo: 1,
    MobileOS: "ETC",
    MobileApp: "EnjoyTrip",
    contentTypeId: modalAttraction.contentTypeId,
    cat1: modalAttraction.cat1,
    cat2: modalAttraction.cat2,
    cat3: modalAttraction.cat3,
    _type: "json",
    serviceKey: VITE_TRIP_SERVICE_KEY,
  });
  getCat(param, modalAttraction);
}

watch(
  () => props.selectStation.value,
  () => {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(props.selectStation.lat, props.selectStation.lng);

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);
  },
  { deep: true }
);

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

const modalCategory = ref("");
const getCat = (param, modalAttraction) => {
  getCategory(
    param.value,
    ({ data }) => {
      if (data.response.body.items.item[0].name) {
        modalCategory.value = data.response.body.items.item[0].name;
        const available = ref(true);
        emit("showModal", modalAttraction, modalCategory, available);
      }
    },
    (error) => {
      console.log(error);
    }
  );
};

watch(
  () => props.attractions.value,
  () => {
    positions.value = [];
    if (props.attractions.length > 0) {
      props.attractions.forEach((attraction) => {
        let obj = {};
        obj.contentId = attraction.contentId;
        (obj.contentTypeId = attraction.contentTypeId), (obj.img1 = attraction.img1);
        obj.cat1 = attraction.cat1;
        obj.cat2 = attraction.cat2;
        obj.cat3 = attraction.cat3;
        obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
        obj.title = attraction.title;

        positions.value.push(obj);
      });
      loadMarkers();
    }
  },
  { deep: true }
);

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);

  // loadMarkers();
};

const loadMarkers = () => {
  // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
  deleteMarkers();
  // deleteInfoWindows();
  // 마커 이미지를 생성합니다.
  //   const imgSrc = require("@/assets/map/markerStar.png");
  // 마커 이미지의 이미지 크기 입니다
  //   const imgSize = new kakao.maps.Size(24, 35);
  //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

  // 마커를 생성합니다
  markers.value = [];
  infoWindows.value = [];
  positions.value.forEach((position) => {
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      // image: markerImage, // 마커의 이미지
    });

    const infoWindow = new kakao.maps.InfoWindow({
      position: position.latlng,
      content: `<div style="display:flex; flex-direction: column; padding:5px; height:65px">
                  <p  class="medium mb-0 p-0" style="display:flex; flex-wrap:nowrap; font-size: 10px">${position.title}</p>
                  <div class="d-flex justify-content-center">
                  <button class="btn btn-dark medium p-1" style="font-size: 10px" id="openModalBtn${position.contentId}" data-bs-toggle="modal" data-bs-target="#placeDetail">상세보기</button>
                    </div>
                  </div>`,
    });
    infoWindows.value.push(infoWindow);
    infoWindow.open(map, marker);
    document.getElementById(`openModalBtn${position.contentId}`).addEventListener("click", () => {
      // console.log("click");
      // console.log(position);
      onShowModal(position);
    });

    markers.value.push(marker);
    // infoWindows.value.push(infoWindow);
  });

  // 4. 지도를 이동시켜주기
  // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
  const bounds = positions.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
  );

  map.setBounds(bounds);
};

const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
  if (infoWindows.value.length > 0) {
    infoWindows.value.forEach((infowindow) => infowindow.close());
  }
};
// const deleteInfoWindows = () => {
//   if (infoWindows.value.length > 0) {
//     // infoWindow.value.forEach((marker) => marker.setMap(null));
//   }
// };
</script>

<template>
  <div id="map"></div>
</template>

<style>
#map {
  width: 100%;
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
