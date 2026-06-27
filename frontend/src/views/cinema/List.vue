<script setup>
// 模块二（fhf）实现
</script>

<template>
  <div class="cinema-list">
    <div class="page-hero">
      <h1>选择影院</h1>
      <div class="gold-line"></div>
    </div>

    <div class="cinema-grid">
      <div v-for="i in 4" :key="i" class="cinema-card">
        <div class="cinema-header">
          <h3>影院 {{ i }}</h3>
          <span class="cinema-type">IMAX · 杜比 · 4K</span>
        </div>
        <div class="cinema-body">
          <p>📍 地址信息待填写</p>
          <p>📞 联系方式待填写</p>
        </div>
        <div class="cinema-footer">
          <el-button type="primary" size="small">查看排片</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page-hero { text-align: center; padding: 40px 0 32px; }
.page-hero h1 { font-size: 2.6rem; color: var(--text-primary); }
.gold-line { width: 60px; height: 3px; background: var(--gold); border-radius: 2px; margin: 12px auto 0; }
.cinema-grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 20px; max-width: 900px; margin: 0 auto; }
.cinema-card {
  background: var(--bg-card); border-radius: var(--radius); padding: 24px;
  border: 1px solid transparent; transition: all 0.3s;
}
.cinema-card:hover { border-color: rgba(212,168,67,0.25); box-shadow: var(--glow-gold); }
.cinema-header { display: flex; align-items: baseline; justify-content: space-between; margin-bottom: 14px; }
.cinema-header h3 { font-size: 1.3rem; }
.cinema-type { font-size: 12px; color: var(--gold); }
.cinema-body p { color: var(--text-secondary); margin: 6px 0; font-size: 14px; }
.cinema-footer { margin-top: 16px; }
@media (max-width: 768px) { .cinema-grid { grid-template-columns: 1fr; } }
</style>
