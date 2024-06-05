
<template>
<div>
  <!-- 搜索表单 -->
  <div style="margin-bottom: 20px">
    <el-input style = "width: 240px" placeholder = "请输入分类名称" v-model="params.name"> </el-input>

    <el-button style = "margin-left: 10px" type="primary" @click="load"><i class="el-icon-search"></i> 搜索</el-button>
    <el-button style = "margin-left: 10px" type="warning" @click="reset"><i class="el-icon-refresh-right"></i> 重制</el-button>
  </div>
  <el-table :data="tableData" stripe row-key="id" default-expand-all>
    <el-table-column prop = "id" label="编号"></el-table-column>
    <el-table-column prop = "name" label="名称"></el-table-column>
    <el-table-column prop = "remark" label="备注"></el-table-column>
    <el-table-column prop = "createtime" label="创建时间"></el-table-column>
    <el-table-column prop = "updatetime" label="更新时间"></el-table-column>

    <el-table-column label="操作" widen="280" >
      <!--scope是当前数据-->
      <template v-slot="scope">
        <el-button type="success" v-if="!scope.row.pid" @click="handleAdd(scope.row)">添加二级分类</el-button>
        <el-button type="primary" @click="$router.push('/editCategory?id=' + scope.row.id)">编辑</el-button>
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


  <el-dialog title="添加二级分类" :visible.sync="dialogFormVisible" widen="30%">
    <el-form :model="form" label-widen="100px" ref="ruleForm" :rules="rules">   <!--ref要写,对应下方的method里的-->
      <el-form-item label="分类名称" prop="name">     <!--prop和rules与下方rules中的对应-->
        <el-input v-model="form.name" autocomplete="off" ></el-input>
      </el-form-item>
      <el-form-item label="分类备注" prop="name">     <!--prop和rules与下方rules中的对应-->
        <el-input v-model="form.remark" autocomplete="off"></el-input>
      </el-form-item>

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>

</div>
</template>

<script>
import request from "@/utils/request";
import Cookies from 'js-cookie'

export default {
  name: 'Category',
  data(){
    return {
      category: Cookies.get('category') ? JSON.parse(Cookies.get('category')) : {},
      tableData:[],
      total:0,
      dialogFormVisible: false,
      form:{},
      pid: null,
      params:{
        pageNum:1,
        pageSize:10,
        name:'',
      },
      rules:{
        name: [
          {required: true, message:'请输入分类名称', trigger:'blur'},

        ]
      }
    }

  },
  created(){
    this.load()
  },
  methods: {

    load() {
      //   fetch('http://localhost:9090/category/list').then(res => res.json()).then(res=>{
      //     console.log(res)
      //     this.tableData = res
      //   })
      request.get('/category/page',
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
        name:''
      }
      this.load()
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/category/delete/" +id).then(res =>{
        if (res.code === '200'){
          this.$notify.success('删除成功')
          this.load()
        }
        else{
          this.$notify.error(res.msg)
        }
      })
    },
    handleAdd(row){
      this.pid = row.id      //将当前行的id作为二级分类的pid
      this.dialogFormVisible =true
    },
    save(){
      this.form.pid=this.pid        //赋值pid给二级分类  与handleAdd联动
      this.$refs['ruleForm'].validate((valid) => {
        if(valid){
          request.post('/category/save', this.form).then(res =>{
            if(res.code ==='200'){
              this.$notify.success('新增二级分类成功')
              this.form = {sex:'男'}
              this.$refs['ruleForm'].resetFields()
              this.dialogFormVisible =false
              this.load()
            }
            else{
              this.$notify.error(res.msg)

            }
          })
        }
      })

    }
  }



}
</script>