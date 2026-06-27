import request from '../request'

export function getDashboard() {
  return request.get('/admin/dashboard')
}

export function getAdminMovies(params) {
  return request.get('/admin/movies', { params })
}

export function createMovie(data) {
  return request.post('/admin/movies', data)
}

export function updateMovie(id, data) {
  return request.put(`/admin/movies/${id}`, data)
}

export function deleteMovie(id) {
  return request.delete(`/admin/movies/${id}`)
}

export function getAdminSchedules(params) {
  return request.get('/admin/schedules', { params })
}

export function createSchedule(data) {
  return request.post('/admin/schedules', data)
}

export function getAdminOrders(params) {
  return request.get('/admin/orders', { params })
}

export function refundOrder(id) {
  return request.post(`/admin/orders/${id}/refund`)
}

export function getAdminUsers(params) {
  return request.get('/admin/users', { params })
}

export function updateUserStatus(id, data) {
  return request.put(`/admin/users/${id}/status`, data)
}
