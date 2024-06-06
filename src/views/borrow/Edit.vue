<template>
  <div style="width: 70%">
      <div style="margin-bottom: 30px">编辑书籍</div>
    <el-form :inline="true" :model="form" :rules="rules" ref = "ruleForm" label-width="100px">
      <el-table-column prop = "id" label="编号"></el-table-column>
      <el-table-column prop = "book_name" label="书籍名称"></el-table-column>
      <el-table-column prop = "book_no" label="书籍编号"></el-table-column>
      <el-table-column prop = "user_id" label="用户编号"></el-table-column>
      <el-table-column prop = "user_name" label="用户名称"></el-table-column>
      <el-table-column prop = "user_phone" label="电话"></el-table-column>
      <el-table-column prop = "createtime" label="创建时间"></el-table-column>
      <el-table-column prop = "updatetime" label="更新时间"></el-table-column>
      <el-table-column prop = "score" label="消耗积分"></el-table-column>

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
      categories:[],
      rules: {
        name: [
          { required: true, message: '名称不能为空', trigger: 'blur' }
        ],
    }

  }
  },
  created(){

    request.get('/category/tree').then(res =>{       //这个代码用于下来菜单中数据的获取
      this.categories = res.data   //将返回的数据赋值给categories
    })

    const id = this.$route.query.id
    request.get("/borrow/" + id).then(res => {
      this.form = res.data
      this.form.categories = this.form.category.split(" > ")  //用于显示文本
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
    handleChange(){

    }
  }
}

</script>