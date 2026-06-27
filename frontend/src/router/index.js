import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '../stores/user'

const routes = [
  { path: '/', name: 'Home', component: () => import('../views/Home.vue') },
  { path: '/movies', name: 'MovieList', component: () => import('../views/movie/List.vue') },
  { path: '/movie/:id', name: 'MovieDetail', component: () => import('../views/movie/Detail.vue') },
  { path: '/cinemas', name: 'CinemaList', component: () => import('../views/cinema/List.vue') },
  {
    path: '/order/seat/:showId', name: 'SeatSelect',
    component: () => import('../views/order/Seat.vue'), meta: { requiresAuth: true }
  },
  {
    path: '/order/confirm/:orderId', name: 'OrderConfirm',
    component: () => import('../views/order/Confirm.vue'), meta: { requiresAuth: true }
  },
  {
    path: '/orders', name: 'OrderList',
    component: () => import('../views/order/List.vue'), meta: { requiresAuth: true }
  },
  { path: '/login', name: 'Login', component: () => import('../views/user/Login.vue') },
  { path: '/register', name: 'Register', component: () => import('../views/user/Register.vue') },
  {
    path: '/user/profile', name: 'Profile',
    component: () => import('../views/user/Profile.vue'), meta: { requiresAuth: true }
  },
  {
    path: '/user/member', name: 'Member',
    component: () => import('../views/user/Member.vue'), meta: { requiresAuth: true }
  },
  {
    path: '/admin/dashboard', name: 'AdminDashboard',
    component: () => import('../views/admin/Dashboard.vue'), meta: { requiresAuth: true, requiresAdmin: true }
  },
  {
    path: '/admin/movies', name: 'AdminMovies',
    component: () => import('../views/admin/MovieManage.vue'), meta: { requiresAuth: true, requiresAdmin: true }
  },
  {
    path: '/admin/orders', name: 'AdminOrders',
    component: () => import('../views/admin/OrderManage.vue'), meta: { requiresAuth: true, requiresAdmin: true }
  },
  {
    path: '/admin/users', name: 'AdminUsers',
    component: () => import('../views/admin/UserManage.vue'), meta: { requiresAuth: true, requiresAdmin: true }
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

router.beforeEach(async (to, from, next) => {
  if (to.meta.requiresAuth) {
    const userStore = useUserStore()

    if (!userStore.token) {
      return next({ path: '/login', query: { redirect: to.fullPath } })
    }
    // 刷新后 userInfo 为空，先拉取最新信息再判角色
    if (!userStore.userInfo) {
      try {
        await userStore.fetchUserInfo()
      } catch {
        userStore.logout()
        return next({ path: '/login', query: { redirect: to.fullPath } })
      }
    }
    if (to.meta.requiresAdmin && userStore.userInfo.role !== 'admin') {
      return next('/')
    }
  }
  next()
})

export default router
