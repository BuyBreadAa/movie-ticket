<script setup>
// 模块六（A）实现
</script>

<template>
  <div class="admin-orders">
    <h1>订单管理</h1>
    <el-table :data="[]" empty-text="暂无数据">
      <el-table-column prop="id" label="订单号" />
      <el-table-column prop="user" label="用户" />
      <el-table-column prop="movie" label="影片" />
      <el-table-column prop="amount" label="金额" />
      <el-table-column prop="status" label="状态" />
      <el-table-column label="操作" />
    </el-table>
  </div>
</template>
