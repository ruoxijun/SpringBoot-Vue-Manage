import { createRouter, createWebHistory } from 'vue-router'
import User from '@/views/User'
import Login from "@/views/Login";
import Layout from "@/layout/Layout";
import Book from "@/views/Book";
import Register from "@/views/Register";
import Info from "@/views/Info";

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/user', // 重定向
    children: [ // 嵌套路由
      {
        path: '/user',
        name: 'User',
        component: User,
      },{
        path: '/book',
        name: 'Book',
        component: Book,
      },{
        path: '/info',
        name: 'Info',
        component: Info
      }
    ],
  },{
    path: '/login',
    name: 'Login',
    component: Login
  },{
    path: '/register',
    name: 'Register',
    component: Register
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
