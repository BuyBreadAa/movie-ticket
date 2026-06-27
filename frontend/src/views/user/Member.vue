<script setup>
// 模块一（zyy）实现
</script>

<template>
  <div class="member">
    <h1>会员中心</h1>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <div class="stat-card">
            <h3>会员等级</h3>
            <p style="font-size:28px;color:#E6A23C">普通会员</p>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="stat-card">
            <h3>当前积分</h3>
            <p style="font-size:28px;color:#409EFF">0</p>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="stat-card">
            <h3>购票优惠</h3>
            <p style="font-size:28px;color:#67C23A">无</p>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<style scoped>
.stat-card { text-align: center; padding: 20px; }
</style>
