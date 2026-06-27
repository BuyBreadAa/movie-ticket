<script setup>
// 模块一（zyy）实现
</script>

<template>
  <div class="register-page">
    <div class="form-card">
      <h2>创建账户</h2>
      <p class="subtitle">加入影院，开启观影之旅</p>
      <el-form label-position="top">
        <el-form-item label="手机号 / 邮箱">
          <el-input placeholder="请输入手机号或邮箱" size="large" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" placeholder="8位以上，含字母和数字" size="large" show-password />
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input type="password" placeholder="请再次输入密码" size="large" show-password />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="large" style="width:100%">注 册</el-button>
        </el-form-item>
      </el-form>
      <p class="switch-link">
        已有账号？<router-link to="/login">去登录</router-link>
      </p>
    </div>
  </div>
</template>

<style scoped>
.register-page { display: flex; align-items: center; justify-content: center; min-height: 70vh; }
.form-card {
  width: 420px; background: var(--bg-card); border-radius: var(--radius);
  padding: 48px 40px; border: 1px solid rgba(255,255,255,0.06);
}
.form-card h2 { font-size: 2rem; text-align: center; margin-bottom: 6px; }
.subtitle { text-align: center; color: var(--text-muted); margin-bottom: 32px; font-size: 14px; }
.switch-link { text-align: center; margin-top: 24px; color: var(--text-muted); font-size: 14px; }
</style>
