<template>
  <div style="width: 70%">
      <div style="margin-bottom: 30px">编辑用户</div>
    <el-form :inline="true" :model="form"  label-width="100px">
      <el-form-item label="用户名">
        <el-input v-model="form.username" disabled></el-input>
      </el-form-item>
      <el-form-item label="联系方式">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
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
  name: 'AddAdmin',
  data() {
    return {
      form: {}
    }

  },
  created(){
    const id = this.$route.query.id
    request.get("/admin/" + id).then(res => {
      this.form = res.data
    })
  },
  methods:{
    // 这里的this.form 已经被 el-form更改
      save(){
      request.put('/admin/update', this.form).then(res =>{
        if(res.code ==='200'){
          this.$notify.success('更新成功')
          this.$router.push("/adminList")
        }
        else{
          this.$notify.error(res.msg)

        }
      })
    }
  }
}

</script>