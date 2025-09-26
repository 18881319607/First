<!-- frontend/src/views/Home.vue -->
<template>
  <div class="home-container">
    <!-- 顶部搜索栏 -->
    <div class="header-bar">
      <div class="logo">
        <img src="/images/logo.png" alt="logo" class="logo-img" />
      </div>
      <div class="search-box">
        <select v-model="searchType">
          <option value="商品">商品</option>
          <option value="店铺">店铺</option>
        </select>
        <input type="text" v-model="searchKeyword" placeholder="搜索商品/店铺..." />
        <button class="search-btn" @click="search">搜索</button>
        <button class="crawl-btn" @click="crawlData">立即抓取</button>
      </div>
    </div>

    <!-- 上部分三栏 -->
    <div class="top-section-wrapper">
      <div class="top-section">
        <!-- 左 -->
        <div class="sidebar">
          <ul>
            <li v-for="cat in categories" :key="cat.name">{{ cat.name }}</li>
          </ul>
        </div>

        <!-- 中 -->
        <div class="banner-products">
          <swiper
              :modules="swiperModules"
              :autoplay="{ delay: 3000 }"
              :loop="true"
              pagination
              class="banner-swiper"
          >
            <swiper-slide v-for="(banner, index) in banners" :key="'banner-'+index">
              <img :src="banner" alt="banner" />
            </swiper-slide>
          </swiper>
        </div>

        <!-- 右 -->
        <div class="right-panel">
          <div class="user-card">
            <h4>欢迎您</h4>
            <p>登录后享更多优惠</p>
            <button>立即登录</button>
          </div>
          <div class="quick-links">
            <p>优惠券</p>
            <p>我的订单</p>
            <p>客服中心</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 下部分 -->
    <div class="bottom-section-wrapper">
      <div class="bottom-section">
        <div class="product-card" v-for="p in products" :key="p.id">
          <img :src="p.image" :alt="p.title" />
          <h4>{{ p.title }}</h4>
          <p class="price">￥{{ p.price }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Swiper, SwiperSlide } from "swiper/vue";
import "swiper/css";
import "swiper/css/pagination";
import { Autoplay, Pagination } from "swiper/modules";

export default {
  components: { Swiper, SwiperSlide },
  data() {
    return {
      searchType: "商品",
      searchKeyword: "",
      categories: [
        { name: "家用电器" },
        { name: "手机/运营商/数码" },
        { name: "电脑/办公" },
        { name: "家居/家具/家装/厨具" },
        { name: "男装/女装/童装/内衣" },
        { name: "美妆/个护清洁" },
        { name: "鞋靴/箱包/钟表" },
        { name: "汽车/汽车用品" }
      ],
      banners: [
        "/images/banner1.png",
        "/images/banner2.png"
      ],
      products: [],
      swiperModules: [Autoplay, Pagination]
    };
  },
  mounted() {
    this.fetchProducts();
  },
  methods: {
    fetchProducts() {
      axios.get("http://localhost:8081/api/products")
          .then(res => this.products = res.data)
          .catch(console.error);
    },
    search() {
      this.fetchProducts();
    },
    crawlData() {
      axios.get("http://localhost:8081/api/crawler/jd", {
        params: { keyword: this.searchKeyword || "家用电器", pages: 2 }
      }).then(() => this.fetchProducts()).catch(console.error);
    }
  }
};
</script>

<style scoped>
.home-container {
  width: 1200px;
  margin: auto;
  font-family: Arial, sans-serif;
}

/* 顶部条 */
.header-bar {
  display: flex;
  align-items: center;
  padding: 10px;
}
.logo-img {
  height: 40px;
}
.search-box {
  display: flex;
  gap: 5px;
  margin-left: 20px;
}
.search-box select,
.search-box input {
  height: 32px;
  border: 1px solid #ccc;
  font-size: 14px;
}
.search-btn {
  background: red;
  color: #fff;
  border: none;
  height: 34px;
  padding: 0 12px;
}
.crawl-btn {
  background: #666;
  color: #fff;
  border: none;
  height: 34px;
  padding: 0 12px;
}

/* 上部分容器 */
.top-section-wrapper {
  background: #fff;
  border: 1px solid #eee;
  padding: 10px;
  margin-top: 10px;
}
.top-section {
  display: flex;
  gap: 10px;
  align-items: stretch; /* 三栏等高 */
}

/* 左栏 */
.sidebar {
  width: 200px;
  border: 1px solid #eee;
  background: #fff;
  display: flex;
  flex-direction: column;
}
.sidebar ul {
  list-style: none;
  margin: 0;
  padding: 0;
  flex: 1;
}
.sidebar li {
  padding: 10px;
  border-bottom: 1px solid #f5f5f5;
  cursor: pointer;
}
.sidebar li:hover {
  background: #f0f0f0;
}

/* 中栏：固定 16:9 比例，避免高度跳动 */
.banner-products {
  flex: 1;
  max-width: calc(100% - 420px); /* 200 左 + 200 右 + 间距 20 */
  aspect-ratio: 16 / 9; /* ★ 固定比例，三栏永远等高 */
}
.banner-swiper {
  width: 100%;
  height: 100%;
}
.banner-swiper img {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 裁剪填充 */
  display: block;
}

/* 右栏 */
.right-panel {
  width: 200px;
  display: flex;
  flex-direction: column;
  border: 1px solid #eee;
}
.user-card,
.quick-links {
  padding: 12px;
  text-align: center;
}
.user-card button {
  background: red;
  color: #fff;
  border: none;
  padding: 5px 10px;
  margin-top: 8px;
}

/* 下部分 */
.bottom-section-wrapper {
  background: #fff;
  border: 1px solid #eee;
  padding: 10px;
  margin-top: 15px;
}
.bottom-section {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 10px;
}
.product-card {
  border: 1px solid #eee;
  text-align: center;
  padding: 5px;
}
.product-card img {
  max-width: 160px;
  max-height: 160px;
  width: auto;
  height: auto;
  object-fit: contain;
  display: block;
  margin: 0 auto;
}
.price {
  color: red;
  font-weight: bold;
}
</style>
