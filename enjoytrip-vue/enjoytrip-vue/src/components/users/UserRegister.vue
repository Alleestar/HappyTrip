<script setup>
import { registerUser } from "@/api/user.js";
import { ref } from "vue";

const name = ref("");
const userId = ref("");
const password = ref("");
const emailId = ref("");
const emailDomain = ref("");

function doRegist() {
  // Gather input values
  const userData = {
    name: name.value,
    userId: userId.value,
    password: password.value,
    email: `${emailId.value}@${emailDomain.value}`,
  };

  // Call registerUser function
  registerUser(userData)
    .then((response) => {
      // Handle the response as needed
      console.log("User registration successful", response);
      // Optionally, you can reset the form after successful registration
      doReset();
    })
    .catch((error) => {
      // Handle registration failure
      console.error("User registration failed", error);
    });
}

function doReset() {
  const formControls = document.querySelectorAll(".form-control");

  formControls.forEach((element) => {
    if (element.tagName === "INPUT" || element.tagName === "TEXTAREA") {
      element.value = "";
    } else if (element.tagName === "SELECT") {
      const selectOptions = element.querySelectorAll("option");
      selectOptions.forEach((option) => {
        if (option.value === "") {
          // Identify the default option by its value
          option.selected = true;
        }
      });
    }
  });
  document.querySelector(".selected").innerHTML = "";
}
</script>

<template>
  <div class="container">
    <div class="row justify-content-center align-items-center flex-column big">
      <div style="width: 30%">
        <h2 class="my-1 py-3 text-center custom-heading">
          <mark class="orange">회원가입</mark>
        </h2>
      </div>
      <div style="width: 30%">
        <form>
          <div class="form-floating custom-label">
            <input
              type="text"
              class="form-control"
              v-model="name"
              @keyup.enter="login"
              placeholder="이름"
            />
            <label for="floatingInputInvalid">Name</label>
          </div>

          <br />
          <div class="form-floating custom-label">
            <input
              v-model="userId"
              type="text"
              class="form-control"
              id="btn2"
              placeholder="아이디..."
            />
            <label for="floatingInputInvalid">ID</label>
          </div>
          <br />
          <div class="form-floating custom-label">
            <input
              type="password"
              class="form-control"
              v-model="password"
              @keyup.enter="login"
              placeholder="비밀번호..."
            />
            <label for="floatingInputInvalid">Password</label>
          </div>
          <br />
          <div class="form-floating custom-label">
            <input
              type="password"
              class="form-control"
              v-model="pwdcheck"
              @keyup.enter="login"
              placeholder="비밀번호..."
            />
            <label for="floatingInputInvalid">Password Check</label>
          </div>

          <!-- <div class="mb-3">
            <label for="pwdcheck" class="form-label">비밀번호확인 : </label>
            <input type="text" class="form-control" id="btn4" placeholder="비밀번호확인..." />
          </div> -->
          <br />
          <div class="mb-3">
            <!-- <label for="emailid" class="form-label">이메일 : </label> -->
            <div class="input-group">
              <input
                v-model="emailId"
                type="text"
                class="form-control"
                placeholder="Email ID"
                id="btn5"
              />
              <span class="input-group-text">@</span>
              <select v-model="emailDomain" class="form-select" aria-label="이메일 도메인 선택">
                <option selected class="selected">선택</option>
                <option value="ssafy.com">싸피</option>
                <option value="google.com">구글</option>
                <option value="naver.com">네이버</option>
                <option value="kakao.com">카카오</option>
              </select>
            </div>
          </div>
          <br />
          <div class="col-auto text-center">
            <button type="button" class="btn btn-dark mb-3" id="register-btn" @click="doRegist">
              회원가입
            </button>
            <button type="button" class="btn btn-dark ms-1 mb-3" id="reset-btn" @click="doReset">
              초기화
            </button>
          </div>
          <br />
        </form>
        <br />
      </div>
    </div>
  </div>
</template>

<style scoped>
#btn1,
#btn2 #btn3 {
  font-family: "EASTARJET-Heavy";
}

#reset-btn,
#register-btn {
  font-family: "EASTARJET-Heavy";
}

h2.custom-heading {
  font-family: "EASTARJET-Heavy"; /* 여기서 'YourDesiredFont'를 원하는 글꼴로 변경하세요 */
  /* 추가적인 스타일 조정이나 속성을 필요에 따라 추가할 수 있습니다. */
}
</style>
