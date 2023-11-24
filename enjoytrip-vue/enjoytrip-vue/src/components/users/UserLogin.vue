<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
const { changeMenuState } = useMenuStore();

const loginUser = ref({
  userId: "",
  userPwd: "",
});

const login = async () => {
  await userLogin(loginUser.value);
  let token = sessionStorage.getItem("accessToken");
  if (isLogin) {
    getUserInfo(token);
    changeMenuState();
  }
  router.push("/"); 
  
};

function goToRegister() {
  router.push({
    name: "user-join",
  });
}
</script>

<template>
  <div class="container">
    <div class="row justify-content-center big">
      <br />

      <div style="width: 50%">
        <h2 class="my-1 py-3 text-center custom-heading">
          <mark class="orange">로그인</mark>
        </h2>
      </div>

      <div>
        <form>
          <div class="form-check mb-1 float-end">
            <input class="form-check-input" type="checkbox" />
            <label class="form-check-label" for="saveid"> 아이디 저장 </label>
          </div>
          <!-- <div class="mb-1 text-start">
            <label for="userid" class="form-label">아이디 : </label>
            <input
              type="text"
              class="form-control"
              v-model="loginUser.userId"
              placeholder="아이디..."
            />
          </div> -->
          <br />
          <div class="mb-1 text-start">
            <form class="form-floating">
              <input
                type="text"
                class="form-control"
                v-model="loginUser.userId"
                placeholder="아이디..."
              />
              <label for="floatingInputInvalid">ID</label>
            </form>
          </div>
          <div class="mb-1 text-start">
            <form class="form-floating">
              <input
                type="password"
                class="form-control"
                v-model="loginUser.userPwd"
                @keyup.enter="login"
                placeholder="비밀번호..."
              />
              <label for="floatingInputInvalid">Password</label>
            </form>
          </div>
          <br />
          <div class="col-auto text-center">
            <button type="button" class="btn btn-dark mb-3" id="login-btn" @click="login">
              로그인
            </button>
            <button
              type="button"
              class="btn btn-dark ms-1 mb-3"
              id="register-btn"
              @click="goToRegister"
            >
              회원가입
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  justify-content: center;
}
#login-btn,
#register-btn {
  font-family: "EASTARJET-Heavy";
}
.big {
  display: flex;
  justify-content: center;
}

div.custom-heading {
  width: 50%;
}

h2.custom-heading {
  font-family: "EASTARJET-Heavy"; /* 여기서 'YourDesiredFont'를 원하는 글꼴로 변경하세요 */
  /* 추가적인 스타일 조정이나 속성을 필요에 따라 추가할 수 있습니다. */
}
.custom-label {
  font-family: "EASTARJET-Heavy"; /* 여기서 'YourDesiredFont'를 원하는 글꼴로 변경하세요 */
  /* 추가적인 스타일 조정이나 속성을 필요에 따라 추가할 수 있습니다. */
}
</style>
