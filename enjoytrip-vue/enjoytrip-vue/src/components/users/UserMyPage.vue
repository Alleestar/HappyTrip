<script setup>
import { useRouter } from "vue-router";
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const memberStore = useMemberStore();

const { isLogin, userInfo } = storeToRefs(memberStore);
const { userLogout } = memberStore;
const name = ref("");
const emailId = ref("");
const emailDomain = ref("");

name.value = userInfo.value.userName;
emailId.value = userInfo.value.emailId;
emailDomain.value = userInfo.value.emailDomain;

import { modifyUser, deleteUser } from "@/api/user.js";
function modifyMem() {
  const s = {
    userId: userInfo.value.userId,
    userName: name.value,
    emailId: emailId.value,
    emailDomain: emailDomain.value,
  };

  modifyUser(
    s,
    ({ data }) => {
      userInfo.value.userName = name.value;
      userInfo.value.emailId = emailId.value;
      userInfo.value.emailDomain = emailDomain.value;

      alert("수정되었습니다.");
    },
    (error) => {
      alert("수정에 실패했습니다.");
    }
  );
}

const router = useRouter();
function deleteMem() {
  deleteUser(
    userInfo.value.userId,
    ({ data }) => {
      alert("탈퇴되었습니다.");
      userLogout(userInfo.value.userId);
    },
    (error) => {
      alert("탈퇴에 실패했습니다.");
    }
  );
}
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 text-center">
          <mark class="orange heavy">내정보</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <div class="card mt-3 m-auto" style="max-width: 700px">
          <div class="row g-0">
            <div class="col-md-4">
              <img
                src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Hear-No-Evil%20Monkey.png"
                alt="Hear-No-Evil Monkey"
                width="100"
                height="100"
              />
            </div>
            <div class="col-md-8">
              <div class="card-body text-start">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item medium">
                    <input style="border: none" type="text" placeholder="이름" v-model="name" />
                  </li>
                  <li class="list-group-item medium">
                    <input
                      style="border: none"
                      type="text"
                      placeholder="이메일 아이디"
                      v-model="emailId"
                    />
                    at
                    <input
                      style="border: none"
                      type="text"
                      placeholder="이메일 도메인"
                      v-model="emailDomain"
                    />
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <button type="button" class="btn btn-dark m-3 heavy" @click="modifyMem">수정</button>
        <button type="button" class="btn btn-outline-dark m-3 heavy" @click="deleteMem">
          탈퇴
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
#body {
  display: flex;
  flex-direction: column;
  height: 100vh;
  flex: 1;
}

#list {
  flex: 1;
}

.inp-text {
  border: noen;
}

#qna-container-content {
  font-family: "EASTARJET-Medium";
  font-size: 12px;
  text-decoration: underline;
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
