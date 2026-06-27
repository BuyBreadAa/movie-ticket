<script setup>
// 模块六（A）实现
</script>

<template>
  <div class="dashboard">
    <h1>数据看板</h1>
    <el-row :gutter="20">
      <el-col :span="6" v-for="item in ['今日订单', '今日票房', '活跃用户', '上座率']" :key="item">
        <el-card>
          <h3>{{ item }}</h3>
          <p style="font-size:24px;color:#409EFF">---</p>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
