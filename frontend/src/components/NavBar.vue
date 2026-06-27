<script setup>
import { ref } from 'vue'
import { useUserStore } from '../stores/user'
import { useRouter } from 'vue-router'

const userStore = useUserStore()
const router = useRouter()
const scrolled = ref(false)

if (typeof window !== 'undefined') {
  window.addEventListener('scroll', () => { scrolled.value = window.scrollY > 60 })
}

function handleLogout() {
  userStore.logout()
  router.push('/')
}
</script>

<template>
  <header :class="['navbar', { scrolled }]">
    <div class="navbar-inner">
      <router-link to="/" class="logo">🎬 电影票务</router-link>
      <nav class="nav-links">
        <router-link to="/movies">影片</router-link>
        <router-link to="/cinemas">影院</router-link>

        <template v-if="userStore.isLogin">
          <router-link to="/orders">订单</router-link>
          <el-dropdown trigger="hover">
            <span class="user-trigger">{{ userStore.userInfo?.nickname || '用户' }}</span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item><router-link to="/user/profile">个人资料</router-link></el-dropdown-item>
                <el-dropdown-item><router-link to="/user/member">会员中心</router-link></el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
          <router-link v-if="userStore.userInfo?.role === 'admin'" to="/admin/dashboard">后台</router-link>
          <a @click.prevent="handleLogout" class="logout-link">退出</a>
        </template>
        <template v-else>
          <router-link to="/login">登录</router-link>
          <router-link to="/register" class="btn-register">注册</router-link>
        </template>
      </nav>
    </div>
  </header>
</template>

<style scoped>
.navbar {
  position: sticky; top: 0; z-index: 1000;
  background: transparent;
  transition: all 0.4s ease;
  padding: 18px 0;
}
.navbar.scrolled {
  background: rgba(10,10,15,0.92);
  backdrop-filter: blur(16px);
  border-bottom: 1px solid rgba(255,255,255,0.06);
  padding: 10px 0;
}
.navbar-inner {
  max-width: 1280px; margin: 0 auto; padding: 0 var(--gap);
  display: flex; align-items: center; gap: 32px;
}
.logo {
  font-family: var(--font-display); font-size: 22px; font-weight: 700;
  color: var(--gold); letter-spacing: 0.04em;
}
.nav-links { display: flex; align-items: center; gap: 28px; margin-left: auto; }
.nav-links a {
  color: var(--text-secondary); font-size: 14px; font-weight: 500;
  transition: color 0.2s; cursor: pointer;
}
.nav-links a:hover { color: var(--gold); }
.nav-links a.router-link-active { color: var(--gold); }
.btn-register {
  background: var(--gold); color: var(--bg-deep) !important;
  padding: 6px 18px; border-radius: 6px; font-weight: 600 !important;
}
.btn-register:hover { background: #e0b850 !important; }
.logout-link { color: var(--text-muted) !important; font-size: 13px; }
.user-trigger { color: var(--text-secondary); cursor: pointer; font-size: 14px; }
.user-trigger:hover { color: var(--gold); }
</style>
