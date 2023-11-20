<script setup></script>

<template>
  <nav
    class="navbar navbar-expand-lg sticky-top d-flex justify-content-center align-items-center navbar-light bg-white"
  >
    <div class="col-10 container-fluid mx-5">
      <router-link
        class="navbar-brand d-flex align-items-center justify-content-center col-2"
        href="#"
        :to="{ name: 'home' }"
      >
        <img src="@/assets/EnjoyTrip-logo.png" class="rounded me-1 d-block logo-img" alt="logo" />
        <p class="logo-font">EnjoyTrip</p>
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarScroll"
        aria-controls="navbarScroll"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <img src="@/assets/menu.png" class="rounded mx-auto d-block logo-img" alt="MENU" />
      </button>
      <div class="collapse navbar-collapse bg-white" id="navbarScroll">
        <ul
          class="navbar-nav my-2 me-1 my-lg-0 navbar-nav-scroll"
          style="--bs-scroll-height: 100px"
        >
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle menu-font"
              href="#"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Place
            </a>
            <ul class="dropdown-menu">
              <li>
                <router-link class="dropdown-item menu-font-sm" href="#" :to="{ name: 'place' }"
                  >검색</router-link
                >
              </li>
            </ul>
          </li>
        </ul>
        <ul
          class="navbar-nav my-2 me-1 my-lg-0 navbar-nav-scroll"
          style="--bs-scroll-height: 100px"
        >
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle menu-font"
              href="#"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              커뮤니티
            </a>
            <ul class="dropdown-menu">
              <li>
                <router-link class="dropdown-item menu-font-sm" href="#" :to="{ name: 'Board' }"
                  >자유</router-link
                >
              </li>
              <li>
                <router-link class="dropdown-item menu-font-sm" href="#" :to="{ name: 'QnA' }"
                  >QnA</router-link
                >
              </li>
            </ul>
          </li>
        </ul>
        <ul
          class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll"
          style="--bs-scroll-height: 100px"
        >
          <li class="nav-item">
            <a class="nav-link menu-font-sm" href="#" role="button"> 로그인 </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
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

nav {
  display: flex;
  flex: 0.5;
  width: 100%;
}

.navbar-toggler {
  border: none;
}

.logo-img {
  width: 30px;
  height: 30px;
}

.menu-font-sm {
  font-family: "EASTARJET-Medium";
  font-size: 12px;
}

.logo-font {
  font-family: "EASTARJET-Heavy";
  font-size: 15px;
  margin: 0px;
}

.menu-font {
  font-family: "EASTARJET-Heavy";
  font-size: 15px;
}
</style>
