<script setup>
// 模块四（zyy）实现推荐系统后替换为真实数据

const movies = [
  { title: '星际穿越', rating: 9.4, tag: '科幻' },
  { title: '肖申克的救赎', rating: 9.7, tag: '剧情' },
  { title: '千与千寻', rating: 9.4, tag: '动画' },
  { title: '盗梦空间', rating: 9.3, tag: '科幻' },
  { title: '泰坦尼克号', rating: 9.4, tag: '爱情' },
  { title: '楚门的世界', rating: 9.3, tag: '剧情' },
  { title: '放牛班的春天', rating: 9.3, tag: '音乐' },
  { title: '疯狂动物城', rating: 9.2, tag: '动画' },
]
</script>

<template>
  <div class="home">

    <!-- ═══ Hero — 聚光灯入场 ═══ -->
    <section class="hero">
      <div class="spotlight-beam"></div>
      <div class="hero-content">
        <p class="hero-eyebrow">CINEMA PARADISO</p>
        <h1 class="hero-title">
          <span class="title-line">银幕之上</span>
          <span class="title-line accent">故事永不落幕</span>
        </h1>
        <div class="gold-line" style="margin: 24px auto"></div>
        <p class="hero-sub">发现你的下一部好电影 · 选座购票，一气呵成</p>
        <div class="hero-actions">
          <router-link to="/movies" class="btn-hero">探索影片</router-link>
          <router-link to="/cinemas" class="btn-hero-outline">查看影院</router-link>
        </div>
      </div>
    </section>

    <!-- ═══ 热映排行 — 灯牌卡片 ═══ -->
    <section class="section-gap">
      <div class="section-head">
        <h2 class="section-title">热映排行</h2>
        <router-link to="/movies" class="section-more">查看全部 →</router-link>
      </div>
      <div class="movie-grid">
        <div v-for="(m, i) in movies.slice(0, 4)" :key="i" class="movie-card" :style="{ animationDelay: `${i * 0.1}s` }">
          <div class="card-poster">
            <span class="poster-index">{{ i + 1 }}</span>
          </div>
          <div class="card-info">
            <h4>{{ m.title }}</h4>
            <span class="card-rating">★ {{ m.rating }}</span>
            <span class="card-tag">{{ m.tag }}</span>
          </div>
        </div>
      </div>
    </section>

    <!-- ═══ 为你推荐 ═══ -->
    <section class="section-gap">
      <div class="section-head">
        <h2 class="section-title">为你推荐</h2>
        <span class="section-more">个性化匹配</span>
      </div>
      <div class="movie-grid">
        <div v-for="(m, i) in movies.slice(4, 8)" :key="i" class="movie-card" :style="{ animationDelay: `${i * 0.1}s` }">
          <div class="card-poster alt">
            <span class="poster-index">{{ i + 1 }}</span>
          </div>
          <div class="card-info">
            <h4>{{ m.title }}</h4>
            <span class="card-rating">★ {{ m.rating }}</span>
            <span class="card-tag">{{ m.tag }}</span>
          </div>
        </div>
      </div>
    </section>

    <!-- ═══ 底部号召 ═══ -->
    <section class="cta">
      <h2>准备好入场了吗</h2>
      <p>加入百万影迷，发现属于你的光影世界</p>
      <router-link to="/register" class="btn-hero">立即加入</router-link>
    </section>

  </div>
</template>

