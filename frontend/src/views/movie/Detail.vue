<script setup>
// 模块二（fhf）实现影片详情
// 模块五（fhf）实现影评区域
</script>

<template>
  <div class="movie-detail">
    <div class="detail-hero">
      <div class="backdrop"></div>
      <div class="detail-content">
        <div class="poster-col">
          <div class="poster-placeholder"></div>
        </div>
        <div class="info-col">
          <h1 class="movie-title">影片详情</h1>
          <div class="gold-line"></div>
          <dl class="meta">
            <div><dt>导演</dt><dd>---</dd></div>
            <div><dt>主演</dt><dd>---</dd></div>
            <div><dt>类型</dt><dd>---</dd></div>
            <div><dt>上映</dt><dd>---</dd></div>
          </dl>
          <div class="rating-badge">
            <span class="score">9.4</span>
            <span class="unit">/10</span>
          </div>
          <el-button type="primary" size="large" class="btn-buy">选座购票</el-button>
        </div>
      </div>
    </div>

    <section class="detail-section">
      <h2>剧情简介</h2>
      <p class="synopsis-placeholder">影片简介将在模块二中实现，届时从后端接口获取详细剧情描述。</p>
    </section>

    <section class="detail-section">
      <h2>影评</h2>
      <p style="color:var(--text-muted)">影评功能将在模块五中实现</p>
    </section>

    <section class="detail-section">
      <h2>相似影片推荐</h2>
      <p style="color:var(--text-muted)">推荐功能将在模块四中实现</p>
    </section>
  </div>
</template>

<style scoped>
.detail-hero {
  position: relative; margin: -20px calc(-1 * var(--gap)) 0; padding: 60px var(--gap);
  background: radial-gradient(ellipse at 20% 50%, #1a1a4e 0%, transparent 60%),
              var(--bg-surface); overflow: hidden;
}
.backdrop {
  position: absolute; inset: 0; opacity: 0.06;
  background: repeating-linear-gradient(0deg, transparent, transparent 2px, rgba(255,255,255,0.03) 2px, rgba(255,255,255,0.03) 4px);
}
.detail-content { position: relative; display: flex; gap: 40px; align-items: flex-start; max-width: 1000px; }
.poster-col { flex-shrink: 0; width: 260px; }
.poster-placeholder { width: 100%; aspect-ratio: 3/4; background: linear-gradient(135deg, #1a1a3e, #2a1a3e); border-radius: var(--radius); }
.movie-title { font-size: 2.6rem; margin-bottom: 16px; }
.gold-line { width: 60px; height: 3px; background: var(--gold); border-radius: 2px; margin-bottom: 24px; }
.meta { display: grid; grid-template-columns: 1fr 1fr; gap: 10px 24px; margin-bottom: 24px; }
.meta dt { font-size: 12px; color: var(--text-muted); text-transform: uppercase; letter-spacing: 0.1em; }
.meta dd { font-size: 15px; color: var(--text-primary); margin-left: 0; }
.rating-badge { margin-bottom: 24px; }
.score { font-size: 3rem; font-family: var(--font-display); color: var(--gold); font-weight: 700; }
.unit { font-size: 1rem; color: var(--text-muted); }
.btn-buy { background: var(--gold) !important; border-color: var(--gold) !important; color: var(--bg-deep) !important; font-weight: 700 !important; }
.detail-section { margin-top: 48px; }
.detail-section h2 { font-size: 1.5rem; margin-bottom: 16px; }
.synopsis-placeholder { color: var(--text-secondary); line-height: 1.8; }
@media (max-width: 768px) {
  .detail-content { flex-direction: column; }
  .poster-col { width: 180px; }
}
</style>
