import request from '../request'

export function getHomeRecommend() {
  return request.get('/recommend/home')
}

export function getSimilarMovies(movieId) {
  return request.get(`/recommend/similar/${movieId}`)
}

export function getHotList() {
  return request.get('/recommend/hot')
}
