<!--frontend/src/views/Login.vue-->
<template>
  <div class="login-container">
    <h2>用户登录</h2>
    <form @submit.prevent="handleLogin">
      <input type="email" v-model="user.email" placeholder="邮箱地址" required />
      <input type="password" v-model="user.password" placeholder="密码" required />
      <button type="submit">登录</button>
    </form>
    <p>没有账号？
      <router-link to="/register">立即注册</router-link>
    </p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      user: {
        email: "",
        password: ""
      }
    };
  },
  methods: {
    async handleLogin() {
      try {
        const res = await axios.post("http://localhost:8080/api/login", this.user);
        if (res.data.code === 200) {
          alert("登录成功！");
          localStorage.setItem("token", res.data.token); // 保存登录凭证
          this.$router.push("/");
        } else {
          alert(res.data.message || "登录失败");
        }
      } catch (err) {
        console.error(err);
        alert("服务器连接失败，请稍后重试");
      }
    }
  }
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ddd;
}
input {
  display: block;
  width: 100%;
  margin-bottom: 10px;
  padding: 10px;
}
button {
  width: 100%;
  background: #008CBA;
  padding: 10px;
  border: none;
  color: white;
}
</style>
