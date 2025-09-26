// frontend/src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import ShoppingCart from '../views/ShoppingCart.vue';
import User from '../views/User.vue';


const routes = [
    { path: '/', component: Home },
    { path: '/login', component: Login },
    { path: '/register', component: Register },
    { path: '/cart', component: ShoppingCart },
    { path: '/user', component: User },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;