<script setup>
import { useRouter } from "vue-router";
import { ref } from "vue";
import { registQna } from "@/api/qna.js"
const router = useRouter();

//v-model과 연결한 반응형 변수 선언
const title = ref("");
const content = ref("");

import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

/*
Dom과 연결할 반응형 변수 선언, setup에서는 DOM tree가 구성되지 않았기 때문에
mounted 이후에 접근이 가능하다.
*/
const contentDiv = ref(null);
const titleDiv = ref(null);

function goToWrite() {
  //사용자 입력 값을 체크하기
  let err = false;
  let msg = "";

  !title.value && ((msg = "제목을 입력해 주세요"), (err = true), contentDiv.value.focus());
  !err && !content.value && ((msg = "내용을 입력해 주세요"), (err = true), titleDiv.value.focus());

  if (err) {
    alert(msg);
  } else {
    const question = {
      title: title.value,
      content: content.value,
      userNickname: userInfo.value.userName
    };
    registQna(question, 
    ({ data }) => {
      if(window.confirm("등록되었습니다.")){
        title.value = "";
        content.value = "";
        goToList();
      }
    },
    (error) => {
      if(window.confirm("오류가 발생했습니다.")){
        title.value = "";
        content.value = "";
        goToList();
      }
    }
    )
  }
}

function goToList() {
  router.push({ name: "qna-list" });
}

</script>

<template>
  <div class="row container-fluid m-1 d-flex justify-content-center">
    <div class="col-12 my-4">
      <div class="row d-flex justify-content-center">
        <div class="col-10">
          <div class="mt-2 mb-4">
            <h3 class="subject">질문하기</h3>
          </div>
          
          <form>
            <div class="form-group question-font">
              <div class="question-subject-area d-flex align-items-center my-2">
                <span class="question-mark me-2">Q</span>
                <input
                  class="form-control"
                  id="question-title"
                  type="text"
                  ref="titleDiv"
                  v-model="title"
                  placeholder="제목을 입력해주세요."
                />
              </div>
              <div class="question-content-area mb-2">
                <textarea
                  class="form-control"
                  id="question-content"
                  placeholder="내용을 입력해주세요.&#10;질문글은 삭제할 수 없습니다."
                  rows="25"
                  v-model="content"
                  ref="contentDiv"
                ></textarea>
              </div></div>
            </form>
            
              <div class="question-btn-area my-2 d-flex justify-content-between">
                <div class="d-flex justify-content-center align-items-center">
                  <button class="btn mx-1 p-0" id="sharp-btn" @click="test">#</button>
                  <button class="btn mx-1 p-0 m-0" id="img-btn">
                    <img src="@/assets/pictures.png" id="pic-img-btn" />
                  </button>
                </div>
                <div>
                  <button class="btn btn-dark mx-1" id="write-btn" @click="goToWrite">등록</button>
                  <button class="btn btn-dark mx-1" id="list-btn" @click="goToList">목록</button>
                </div>
              </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
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

#pic-img-btn {
  height: 25px;
}

.subject {
  font-family: "EASTARJET-Heavy";
}

.question-mark {
  font-family: "EASTARJET-Medium";
}

.question-font {
  font-family: "EASTARJET-DemiLight";
}

#question-title,
#question-content {
  font-size: 12px;
  color: gray;
}

input.form-control,
textarea.form-control {
  border-radius: 0;
  border-width: 1px;
  padding: 6px;
}

#list-btn,
#write-btn {
  font-family: "EASTARJET-Heavy";
  font-size: 12px;
}

#sharp-btn {
  font-family: "EASTARJET-Heavy";
  font-size: 20px;
  width: 25px;
}
</style>
