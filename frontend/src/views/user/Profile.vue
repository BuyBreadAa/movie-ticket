<script setup>
// 模块一（zyy）实现
</script>

<template>
  <div class="profile">
    <h1>个人资料</h1>
    <el-card style="max-width:500px">
      <el-form>
        <el-form-item label="头像">
          <div class="avatar-placeholder"></div>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary">保存</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.avatar-placeholder { width: 64px; height: 64px; border-radius: 50%; background: #ddd; }
</style>
