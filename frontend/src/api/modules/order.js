import request from '../request'

export function lockSeats(data) {
  return request.post('/order/lock-seats', data)
}

export function createOrder(data) {
  return request.post('/order/create', data)
}

export function payOrder(id, data) {
  return request.post(`/order/${id}/pay`, data)
}

export function cancelOrder(id) {
  return request.post(`/order/${id}/cancel`)
}

export function getOrderDetail(id) {
  return request.get(`/order/${id}`)
}

export function getOrderList(params) {
  return request.get('/order/list', { params })
}
