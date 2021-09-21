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
      <el-table-column prop="name" label="书籍"></el-table-column>
      <el-table-column prop="price" label="价格"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column prop="createTime" label="出版时间"></el-table-column>
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
      <!--   新增书籍表单   -->
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="书籍">
          <el-input v-model="form.name" style="width: 80%;"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="form.price" style="width: 80%;"></el-input>
        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="form.author" style="width: 80%;"></el-input>
        </el-form-item>
        <el-form-item label="出版时间">
          <el-date-picker v-model="form.createTime" style="width: 80%;" type="date"
                          format="YYYY-MM-DD" placeholder="请选择日期" clearable></el-date-picker>
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
  name: 'Book',
  components: {},
  data() {
    return {
      form: {}, // 表单数据接收对象
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
      request.get("/book", {
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
    add() { // 点击新增书籍
      this.form = {};
      this.dialogVisible = true;
    },
    save() { // 新增或者更新书籍
      if (this.form.id) {
        request.put("/book", this.form).then(res => {
          if (Number(res.code) === 0 || res.msg === "成功") {
            this.dialogVisible = false;
            ElMessage.success({
              message: '恭喜你，更新书籍成功',
              type: 'success',
            });
            this.load();
          }
        });
      } else {
        request.post("/book", this.form).then(res => {
          if (Number(res.code) === 0 || res.msg === "成功") {
            this.dialogVisible = false;
            ElMessage.success({
              message: '恭喜你，添加书籍成功',
              type: 'success',
            });
            this.load();
          }
        });
      }
    },
    handleDelete(id) { // 点击确认删除时
      request.delete("/book",{
        params:{id:id}
      }).then(res=>{
        if (Number(res.code) === 0 || res.msg === "成功") {
          ElMessage.success({
            message: '恭喜你，删除书籍成功',
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