<script setup>
import NavBar from './components/NavBar.vue'
</script>

<template>
  <div id="app-container">
    <NavBar />
    <main class="main-content">
      <router-view v-slot="{ Component }">
        <transition name="page" mode="out-in">
          <component :is="Component" />
        </transition>
      </router-view>
    </main>
  </div>
</template>

<style>
#app-container { min-height: 100vh; }
.main-content { max-width: 1280px; margin: 0 auto; padding: 20px var(--gap); }
</style>
