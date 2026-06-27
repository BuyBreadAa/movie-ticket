import request from '../request'

export function getMovieList(params) {
  return request.get('/movie/list', { params })
}

export function getMovieDetail(id) {
  return request.get(`/movie/${id}`)
}

export function getMovieReviews(movieId, params) {
  return request.get(`/movie/${movieId}/reviews`, { params })
}

export function postMovieReview(movieId, data) {
  return request.post(`/movie/${movieId}/review`, data)
}
