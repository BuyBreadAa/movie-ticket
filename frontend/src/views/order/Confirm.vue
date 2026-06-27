<script setup>
// 模块三（A）实现订单确认 + 支付 + 倒计时
import { useRoute } from 'vue-router'
const route = useRoute()
</script>

<template>
  <div class="order-confirm">
    <h1>确认订单</h1>
    <p>订单ID：{{ route.params.orderId }}</p>
    <el-card>
      <p>影片：---</p>
      <p>场次：---</p>
      <p>座位：---</p>
      <p>金额：---</p>
    </el-card>
    <p style="margin-top:12px;color:#E6A23C">⏱ 支付倒计时：15:00</p>
    <el-button type="success" style="margin-top:16px">确认支付</el-button>
  </div>
</template>
