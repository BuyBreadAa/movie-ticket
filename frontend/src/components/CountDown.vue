<script setup>
// 模块三（A）实现 15 分钟支付倒计时
defineProps({
  seconds: { type: Number, default: 900 } // 15分钟 = 900秒
})
</script>

<template>
  <span class="countdown">⏱ {{ Math.floor(seconds / 60) }}:{{ String(seconds % 60).padStart(2, '0') }}</span>
</template>
