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
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">회원가입</mark>
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <form>
          <div class="mb-3">
            <label for="username" class="form-label">이름 : </label>
            <input v-model="name" type="text" class="form-control" placeholder="이름..." />
          </div>
          <div class="mb-3">
            <label for="userid" class="form-label">아이디 : </label>
            <input v-model="userId" type="text" class="form-control" placeholder="아이디..." />
          </div>
          <div class="mb-3">
            <label for="userpwd" class="form-label">비밀번호 : </label>
            <input v-model="password" type="text" class="form-control" placeholder="비밀번호..." />
          </div>
          <div class="mb-3">
            <label for="pwdcheck" class="form-label">비밀번호확인 : </label>
            <input type="text" class="form-control" id="pwdcheck" placeholder="비밀번호확인..." />
          </div>
          <div class="mb-3">
            <label for="emailid" class="form-label">이메일 : </label>
            <div class="input-group">
              <input
                v-model="emailId"
                type="text"
                class="form-control"
                placeholder="이메일아이디"
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
          <div class="col-auto text-center">
            <button type="button" class="btn btn-outline-primary mb-3" @click="doRegist">
              회원가입
            </button>
            <button type="button" class="btn btn-outline-success ms-1 mb-3" @click="doReset">
              초기화
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
