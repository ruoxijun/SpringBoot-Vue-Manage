<template>
<div>
  <el-menu
      :default-active="path"
      router
      class="el-menu-vertical-demo"
      style="min-height: calc(100vh - 51px);"
  ><!-- 100vh 表示整个窗口的高度 calc 计数减去头部的高度 -->
    <el-sub-menu index="1">
      <template #title>
        <span>系统管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/user">用户管理</el-menu-item>
        <el-menu-item index="/book">书籍管理</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
    <el-menu-item index="data">数据管理</el-menu-item>
  </el-menu>
</div>
</template>

<script>
export default {
  name: "Aside",
  data(){
    return{
      path: this.$route.path,
    }
  },
}
</script>

<style scoped>

</style>