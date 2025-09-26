<!--frontend/src/views/Register.vue-->
<template>
  <div class="register-container">
    <h2>新用户注册</h2>
    <form @submit.prevent="handleSubmit">
      <input type="text" v-model="newUser.name" placeholder="姓名" required />
      <input type="email" v-model="newUser.email" placeholder="邮箱地址" required />
      <input type="password" v-model="newUser.password" placeholder="设置密码" required />
      <button type="submit">注册</button>
    </form>
    <p>已有账号？
      <router-link to="/login">直接登录</router-link>
    </p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Register",
  data() {
    return {
      newUser: {
        name: "",
        email: "",
        password: ""
      }
    };
  },
  methods: {
    async handleSubmit() {
      try {
        // 假设 SpringBoot 后端注册接口为 POST /api/register
        const res = await axios.post("http://localhost:8080/api/register", this.newUser);

        if (res.data.code === 200) {
          alert("注册成功，请登录！");
          this.$router.push("/login");
        } else {
          alert(res.data.message || "注册失败");
        }
      } catch (error) {
        console.error(error);
        alert("注册失败，请稍后再试");
      }
    }
  }
};
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 50px auto;
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 4px;
}
input {
  display: block;
  width: 100%;
  margin-bottom: 15px;
  padding: 10px;
}
button {
  width: 100%;
  background: #008CBA;
  color: white;
  border: none;
  padding: 10px;
}
</style>

