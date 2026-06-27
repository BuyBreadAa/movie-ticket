import request from '../request'

export function getCinemaList(params) {
  return request.get('/cinema/list', { params })
}

export function getCinemaDetail(id) {
  return request.get(`/cinema/${id}`)
}

export function getCinemaSchedule(cinemaId, params) {
  return request.get(`/cinema/${cinemaId}/schedule`, { params })
}
