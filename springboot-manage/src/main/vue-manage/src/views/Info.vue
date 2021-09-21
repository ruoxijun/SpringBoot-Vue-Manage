<template>
  <el-form ref="form" :model="form" label-width="120px" style="margin: 30px 0 0 30px">
    <el-form-item label="用户名">
      <el-input v-model="form.username" style="width: 30%; min-width: 200px"></el-input>
    </el-form-item>
    <el-form-item label="昵称">
      <el-input v-model="form.nickName" style="width: 30%; min-width: 200px"></el-input>
    </el-form-item>
    <el-form-item label="年龄">
      <el-input v-model="form.age" style="width: 30%; min-width: 200px"></el-input>
    </el-form-item>
    <el-form-item label="性别">
      <el-radio v-model="form.sex" label="男">男</el-radio>
      <el-radio v-model="form.sex" label="女">女</el-radio>
      <el-radio v-model="form.sex" label="隐私">隐私</el-radio>
    </el-form-item>
    <el-form-item label="地址">
      <el-input v-model="form.address" style="width: 30%; min-width: 200px" type="textarea"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="save">更改个人信息</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import request from "@/utils/request";
import {ElMessage} from "element-plus";

export default {
  name: "Info",
  data() {
    return{
      form: JSON.parse(sessionStorage.getItem('user')),
    }
  },
  methods:{
    save(){
      request.put("/user", this.form).then(res => {
        if (Number(res.code) === 0 || res.msg === "成功") {
          this.dialogVisible = false;
          ElMessage.success({
            message: '恭喜你，更改个人信息成功',
            type: 'success',
          });
          sessionStorage.removeItem('user');
          sessionStorage.setItem('user',JSON.stringify(this.form));
          this.$router.go('/info');
        }
      });
    }
  },
}
</script>

<style scoped>

</style>