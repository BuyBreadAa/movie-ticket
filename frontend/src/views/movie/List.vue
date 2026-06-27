<script setup>
// 模块二（fhf）实现
import { ref } from 'vue'
const activeFilter = ref('all')
</script>

<template>
  <div class="movie-list">
    <div class="page-hero">
      <h1>探索影片</h1>
      <div class="gold-line"></div>
    </div>

    <div class="filters">
      <el-radio-group v-model="activeFilter" size="small">
        <el-radio-button value="all">全部</el-radio-button>
        <el-radio-button value="action">动作</el-radio-button>
        <el-radio-button value="drama">剧情</el-radio-button>
        <el-radio-button value="scifi">科幻</el-radio-button>
        <el-radio-button value="anime">动画</el-radio-button>
      </el-radio-group>
    </div>

    <div class="movie-grid">
      <div v-for="i in 8" :key="i" class="movie-card">
        <div class="card-poster">
          <span class="poster-index">{{ i }}</span>
        </div>
        <div class="card-info">
          <h4>影片 {{ i }}</h4>
          <span class="card-rating">★ 9.0</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page-hero { text-align: center; padding: 40px 0 32px; }
.page-hero h1 { font-size: 2.6rem; color: var(--text-primary); }
.gold-line { width: 60px; height: 3px; background: var(--gold); border-radius: 2px; margin: 12px auto 0; }
.filters { margin-bottom: 32px; display: flex; justify-content: center; }
.movie-grid { display: grid; grid-template-columns: repeat(4, 1fr); gap: 20px; }
.movie-card {
  background: var(--bg-card); border-radius: var(--radius); overflow: hidden;
  cursor: pointer; transition: all 0.4s cubic-bezier(0.25, 0.46, 0.45, 0.94);
}
.movie-card:hover { transform: translateY(-6px); box-shadow: var(--glow-gold); }
.card-poster {
  aspect-ratio: 3/4; background: linear-gradient(135deg, #1a1a3e, #2a1a3e);
  display: flex; align-items: center; justify-content: center; position: relative; overflow: hidden;
}
.card-poster::after {
  content: ''; position: absolute; inset: 0;
  background: linear-gradient(to top, rgba(10,10,15,0.8) 0%, transparent 50%);
}
.poster-index {
  font-family: var(--font-display); font-size: 5rem; font-weight: 700;
  color: rgba(255,255,255,0.05); position: absolute; bottom: -10px; right: 10px; line-height: 1;
}
.card-info { padding: 14px 16px; }
.card-info h4 { font-size: 15px; margin-bottom: 6px; }
.card-rating { color: var(--gold); font-weight: 700; font-size: 14px; }
@media (max-width: 768px) { .movie-grid { grid-template-columns: repeat(2, 1fr); } }
</style>
