<template>
  <div style="width: 70%">
    <div style="margin-bottom: 30px">新增</div>
    <el-form :inline="true" :model="form" :rules="rules" ref = "ruleForm" label-width="100px">


      <el-form-item label="图书名称" prop="book_name">
        <el-select v-model="form.book_name" clearable filterable placeholder="请选择" @change="selBook">
          <el-option
              v-for="item in books"
              :key="item.id"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="图书编号" prop="book_no">
        <el-input v-model="form.book_no" disabled ></el-input>
      </el-form-item>
      <el-form-item label="消耗积分" prop="score">
        <el-input v-model="form.score" disabled ></el-input>
      </el-form-item>



      <el-form-item label="用户姓名" prop="name">
        <el-select v-model="form.name" clearable filterable placeholder="请选择" @change="selUser">
          <el-option
              v-for="item in users"
              :key="item.id"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="用户编号" prop="username">
        <el-input v-model="form.username"disabled></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="user_phone">
        <el-input v-model="form.user_phone" disabled></el-input>
      </el-form-item>






    </el-form>
    <div style="text-align: center;margin-top: 30px ">
      <el-button type="primary" @click="save" size="medium">提交</el-button>

    </div>
  </div>
</template>

<script>

import  request from "@/utils/request"
export default {
  name: 'AddBorrow',
  data() {
    return {
      form: {},      //输入后的返回值返回到了form.
      books:[],
      users:[],
      rules: {
        book_name: [
          { required: true, message: '图书名称不能为空', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '姓名不能为空', trigger: 'blur' }
        ],
      }
    }

  },
  created(){
    //获取数据,显示在下拉框中
    request.get('/book/list').then(res =>{
      this.books = res.data   //将返回的数据赋值给books
    })
    request.get('/user/list').then(res =>{
      this.users = res.data   //将返回的数据赋值给users
    })
  },
  methods:{
    save(){
      this.$refs['ruleForm'].validate((valid) => {
        if(valid){
          request.post('/borrow/save', this.form).then(res =>{
            if(res.code ==='200'){
              this.$notify.success('新增成功')
              this.$refs['ruleForm'].resetFields()
            }
            else{
              this.$notify.error(res.msg)

            }
          })
        }
      })

    },
    selBook(){
    const book = this.books.find(v => v.name === this.form.book_name)
      this.form.book_no = book.book_no
      this.form.score = book.score
    } ,
    selUser(){
    const user = this.users.find(v => v.name === this.form.name)
      this.form.username= user.username
      this.form.user_phone = user.phone
    }
  }
}

</script>