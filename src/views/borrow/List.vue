
<template>
<div>
  <!-- 搜索表单 -->
  <div style="margin-bottom: 20px">
    <el-input style = "width: 240px" placeholder = "请输入书籍名称" v-model="params.book_name"> </el-input>
    <el-input style = "width: 240px" placeholder = "请输入用户名称" v-model="params.name"> </el-input>
    <el-button style = "margin-left: 10px" type="primary" @click="load"><i class="el-icon-search"></i> 搜索</el-button>
    <el-button style = "margin-left: 10px" type="warning" @click="reset"><i class="el-icon-refresh-right"></i> 重制</el-button>
  </div>
  <el-table :data="tableData" stripe>
    <el-table-column prop = "id" label="编号"></el-table-column>
    <el-table-column prop = "book_name" label="书籍名称"></el-table-column>
    <el-table-column prop = "book_no" label="书籍编号"></el-table-column>
    <el-table-column prop = "username" label="用户编号"></el-table-column>
    <el-table-column prop = "name" label="用户名称"></el-table-column>
    <el-table-column prop = "user_phone" label="电话"></el-table-column>
    <el-table-column prop = "createtime" label="创建时间"></el-table-column>
    <el-table-column prop = "updatetime" label="更新时间"></el-table-column>
    <el-table-column prop = "score" label="消耗积分"></el-table-column>
    <el-table-column prop = "borrow_num" label="借阅数量"></el-table-column>

    <el-table-column label="操作">
      <template v-slot="scope">

        <el-popconfirm
            style="margin-left: 5px"
            title="您确定删除这行数据吗？"
            @confirm="del(scope.row.id)"
        >
          <el-button type="danger" slot="reference">删除</el-button>
        </el-popconfirm>
        </template>
    </el-table-column>
  </el-table>


  <!--分页-->
  <div style="margin-top: 20px">
    <el-pagination
        background
        :current-page = "params.pageNum"
        :page-size="params.pageSize"
        layout = "prev,pager,next"
        @current-change = "handleCurrentChange"
        :total = "total">
    </el-pagination>
  </div>
</div>
</template>

<script>
import request from "@/utils/request";


export default {
  name: 'Borrow',
  data(){
    return {
      tableData:[],
      total:0,
      params:{
        pageNum:1,
        pageSize:10,
        name:'',
        username:''
      }

    }
  },
  created(){
    this.load()
  },
  methods: {
    load() {
      //   fetch('http://localhost:9090/borrow/list').then(res => res.json()).then(res=>{
      //     console.log(res)
      //     this.tableData = res
      //   })
      request.get('/borrow/page',
          {
            params: this.params
          }).then(res => {
        if(res.code ==='200')
        {
          this.tableData = res.data.list
          this.total = res.data.total
        }

      })
    },
    reset(){
      this.params = {
        pageNum:1,
        pageSize:10,
        name:'',
        username:''
      }
      this.load()
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/borrow/delete/" +id).then(res =>{
        if (res.code === '200'){
          this.$notify.success('删除成功')
          this.load()
        }
        else{
          this.$notify.error(res.msg)
        }
      })
    }

  }
}
</script>