<template>
  <div style="padding: 10px;">
    <!--  头部按钮  -->
    <el-input placeholder="搜索内容" v-model="search" clearable
              style="width: 20%; margin: 10px;"></el-input>
    <el-button @click="load" icon="el-icon-search" plain>搜索</el-button>
    <el-button type="primary" plain @click="add"
               style="margin: 10px; float: right;">新增
    </el-button>
    <!--  表格文件  -->
    <el-table :data="tableData" border stripe>
      <el-table-column prop="id" sortable label="ID"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="nickName" label="昵称"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="text">编辑</el-button>
          <!--    点击确定时触发handleDelete事件      -->
          <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="text" style="color: #F56C6C;">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!--  分页组件  -->
    <div style="margin: 10px 0;">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
      >
      </el-pagination>
    </div>

    <!--  弹窗  -->
    <el-dialog
        title="提示"
        v-model="dialogVisible"
        width="40%">
      <!--   新增用户表单   -->
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="用户名">
          <el-input v-model="form.username" style="width: 80%;"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickName" style="width: 80%;"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age" style="width: 80%;"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="form.sex" label="男">男</el-radio>
          <el-radio v-model="form.sex" label="女">女</el-radio>
          <el-radio v-model="form.sex" label="隐私">隐私</el-radio>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" style="width: 80%;" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";
import {ElMessage} from "element-plus";

export default {
  name: 'User',
  components: {},
  data() {
    return {
      form: {sex: "隐私"}, // 表单数据接收对象
      dialogVisible: false, // 弹窗默认关闭
      total: 0, // 一共多少条数据
      pageSize: 10, // 一页多少条数据
      currentPage: 1, // 分页的当前页
      search: '', // 模糊查询条件
      tableData: [], // 表数据
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() { // 查询方法
      request.get("/user", {
        params: { // get 请求使用URL传值，就需要使用params参数传值
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search,
        }
      }).then(res => {
        this.total = res.data.total;
        // 表数据
        this.tableData = res.data.records;
      });
    },
    add() { // 点击新增用户
      this.form = {sex: "隐私"};
      this.dialogVisible = true;
    },
    save() { // 新增或者更新用户
      if (this.form.id) {
        request.put("/user", this.form).then(res => {
          if (Number(res.code) === 0 || res.msg === "成功") {
            this.dialogVisible = false;
            ElMessage.success({
              message: '恭喜你，更新用户成功',
              type: 'success',
            });
            this.load();
          }
        });
      } else {
        request.post("/user", this.form).then(res => {
          if (Number(res.code) === 0 || res.msg === "成功") {
            this.dialogVisible = false;
            ElMessage.success({
              message: '恭喜你，添加用户成功',
              type: 'success',
            });
            this.load();
          }
        });
      }
    },
    handleDelete(id) { // 点击确认删除时
      request.delete("/user",{
        params:{id:id}
      }).then(res=>{
        if (Number(res.code) === 0 || res.msg === "成功") {
          ElMessage.success({
            message: '恭喜你，删除用户成功',
            type: 'success',
          });
          this.load();
        }
      });
    },
    handleEdit(row) { // 点击编辑时
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
    },
    handleSizeChange(pageSize) { // 改变每页个数时
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) { // 改变当前页码
      this.currentPage = pageNum;
      this.load();
    },
  }
}
</script>
