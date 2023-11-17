import { createRouter, createWebHistory } from "vue-router";
import TheHomeView from "@/views/TheHomeView.vue";
import ThePlaceView from "@/views/ThePlaceView.vue";

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
  ],
});

export default router;
