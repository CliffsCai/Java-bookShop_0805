<template>
  <div style="width: 70%">
      <div style="margin-bottom: 30px">编辑书籍</div>
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
      <el-form-item label="借阅数量" prop="borrow_num">
        <el-input-number v-model="form.borrow_num"  :min="1" :max="10" label="请输入借阅数量"></el-input-number>
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
  name: 'EditBorrow',
  data() {
    return {
      form: {},

  }
  },
  created(){

    const id = this.$route.query.id
    request.get("/borrow/" + id).then(res => {
      this.form = res.data
    })



  },

  methods:{
      save(){
      request.put('/borrow/update', this.form).then(res =>{
        if(res.code ==='200'){
          this.$notify.success('更新成功')
          this.$router.push("/borrowList")
        }
        else{
          this.$notify.error(res.msg)

        }
      })
    },
  }
}

</script>