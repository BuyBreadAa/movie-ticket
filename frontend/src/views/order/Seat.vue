<script setup>
// 模块三（A）实现选座 + SVG 座位图 + 15分钟倒计时
</script>

<template>
  <div class="seat-select">
    <h1>选择座位</h1>
    <p>场次信息：---</p>
    <div class="screen">银幕</div>
    <div class="seat-placeholder">
      <p style="text-align:center;color:#999;padding:80px 0">座位图（SVG实现，模块三开发）</p>
    </div>
    <div style="margin-top:20px;display:flex;gap:12px">
      <span>🟢 可选</span><span>🔴 已售</span><span>🟡 锁定</span><span>⚫ 故障</span>
    </div>
    <el-button type="primary" style="margin-top:20px">确认选座</el-button>
  </div>
</template>

<style scoped>
.screen { text-align: center; background: #ccc; padding: 8px; margin: 16px 0; border-radius: 4px; font-size: 14px; color: #666; }
.seat-placeholder { background: #fafafa; border: 1px dashed #ddd; border-radius: 8px; min-height: 200px; }
</style>
