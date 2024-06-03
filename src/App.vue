<template>
  <div id="app">
    <!--头部 -->
    <div style="height: 60px;line-height: 60px;background-color: white">
      <img src="@/assets/logo.png" alt="" style="width: 40px;position: relative; top: 10px; left: 20px">
      <span style="margin-left: 25px;font-size: 24px">图书管理系统</span>
    </div>

    <!--侧边栏和主体-->
    <div style = "display: flex">
      <!-- 侧边栏导航 -->
      <div style = "width: 200px; min-height: calc(100vh - 62px);overflow: hidden;margin-right: 2px;background-color: white; margin-bottom: 2px">
        <el-menu :default-active="$route.path" default-openeds="" router class="el-menu-demo" ><!--default-openeds="['user']" 默认开启此列表-->
          <el-menu-item index="/">
            <i class="el-icon-eleme"></i>  <!--图片的名字-->
            <span>首页</span>
          </el-menu-item>
          <el-submenu index="user">      <!--若改成一样的话会同时操作好几个-->
            <template slot="title">
              <i class="el-icon-s-goods"></i>
               <span>会员管理</span>
            </template>
            <el-menu-item index="/addUser">会员添加</el-menu-item>
            <el-menu-item index="/userList">会员列表</el-menu-item>
          </el-submenu>
          <el-submenu index="admin">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>管理员管理</span>
            </template>
            <el-menu-item index="/addAdmin">管理员添加</el-menu-item>    <!--加斜杠可以保证刷新后保持原界面-->
            <el-menu-item index="/adminList">管理员列表</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>

      <!--主体数据-->
      <div style="flex: 1;background-color: white; padding:10px">
        <router-view/>
      </div>
    </div>


  </div>
</template>

<script>
</script>