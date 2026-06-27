import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getMovieList, getMovieDetail } from '../api/modules/movie'
import { getHotList, getHomeRecommend, getSimilarMovies } from '../api/modules/recommend'

export const useMovieStore = defineStore('movie', () => {
  const hotList = ref([])
  const recommendList = ref([])
  const currentMovie = ref(null)
  const similarMovies = ref([])

  async function fetchHot() {
    hotList.value = await getHotList()
  }

  async function fetchRecommend() {
    recommendList.value = await getHomeRecommend()
  }

  async function fetchDetail(id) {
    currentMovie.value = await getMovieDetail(id)
  }

  async function fetchSimilar(id) {
    similarMovies.value = await getSimilarMovies(id)
  }

  async function fetchList(params) {
    return await getMovieList(params)
  }

  return { hotList, recommendList, currentMovie, similarMovies, fetchHot, fetchRecommend, fetchDetail, fetchSimilar, fetchList }
})
