
<template>
<div>
  <!-- 搜索表单 -->
  <div style="margin-bottom: 20px">
    <el-input style = "width: 240px" placeholder = "请输入名称" v-model="params.name"> </el-input>
    <el-input style = "width: 240px" placeholder = "请输入编码" v-model="params.book_no"> </el-input>
    <el-button style = "margin-left: 10px" type="primary" @click="load"><i class="el-icon-search"></i> 搜索</el-button>
    <el-button style = "margin-left: 10px" type="warning" @click="reset"><i class="el-icon-refresh-right"></i> 重制</el-button>
  </div>
  <el-table :data="tableData" stripe>
    <el-table-column prop = "id" label="编号"></el-table-column>
    <el-table-column prop = "name" label="名称"></el-table-column>
    <el-table-column prop = "description" label="描述"></el-table-column>
    <el-table-column prop = "publish_date" label="发行日期"></el-table-column>
    <el-table-column prop = "author" label="作者"></el-table-column>
    <el-table-column prop = "publisher" label="发行商"></el-table-column>
    <el-table-column prop = "category" label="分类"></el-table-column>
    <el-table-column prop = "book_no" label="书籍编号"></el-table-column>
    <el-table-column prop = "cover" label="封面">
      <el-table-column prop = "score" label="消耗积分"></el-table-column>

      <template v-slot="scope">
        <el-image :src="scope.row.cover" :preview-src-list="[]"></el-image>
      </template>
    </el-table-column>

    <el-table-column prop = "createtime" label="创建时间"></el-table-column>
    <el-table-column prop = "updatetime" label="更新时间"></el-table-column>

    <el-table-column label="编辑">
      <template v-slot="scope">
        <el-button type="primary" @click="$router.push('/editBook?id=' + scope.row.id)">编辑</el-button>
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
  name: 'Book',
  data(){
    return {
      tableData:[],
      total:0,
      params:{
        pageNum:1,
        pageSize:10,
        name:'',
        book_no:''
      }

    }
  },
  created(){
    this.load()
  },
  methods: {
    load() {
      //   fetch('http://localhost:9090/book/list').then(res => res.json()).then(res=>{
      //     console.log(res)
      //     this.tableData = res
      //   })
      request.get('/book/page',
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
        book_no:''
      }
      this.load()
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/book/delete/" +id).then(res =>{
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