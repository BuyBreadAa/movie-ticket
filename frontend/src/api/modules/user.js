import request from '../request'

// ponytail: 骨架阶段返回 mock 结构，后端就绪后替换为真实请求
export function login(data) {
  return request.post('/user/login', data)
}

export function register(data) {
  return request.post('/user/register', data)
}

export function getUserProfile() {
  return request.get('/user/profile')
}

export function updateUserProfile(data) {
  return request.put('/user/profile', data)
}

export function updatePassword(data) {
  return request.put('/user/password', data)
}

export function getMemberInfo() {
  return request.get('/user/member')
}
