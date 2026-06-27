<script setup>
// 模块六（A）实现
</script>

<template>
  <div class="admin-movies">
    <h1>影片管理</h1>
    <el-button type="primary">新增影片</el-button>
    <el-table :data="[]" empty-text="暂无数据" style="margin-top:16px">
      <el-table-column prop="id" label="ID" />
      <el-table-column prop="title" label="片名" />
      <el-table-column prop="type" label="类型" />
      <el-table-column prop="status" label="状态" />
      <el-table-column label="操作" />
    </el-table>
  </div>
</template>
