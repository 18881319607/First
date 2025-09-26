<!--frontend/src/views/User.vue-->
<template>
  <div class="profile">
    <h2>个人中心</h2>
    <div v-if="userInfo">
      <p><strong>昵称：</strong>{{ userInfo.nickname }}</p>
      <p><strong>邮箱：</strong>{{ userInfo.email }}</p>
      <p><strong>注册时间：</strong>{{ formatDate(userInfo.createdAt) }}</p>
    </div>
    <button @click="logout">退出登录</button>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      userInfo: {
        nickname: "测试用户",
        email: "test@example.com",
        createdAt: "2025-09-01 12:00:00"
      }
    };
  },
  methods: {
    formatDate(date) {
      return date.split(" ")[0]; // 简单格式化
    },
    logout() {
      localStorage.removeItem("token");
      alert("已退出登录");
      this.$router.push("/");
    }
  }
};
</script>
