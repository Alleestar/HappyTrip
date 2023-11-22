<script setup>
import { ref, watch, onMounted } from "vue";
import { getCategory } from "@/api/public.js";
const { VITE_TRIP_SERVICE_KEY } = import.meta.env;
var map;
const positions = ref([]);
const markers = ref([]);
const keys = ref([]);
const infoWindows = ref(new Map());

const props = defineProps({ attractions: Array, selectAttraction: Object });

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
  () => props.selectAttraction,
  () => {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon;
    if(props.selectAttraction.latitude){
      moveLatLon = new kakao.maps.LatLng(
      props.selectAttraction.latitude,
      props.selectAttraction.longitude
      );
      // console.log(props.selectAttraction.contentId)
      infoWindows.value.get(props.selectAttraction.contentId).setMap(map);
      document.getElementById(`openModalBtn${props.selectAttraction.contentId}`).addEventListener("click", () => {
        onShowModal(props.selectAttraction);
      });
      document.getElementById(`closeBtn${props.selectAttraction.contentId}`).addEventListener("click", () => {
        infoWindows.value.get(props.selectAttraction.contentId).setMap(null);
      });
    } else {
      moveLatLon = new kakao.maps.LatLng(
      props.selectAttraction.latlng.Ma,
      props.selectAttraction.latlng.La
      );
      
    }
    
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
        emit("showModal", modalAttraction, modalCategory);
      }
    },
    (error) => {
      console.log(error);
      emit("showModal", modalAttraction, "");
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
        obj.contentTypeId = attraction.contentTypeId, 
        obj.img1 = attraction.img1;
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
  keys.value = [];
  infoWindows.value = new Map();
  positions.value.forEach((position) => {
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      // image: markerImage, // 마커의 이미지
    });

    const infoWindow = new kakao.maps.CustomOverlay({
      position: position.latlng,
      content: `<div class="container info">
                  <p  class="medium mb-0 p-0" style="display:flex; justify-content:center; font-size: 12px">${position.title}</p>
                  <div style="display:flex;" >
                  <button class="btn btn-dark medium p-1 mx-1" style= "display:flex; justify-content:center; height: 20px; width: 20px" id="openModalBtn${position.contentId}" data-bs-toggle="modal" data-bs-target="#placeDetail">
                      <svg xmlns="http://www.w3.org/2000/svg" width="10" height="10" fill="white" class="bi bi-search" viewBox="0 0 16 16">
                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                      </svg>                  
                    </button>
                    <button class="btn btn-dark medium info-btn p-1 "  style= "display:flex; justify-content:center; height: 20px; width: 20px"  id="closeBtn${position.contentId}">
                      <svg xmlns="http://www.w3.org/2000/svg" width="10" height="10" fill="white" class="bi bi-x" viewBox="0 0 16 16">
                        <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                      </svg>  
                    </button>
                    </div>
                  </div>
                </div>`,
    });

    keys.value.push(position.contentId);
    infoWindows.value.set(position.contentId ,infoWindow);
    kakao.maps.event.addListener(marker, "click", function () {
      infoWindow.setMap(map);
      document.getElementById(`openModalBtn${position.contentId}`).addEventListener("click", () => {
        onShowModal(position);
      });
      document.getElementById(`closeBtn${position.contentId}`).addEventListener("click", () => {
        infoWindow.setMap(null);
      });
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
  if (keys.value.length > 0) {
    keys.value.forEach((key) => infoWindows.value.get(key).setMap(null));
    keys.value = [];
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

.info {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding: 5px;
  background-color: white;
  border: solid 2px;
}
</style>
