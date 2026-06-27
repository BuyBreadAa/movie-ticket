import axios from 'axios'
import { getToken, clearToken } from '../utils'
import { USE_MOCK, matchMock } from './mock'

const request = axios.create({
  baseURL: '/api',
  timeout: 10000,
})

request.interceptors.request.use(
  (config) => {
    const token = getToken()
    if (token) config.headers.Authorization = `Bearer ${token}`
    return config
  },
  (error) => Promise.reject(error)
)

request.interceptors.response.use(
  (response) => {
    const body = response.data
    // 解包 ApiResponse: {code, message, data} → data
    if (body && typeof body.code === 'number' && body.code !== 200) {
      return Promise.reject(new Error(body.message || '请求失败'))
    }
    return body && 'data' in body ? body.data : body
  },
  (error) => {
    if (error.response?.status === 401) {
      clearToken()
      window.location.href = '/login'
    }
    return Promise.reject(error)
  }
)

// ponytail: mock 拦截，后端就绪后改 USE_MOCK=false 即可
if (USE_MOCK) {
  const _get = request.get.bind(request)
  const _post = request.post.bind(request)
  const _put = request.put.bind(request)
  const _delete = request.delete.bind(request)

  request.get = (url, config) => mockOrReal('GET', url, () => _get(url, config))
  request.post = (url, data, config) => mockOrReal('POST', url, () => _post(url, data, config))
  request.put = (url, data, config) => mockOrReal('PUT', url, () => _put(url, data, config))
  request.delete = (url, config) => mockOrReal('DELETE', url, () => _delete(url, config))
}

function mockOrReal(method, url, realCall) {
  const mock = matchMock(method, url)
  if (mock !== undefined) return Promise.resolve(mock)
  return realCall()
}

export default request
