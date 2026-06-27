<script setup>
// 模块三（A）实现
</script>

<template>
  <div class="order-list">
    <h1>我的订单</h1>
    <el-table :data="[]" empty-text="暂无订单">
      <el-table-column prop="id" label="订单号" />
      <el-table-column prop="movie" label="影片" />
      <el-table-column prop="showTime" label="场次" />
      <el-table-column prop="status" label="状态" />
      <el-table-column label="操作" />
    </el-table>
  </div>
</template>