<style scoped>
/* ═══ Hero ═══ */
.hero {
  position: relative; min-height: 85vh; display: flex; align-items: center; justify-content: center;
  text-align: center; overflow: hidden; margin: -20px calc(-1 * var(--gap)) 0;
  background: radial-gradient(ellipse at 30% 20%, #1a1a3e 0%, transparent 60%),
              radial-gradient(ellipse at 70% 80%, #3e1a1a 0%, transparent 50%),
              var(--bg-deep);
}
/* 聚光灯 */
.spotlight-beam {
  position: absolute; top: -10%; left: -20%; width: 60%; height: 120%;
  background: linear-gradient(105deg, transparent 30%, rgba(212,168,67,0.08) 45%, rgba(212,168,67,0.15) 50%, rgba(212,168,67,0.08) 55%, transparent 70%);
  transform: skewX(-12deg);
  animation: spotlight-sweep 3.5s ease-in-out infinite;
  pointer-events: none;
}
.hero-content {
  position: relative; z-index: 1; animation: fade-rise 0.8s ease-out;
}
.hero-eyebrow {
  font-family: var(--font-mono); font-size: 13px; letter-spacing: 0.3em;
  color: var(--gold); margin-bottom: 16px; opacity: 0.8;
}
.hero-title { font-size: 4rem; line-height: 1.15; margin-bottom: 8px; }
.title-line { display: block; color: var(--text-primary); animation: curtain-reveal 0.8s ease-out; }
.title-line.accent { color: var(--gold); font-style: italic; animation: curtain-reveal 1s ease-out; }
.hero-sub { color: var(--text-secondary); font-size: 1.1rem; margin-bottom: 40px; }
.hero-actions { display: flex; gap: 16px; justify-content: center; flex-wrap: wrap; }
.btn-hero {
  background: var(--gold); color: var(--bg-deep); padding: 14px 36px;
  border-radius: 8px; font-weight: 700; font-size: 16px; letter-spacing: 0.04em;
  transition: all 0.3s; font-family: var(--font-display);
}
.btn-hero:hover { background: #e0b850; box-shadow: var(--glow-gold); transform: translateY(-2px); }
.btn-hero-outline {
  border: 2px solid rgba(212,168,67,0.4); color: var(--gold); padding: 14px 36px;
  border-radius: 8px; font-weight: 700; font-size: 16px; letter-spacing: 0.04em;
  transition: all 0.3s; font-family: var(--font-display);
}
.btn-hero-outline:hover { border-color: var(--gold); background: rgba(212,168,67,0.08); }

/* ═══ 区块头 ═══ */
.section-head { display: flex; align-items: baseline; justify-content: space-between; margin-bottom: 28px; }
.section-title { font-size: 2rem; color: var(--text-primary); letter-spacing: 0.03em; }
.section-more { color: var(--gold); font-size: 14px; }

/* ═══ 影片卡片网格 ═══ */
.movie-grid {
  display: grid; grid-template-columns: repeat(4, 1fr); gap: 20px;
}
.movie-card {
  background: var(--bg-card); border-radius: var(--radius); overflow: hidden;
  cursor: pointer; transition: all 0.4s cubic-bezier(0.25, 0.46, 0.45, 0.94);
  animation: fade-rise 0.6s ease-out both;
}
.movie-card:hover {
  transform: translateY(-8px) scale(1.03);
  animation: breathe-glow 2s ease-in-out infinite;
}
.card-poster {
  aspect-ratio: 3/4; background: linear-gradient(135deg, #1a1a3e, #2a1a3e);
  display: flex; align-items: center; justify-content: center; position: relative; overflow: hidden;
}
.card-poster::after {
  content: ''; position: absolute; inset: 0;
  background: linear-gradient(to top, rgba(10,10,15,0.8) 0%, transparent 50%);
}
.card-poster.alt { background: linear-gradient(135deg, #1a2a1e, #1a2a3e); }
.poster-index {
  font-family: var(--font-display); font-size: 5rem; font-weight: 700;
  color: rgba(255,255,255,0.06); position: absolute; bottom: -10px; right: 10px; line-height: 1;
}
.card-info { padding: 14px 16px; }
.card-info h4 { font-size: 15px; margin-bottom: 6px; }
.card-rating { color: var(--gold); font-weight: 700; font-size: 14px; margin-right: 10px; }
.card-tag { color: var(--text-muted); font-size: 12px; padding: 2px 8px; background: rgba(255,255,255,0.04); border-radius: 4px; }

/* ═══ CTA ═══ */
.cta {
  text-align: center; padding: 80px 0;
  background: radial-gradient(ellipse at center, rgba(212,168,67,0.06) 0%, transparent 70%);
  border-radius: var(--radius);
}
.cta h2 { font-size: 2.4rem; margin-bottom: 12px; }
.cta p { color: var(--text-secondary); margin-bottom: 32px; }

/* ═══ 响应式 ═══ */
@media (max-width: 768px) {
  .hero-title { font-size: 2.4rem; }
  .movie-grid { grid-template-columns: repeat(2, 1fr); }
}
</style>
