import { createApp } from 'vue'
import App from './App.vue'
import store from './store'

// 引入路由
import router from './router'

// 引入element-plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn' // 中文支持

import './assets/css/global.css'

createApp(App).use(store)
    .use(router) // 使用路由
    .use(ElementPlus,{
        locale: zhCn, // 使用中文
        // size:"small", // 全局样式大小
    }) // 使用element-plus
    .mount('#app')
