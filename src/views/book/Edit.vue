<template>
  <div style="width: 70%">
      <div style="margin-bottom: 30px">编辑书籍</div>
    <el-form :inline="true" :model="form" :rules="rules" ref = "ruleForm" label-width="100px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入名称"></el-input>
      </el-form-item>
      <el-form-item label="描述" prop="age">
        <el-input v-model="form.description" placeholder="请输入描述"></el-input>
      </el-form-item>
      <el-form-item label="发布日期" prop="publish_date">
        <el-input v-model="form.publish_date" placeholder="请输入发布日期"></el-input>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input v-model="form.author" placeholder="请输入作者"></el-input>
      </el-form-item>
      <el-form-item label="发行商" prop="publisher">
        <el-input v-model="form.publisher" placeholder="请输入发行商"></el-input>
      </el-form-item>
      <el-form-item label="分类" prop="categories">
        <!-- v-model="form.categories"  与显示文本框有关-->
        <el-cascader
            :props="{ value: 'name', label:'name'}"
            v-model="form.categories"
            :options="categories"></el-cascader>         <!--绑定下方categories：[]-->
      </el-form-item>
      <el-form-item label="书籍编号" prop="book_no">
        <el-input v-model="form.book_no" placeholder="请输入书籍编号"></el-input>
      </el-form-item>
      <el-form-item label="封面" prop="cover">
        <el-input v-model="form.cover" placeholder="请输入封面"></el-input>
      </el-form-item>
      <el-form-item label="数量" prop="number">
        <el-input v-model="form.number" placeholder="请输入数量"></el-input>
      </el-form-item>
      <el-form-item label="积分" prop="score">
        <el-input-number v-model="num" @change="handleChange" :min="1" :max="10" label="请输入积分"></el-input-number>
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
  name: 'EditBook',
  data() {
    return {
      form: {},
      categories:[],
      rules: {
        name: [
          { required: true, message: '名称不能为空', trigger: 'blur' }
        ],
        score: [
          { required: true, message: '积分不能为空', trigger: 'blur' }
        ],
        book_no: [
          { required: true, message: '书籍编号不能为空', trigger: 'blur' }
        ],
    }

  }
  },
  created(){

    request.get('/category/tree').then(res =>{       //这个代码用于下来菜单中数据的获取
      this.categories = res.data   //将返回的数据赋值给categories
    })

    const id = this.$route.query.id
    request.get("/book/" + id).then(res => {
      this.form = res.data
      this.form.categories = this.form.category.split(" > ")  //用于显示文本
    })



  },

  methods:{
      save(){
      request.put('/book/update', this.form).then(res =>{
        if(res.code ==='200'){
          this.$notify.success('更新成功')
          this.$router.push("/bookList")
        }
        else{
          this.$notify.error(res.msg)

        }
      })
    },


    }

}

</script>