
<template>
<div>
  <!-- 搜索表单 -->
  <div style="margin-bottom: 20px">
    <el-input style = "width: 240px" placeholder = "请输入名称" v-model="params.name"> </el-input>
    <el-input style = "width: 240px; margin-left: 10px" placeholder = "请输入联系方式" v-model="params.phone"> </el-input>
    <el-button style = "margin-left: 10px" type="primary" @click="load"><i class="el-icon-search"></i> 搜索</el-button>
    <el-button style = "margin-left: 10px" type="warning" @click="reset"><i class="el-icon-refresh-right"></i> 重制</el-button>
  </div>
  <el-table :data="tableData" stripe>
    <el-table-column prop = "id" label="编号"></el-table-column>
    <el-table-column prop = "username" label="会员号"></el-table-column>
    <el-table-column prop = "name" label="姓名"></el-table-column>
    <el-table-column prop = "age" label="年龄"></el-table-column>
    <el-table-column prop = "address" label="地址"></el-table-column>
    <el-table-column prop = "phone" label="联系方式"></el-table-column>
    <el-table-column prop = "sex" label="性别"></el-table-column>
    <el-table-column prop = "createtime" label="创建时间"></el-table-column>
    <el-table-column prop = "updatetime" label="更新时间"></el-table-column>
    <el-table-column prop = "user_score" label="用户积分"></el-table-column>

    <el-table-column label="操作">
      <template v-slot="scope">
        <el-button type="warning" @click="handleAccountAdd(scope.row)">充值</el-button>
        <el-button type="primary" @click="$router.push('/editUser?id=' + scope.row.id)">编辑</el-button>
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

  <el-dialog title="充值" :visible.sync="dialogFormVisible" widen="30%">
    <el-form :model="form" label-widen="100px" ref="ruleForm">   <!--ref要写,对应下方的method里的-->
      <el-form-item label="当前积分" prop="user_score">     <!--查看当前用户积分-->
        <el-input disabled v-model="form.user_score" autocomplete="off" ></el-input>
      </el-form-item>
      <el-form-item label="充值积分" prop="score">     <!--prop和rules与下方rules中的对应-->
        <el-input v-model="form.score" autocomplete="off" ></el-input>
      </el-form-item>


    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="addScore">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
import request from "@/utils/request";


export default {
  name: 'User',
  data(){
    return {
      tableData:[],
      total:0,
      params:{
        pageNum:1,
        pageSize:10,
        name:'',
        phone:''
      },
      dialogFormVisible :false,
      form:{}

    }
  },
  created(){
    this.load()
  },
  methods: {
    load() {
      //   fetch('http://localhost:9090/user/list').then(res => res.json()).then(res=>{
      //     console.log(res)
      //     this.tableData = res
      //   })
      request.get('/user/page',
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
        phone:''
      }
      this.load()
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/user/delete/" +id).then(res =>{
        if (res.code === '200'){
          this.$notify.success('删除成功')
          this.load()
        }
        else{
          this.$notify.error(res.msg)
        }
      })
    },
    handleAccountAdd(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    addScore(){
      this.$refs['ruleForm'].validate((valid) =>{
        if(valid){
          request.post('/user/score', this.form).then(res =>{
            if(res.code === '200'){
              this.$notify.success('充值成功')
              this.dialogFormVisible=false
              this.load()
            }else{
              this.$notify.error(res.msg)
            }
          })
        }
      })
    }

  }
}
</script>