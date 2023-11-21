import { createRouter, createWebHistory } from "vue-router";
import TheHomeView from "@/views/TheHomeView.vue";
import ThePlaceView from "@/views/ThePlaceView.vue";
import MyPlaceView from "@/views/MyPlaceView.vue";
import MyPlacePlanView from "@/views/MyPlacePlanView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: TheHomeView,
    },
    {
      path: "/place",
      name: "place",
      component: ThePlaceView,
      // children: [
      //   {
      //     path: "detail",
      //     name: "place-detail",
      //     component: () => import("@/views/PlaceDetail.vue"),
      //   },
      // ],
    },
    {
      path: "/myplace",
      name: "my-place", 
      component: MyPlaceView,
      redirect: { name: "mp-plan-list" },
      children: [
        {
          path: "plan", // children 에서는 /가 붙기 때문에 빼야함
          name: "mp-plan-list",
          component: () => import("@/components/MyPlace/MyPlacePlanList.vue"),
        },
      ],
    },
    {
      path: "/myplace/plan",
      name: "mp-plan", 
      component: MyPlacePlanView,
      // children: [
      //   {
      //     path: "plan", // children 에서는 /가 붙기 때문에 빼야함
      //     name: "mp-plan-list",
      //     component: () => import("@/components/MyPlace/MyPlacePlanList.vue"),
      //   },
      // ],
    },
    {
      path: "/qna",
      name: "QnA",
      component: () => import("@/views/TheQnAView.vue"),
      redirect: { name: "qna-list" },
      children: [
        {
          path: "list", // children 에서는 /가 붙기 때문에 빼야함
          name: "qna-list",
          component: () => import("@/components/QnA/QnAList.vue"),
        },
        {
          path: "modify", // children 에서는 /가 붙기 때문에 빼야함
          name: "qna-modify",
          component: () => import("@/components/QnA/QnAModify.vue"),
        },
        {
          path: "write",
          name: "qna-write",
          component: () => import("@/components/QnA/QnAWrite.vue"),
        },
        {
          path: "detail",
          name: "qna-detail",
          component: () => import("@/components/QnA/QnADetail.vue"),
        },
      ],
    },
    {
      path: "/board",
      name: "Board",
      component: () => import("@/views/TheBoardView.vue"),
      redirect: { name: "board-list" },
      children: [
        {
          path: "list", // children 에서는 /가 붙기 때문에 빼야함
          name: "board-list",
          component: () => import("@/components/Board/BoardList.vue"),
        },
        {
          path: "modify", // children 에서는 /가 붙기 때문에 빼야함
          name: "board-modify",
          component: () => import("@/components/Board/BoardModify.vue"),
        },
        {
          path: "write",
          name: "board-write",
          component: () => import("@/components/Board/BoardWrite.vue"),
        },
        {
          path: "detail",
          name: "board-detail",
          component: () => import("@/components/Board/BoardDetail.vue"),
        },
      ],
    },
  ],
});

export default router;
