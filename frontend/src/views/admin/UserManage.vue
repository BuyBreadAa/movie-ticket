<script setup>
// 模块六（A）实现
</script>

<template>
  <div class="admin-users">
    <h1>用户管理</h1>
    <el-table :data="[]" empty-text="暂无数据">
      <el-table-column prop="id" label="ID" />
      <el-table-column prop="nickname" label="昵称" />
      <el-table-column prop="email" label="邮箱" />
      <el-table-column prop="role" label="角色" />
      <el-table-column prop="status" label="状态" />
      <el-table-column label="操作" />
    </el-table>
  </div>
</template>
