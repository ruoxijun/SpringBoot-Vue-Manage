<template>
  <div class="body">
    <div style="margin: 15vh auto 0px; min-width: 300px; max-width: 25vw; background: #FFFFFF;
        box-shadow: 0 2px 4px rgba(0,0,0,0.12),0 0 6px rgba(0,0,0,0.04); padding: 40px 20px;
        border-radius: 4px">
      <h2 style="margin-bottom: 30px; text-align: center;
          border-bottom: #cccccc 1px solid">请登录</h2>
      <el-form ref="from" :model="form" :rules="rules">
        <el-form-item prop="username">
          <el-input placeholder="请输入用户名" v-model="form.username" clearable
                    prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" v-model="form.password" show-password
                    prefix-icon="el-icon-unlock"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%; margin-top: 10px" @click="login">登录</el-button>
        </el-form-item>
        <el-button type="text" style="float: right;"
                   @click="$router.push('/register')"> 注册账号 </el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return {
      form:{},
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          {
            min: 1,
            max: 10,
            message: '长度在 1 到 10 个字符',
            trigger: 'blur',
          },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          {
            min: 5,
            max: 18,
            message: '长度在 5 到 18 个字符',
            trigger: 'blur',
          },
        ],
      }
    }
  },
  created() {
    sessionStorage.removeItem('user');
  },
  methods:{
    login(){
      // 表单验证ref为from的表单
      this.$refs['from'].validate((valid) => {
        if (valid) { // 当表单验证合法时
          request.post("/user/login",this.form).then((res)=>{
            if (res.code == '0') {
              this.$message({
                type:"success",
                message:"登录成功"
              });
              sessionStorage.setItem('user',JSON.stringify(res.data));
              this.$router.push("/");  // 跳转页面
            } else {
              this.$message({
                type:"error",
                message:res.msg
              });
            }
          });
        }
      });
    },
  }
}
</script>

<style scoped>
.body{
  width: 100%;
  height: 100vh;
  overflow:hidden;
  background-image: url(../assets/login_back.png);
}
</style>