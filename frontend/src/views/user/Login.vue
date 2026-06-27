<script setup>
// 模块一（zyy）实现
</script>

<template>
  <div class="login-page">
    <div class="form-card">
      <h2>欢迎回来</h2>
      <p class="subtitle">登录您的电影票务账户</p>
      <el-form label-position="top">
        <el-form-item label="账号">
          <el-input placeholder="手机号 / 邮箱" size="large" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" placeholder="请输入密码" size="large" show-password />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="large" style="width:100%">登 录</el-button>
        </el-form-item>
      </el-form>
      <p class="switch-link">
        没有账号？<router-link to="/register">立即注册</router-link>
      </p>
    </div>
  </div>
</template>

<style scoped>
.login-page { display: flex; align-items: center; justify-content: center; min-height: 70vh; }
.form-card {
  width: 420px; background: var(--bg-card); border-radius: var(--radius);
  padding: 48px 40px; border: 1px solid rgba(255,255,255,0.06);
}
.form-card h2 { font-size: 2rem; text-align: center; margin-bottom: 6px; }
.subtitle { text-align: center; color: var(--text-muted); margin-bottom: 32px; font-size: 14px; }
.switch-link { text-align: center; margin-top: 24px; color: var(--text-muted); font-size: 14px; }
</style>
