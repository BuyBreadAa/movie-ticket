const TOKEN_KEY = 'movie_token'

export function getToken() {
  return localStorage.getItem(TOKEN_KEY)
}

export function setToken(token) {
  localStorage.setItem(TOKEN_KEY, token)
}

export function clearToken() {
  localStorage.removeItem(TOKEN_KEY)
}

// 选座临时数据（sessionStorage，关闭标签页即清除）
const SEAT_KEY = 'seat_selection'

export function saveSeatSelection(data) {
  sessionStorage.setItem(SEAT_KEY, JSON.stringify(data))
}

export function getSeatSelection() {
  const raw = sessionStorage.getItem(SEAT_KEY)
  return raw ? JSON.parse(raw) : null
}

export function clearSeatSelection() {
  sessionStorage.removeItem(SEAT_KEY)
}
