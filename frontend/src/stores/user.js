import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { getToken, setToken, clearToken } from '../utils'
import { login as loginApi, getUserProfile, updateUserProfile, getMemberInfo } from '../api/modules/user'

export const useUserStore = defineStore('user', () => {
  const token = ref(getToken() || '')
  const userInfo = ref(null)
  const memberInfo = ref(null)

  const isLogin = computed(() => !!token.value)

  async function login(credentials) {
    const res = await loginApi(credentials)
    token.value = res.token
    setToken(res.token)
    await fetchUserInfo()
  }

  function logout() {
    token.value = ''
    userInfo.value = null
    memberInfo.value = null
    clearToken()
  }

  async function fetchUserInfo() {
    userInfo.value = await getUserProfile()
  }

  async function updateProfile(data) {
    userInfo.value = await updateUserProfile(data)
  }

  async function fetchMember() {
    memberInfo.value = await getMemberInfo()
  }

  return { token, userInfo, memberInfo, isLogin, login, logout, fetchUserInfo, updateProfile, fetchMember }
})
