<script setup>
// 模块二（fhf）实现后可替换占位卡片
defineProps({
  movie: { type: Object, default: () => ({}) }
})
</script>

<template>
  <div class="movie-card">
    <div class="poster"></div>
    <h4>{{ movie.title || '影片名称' }}</h4>
    <p>{{ movie.rating || '暂无评分' }}</p>
  </div>
</template>

<style scoped>
.movie-card { cursor: pointer; }
.poster { width: 100%; aspect-ratio: 3/4; background: #ddd; border-radius: 8px; margin-bottom: 6px; }
.poster:hover { opacity: 0.8; }
h4 { font-size: 14px; }
p { font-size: 12px; color: #999; }
</style>
