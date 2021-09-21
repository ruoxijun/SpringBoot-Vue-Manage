<template>
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc;
   display: flex;">
    <div style="width: 200px; padding-left: 30px; font-weight: bold;
     color: dodgerblue;">后台管理
    </div>
    <div style="flex: 1;"></div>
    <div style="width: 100px;">
      <el-dropdown>
        <span class="el-dropdown-link">
          {{username}}
          <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/info')">个人信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/login')">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return{
      username:'',
    }
  },
  created() {
    let user = JSON.parse(sessionStorage.getItem("user"));
    if (user) {
      this.username = JSON.parse(sessionStorage.getItem("user")).username;
    }
  }
}
</script>

<style scoped>

</style>