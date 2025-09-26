<!--frontend/src/views/ShoppingCart.vue-->
<template>
  <div class="cart">
    <h2>我的购物车</h2>
    <ul v-if="items.length > 0">
      <li v-for="item in items" :key="item.id">
        {{ item.title }} × {{ item.quantity }} — ￥{{ item.price * item.quantity }}
      </li>
    </ul>
    <p v-else>您的购物车还是空的哦~</p>
    <div class="total">总计：￥{{ totalPrice }}</div>
    <button class="checkout">结算</button>
  </div>
</template>

<script>
export default {
  name: "ShoppingCart",
  data() {
    return {
      items: [
        { id: 1, title: "商品A", price: 99, quantity: 2 },
        { id: 2, title: "商品B", price: 199, quantity: 1 }
      ]
    };
  },
  computed: {
    totalPrice() {
      return this.items.reduce((sum, i) => sum + i.price * i.quantity, 0);
    }
  }
};
</script>
