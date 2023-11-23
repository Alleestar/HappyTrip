import { createRouter, createWebHistory } from "vue-router";
import TheHomeView from "@/views/TheHomeView.vue";
import ThePlaceView from "@/views/ThePlaceView.vue";
import MyPlaceView from "@/views/MyPlaceView.vue";
import MyPlacePlanView from "@/views/MyPlacePlanView.vue";

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;

  let token = sessionStorage.getItem("accessToken");

  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }
  if (!isValidToken.value || userInfo.value === null) {
    next({ name: "user-login" });
  } else {
    next();
  }
};

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
      path: "/myplan/:id",
      name: "my-plan",
      component: MyPlacePlanView,
      props: true,
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
          path: "list",
          name: "qna-list",
          component: () => import("@/components/QnA/QnAList.vue"),
        },
        {
          path: "modify",
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
        {
          path: "qnacommentdetail",
          name: "qna-comment-modify",
          component: () => import("@/components/QnA/item/QnACommentModify.vue"),
        },
      ],
    },
    {
      path: "/user",
      name: "user",
      component: () => import("@/views/TheUserView.vue"),
      redirect: { name: "user-mypage" },
      children: [
        {
          path: "login",
          name: "user-login",
          component: () => import("@/components/users/UserLogin.vue"),
        },
        {
          path: "join",
          name: "user-join",
          component: () => import("@/components/users/UserRegister.vue"),
        },
        {
          path: "mypage",
          name: "user-mypage",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/users/UserMyPage.vue"),
        },
        // {
        //   path: "modify/:userid",
        //   name: "user-modify",
        //   component: () => import("@/components/users/UserModify.vue"),
        // },
      ],
    },
    {
      path: "/board",
      name: "Board",
      component: () => import("@/views/TheBoardView.vue"),
      redirect: { name: "board-list" },
      children: [
        {
          path: "list",
          name: "board-list",
          component: () => import("@/components/board/BoardList.vue"),
        },
        {
          path: "modify",
          name: "board-modify",
          component: () => import("@/components/board/BoardModify.vue"),
        },
        {
          path: "write",
          name: "board-write",
          component: () => import("@/components/board/BoardWrite.vue"),
        },
        {
          path: "detail",
          name: "board-detail",
          component: () => import("@/components/board/BoardDetail.vue"),
        },
        {
          path: "boardcommentdetail",
          name: "board-comment-modify",
          component: () => import("@/components/board/item/BoardCommentModify.vue"),
        },
      ],
    },
  ],
});

export default router;
