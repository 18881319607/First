<!--frontend/src/App.vue-->
<template>
  <div id="app">
    <!-- 头部导航 -->
    <header class="header">
      <div class="header-inner">
        <!-- 左：地址选择 -->
        <div class="header-left" @mouseenter="showList" @mouseleave="delayHideList">
          <span class="location-text">中国大陆版 - {{ currentProvince }}</span>
          <span class="location-btn">切换</span>

          <!-- 省份选择浮层 -->
          <div
              v-if="showLocationList"
              class="location-list"
              @mouseenter="showList"
              @mouseleave="delayHideList"
          >
            <div
                v-for="province in provinces"
                :key="province"
                class="province-item"
                :class="{ active: province === currentProvince }"
                @click="selectProvince(province)"
            >
              {{ province }}
            </div>
          </div>
        </div>

        <!-- 中：页面导航 -->
        <nav class="header-nav">
          <router-link to="/">首页</router-link>
          <router-link to="/login">登录</router-link>
          <router-link to="/register">注册</router-link>
          <router-link to="/cart">购物车</router-link>
          <router-link to="/user">个人中心</router-link>
        </nav>

        <!-- 右：预留 -->
        <div class="header-right">
          <!-- 可加搜索框/其他功能 -->
        </div>
      </div>
    </header>

    <main>
      <router-view></router-view>
    </main>

    <footer class="footer">
      <p>© 2025 在线商城 版权所有</p>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      currentProvince: '广东',
      showLocationList: false,
      hideTimer: null,
      provinces: [
        "北京","上海","天津","重庆","河北","山西","河南","辽宁","吉林","黑龙江",
        "内蒙古","江苏","山东","安徽","浙江","福建","湖北","湖南","广东","广西",
        "江西","四川","海南","贵州","云南","西藏","陕西","甘肃","青海","宁夏",
        "新疆","港澳","台湾","海外"
      ]
    }
  },
  mounted() {
    this.getLocation();
  },
  methods: {
    async getLocation() {
      try {
        const res = await fetch('https://ipapi.co/json/');
        const data = await res.json();
        if (data.country_code === 'CN' && data.region) {
          this.currentProvince = data.region;
        }
      } catch (e) {
        console.error('位置获取失败:', e);
      }
    },
    selectProvince(province) {
      this.currentProvince = province;
      this.showLocationList = false;
      this.clearHideTimer();
    },
    showList() {
      this.clearHideTimer();
      this.showLocationList = true;
    },
    delayHideList() {
      this.clearHideTimer();
      this.hideTimer = setTimeout(() => {
        this.showLocationList = false;
      }, 100);
    },
    clearHideTimer() {
      if (this.hideTimer) {
        clearTimeout(this.hideTimer);
        this.hideTimer = null;
      }
    }
  }
}
</script>

<style scoped>
#app {
  font-family: "Helvetica Neue", Arial, sans-serif;
  margin: 0;
  padding: 0;
}

.header {
  background-color: #f5f5f5;
  padding: 5px 0;
}

.header-inner {
  width: 1200px;
  margin: 0 auto;
  display: flex;
  align-items: center;
}

.header-left {
  flex: 1;
  position: relative;
  display: flex;
  align-items: center;
  gap: 5px;
  cursor: pointer;
}
.location-text {
  font-size: 14px;
}
.location-btn {
  color: #007aff;
  font-size: 14px;
}
.location-list {
  position: absolute;
  top: 28px;
  left: 0;
  background: white;
  border: 1px solid #ddd;
  box-shadow: 0 2px 6px rgba(0,0,0,0.15);
  padding: 10px;
  display: flex;
  flex-wrap: wrap;
  width: 280px;
  z-index: 100;
}
.province-item {
  width: 25%;
  padding: 5px;
  cursor: pointer;
  text-align: center;
  font-size: 13px;
}
.province-item:hover {
  background: #f0f0f0;
}
.province-item.active {
  background: red;
  color: white;
}

/* 中间导航居中 */
.header-nav {
  flex: 1;
  display: flex;
  justify-content: center;
}
.header-nav a {
  color: black;
  text-decoration: none;
  margin: 0 10px;
}

/* 右侧占位 */
.header-right {
  flex: 1;
  display: flex;
  justify-content: flex-end;
}

main {
  min-height: 70vh;
}

.footer {
  background: #f1f1f1;
  text-align: center;
  padding: 12px;
  color: #666;
}
</style>
