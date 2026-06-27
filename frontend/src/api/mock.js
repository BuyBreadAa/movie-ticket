// ponytail: 全局 mock，后端就绪后改 USE_MOCK=false 即可切回真实请求
export const USE_MOCK = false

// key: "METHOD /path"（不含 query string）
const mocks = {

  // ═══ 用户模块 ═══
  'POST /user/login': {
    token: 'mock-jwt-token-abc123'
  },
  'POST /user/register': { ok: true },
  'GET /user/profile': { id: 1, nickname: '影迷小明', phone: '13800138000', email: 'test@movie.com', avatar: '', role: 'user', level: 'VIP会员', points: 1280 },
  'PUT /user/profile': { id: 1, nickname: '影迷小明', phone: '13800138000', email: 'test@movie.com', avatar: '', role: 'user', level: 'VIP会员', points: 1280 },
  'PUT /user/password': { ok: true },
  'GET /user/member': { level: 'VIP会员', points: 1280, discount: 0.85, nextLevel: '超级VIP', nextNeed: 720 },

  // ═══ 影片模块 ═══
  'GET /movie/list': {
    list: [
      { id: 1, title: '星际穿越', poster: '', type: '科幻', region: '美国', year: 2014, rating: 9.4 },
      { id: 2, title: '肖申克的救赎', poster: '', type: '剧情', region: '美国', year: 1994, rating: 9.7 },
      { id: 3, title: '千与千寻', poster: '', type: '动画', region: '日本', year: 2001, rating: 9.4 },
      { id: 4, title: '盗梦空间', poster: '', type: '科幻', region: '美国', year: 2010, rating: 9.3 },
      { id: 5, title: '泰坦尼克号', poster: '', type: '爱情', region: '美国', year: 1997, rating: 9.4 },
      { id: 6, title: '楚门的世界', poster: '', type: '剧情', region: '美国', year: 1998, rating: 9.3 },
      { id: 7, title: '放牛班的春天', poster: '', type: '音乐', region: '法国', year: 2004, rating: 9.3 },
      { id: 8, title: '疯狂动物城', poster: '', type: '动画', region: '美国', year: 2016, rating: 9.2 },
    ]
  },
  'GET /movie/1': { id: 1, title: '星际穿越', poster: '', trailer: '', director: '克里斯托弗·诺兰', castList: '马修·麦康纳, 安妮·海瑟薇', type: '科幻', region: '美国', year: 2014, synopsis: '一组宇航员穿越虫洞，为人类寻找新家园。', rating: 9.4 },
  'GET /movie/2': { id: 2, title: '肖申克的救赎', poster: '', trailer: '', director: '弗兰克·德拉邦特', castList: '蒂姆·罗宾斯, 摩根·弗里曼', type: '剧情', region: '美国', year: 1994, synopsis: '一个银行家被误判谋杀，在监狱中寻找希望。', rating: 9.7 },
  'GET /movie/3': { id: 3, title: '千与千寻', poster: '', trailer: '', director: '宫崎骏', castList: '柊瑠美, 入野自由', type: '动画', region: '日本', year: 2001, synopsis: '女孩千寻误入神灵世界，为救父母而工作。', rating: 9.4 },
  'GET /movie/4': { id: 4, title: '盗梦空间', poster: '', trailer: '', director: '克里斯托弗·诺兰', castList: '莱昂纳多·迪卡普里奥', type: '科幻', region: '美国', year: 2010, synopsis: '一个专门从他人梦中窃取秘密的小组。', rating: 9.3 },
  'GET /movie/1/reviews': { list: [{ id: 1, userId: 2, rating: 5, content: '震撼的视觉体验，诺兰最佳作品之一', createdAt: '2026-06-20' }] },
  'GET /movie/2/reviews': { list: [{ id: 2, userId: 2, rating: 5, content: '希望是美好的，也许是最美好的东西', createdAt: '2026-06-18' }] },
  'POST /movie/1/review': { ok: true },
  'POST /movie/2/review': { ok: true },

  // ═══ 影院模块 ═══
  'GET /cinema/list': {
    list: [
      { id: 1, name: '万达影城（朝阳店）', address: '朝阳区建国路93号万达广场B1', contact: '010-88886666', facilities: 'IMAX, 杜比全景声, 4K激光', city: '北京', region: '朝阳区' },
      { id: 2, name: 'CGV影城（三里屯店）', address: '朝阳区工体北路甲2号', contact: '010-66668888', facilities: 'IMAX, ScreenX, 4D', city: '北京', region: '朝阳区' },
      { id: 3, name: '博纳国际影城（中关村店）', address: '海淀区中关村大街19号', contact: '010-55556666', facilities: 'IMAX, 杜比全景声', city: '北京', region: '海淀区' },
      { id: 4, name: 'UME国际影城（双井店）', address: '朝阳区东三环中路65号', contact: '010-33336666', facilities: 'IMAX, 中国巨幕, 4K', city: '北京', region: '朝阳区' },
    ]
  },
  'GET /cinema/1': { id: 1, name: '万达影城（朝阳店）', address: '朝阳区建国路93号万达广场B1', contact: '010-88886666', facilities: 'IMAX, 杜比全景声, 4K激光', halls: [{ id: 1, name: '1号IMAX厅', type: 'IMAX', capacity: 200 }, { id: 2, name: '2号普通厅', type: '普通', capacity: 120 }] },
  'GET /cinema/1/schedule': { list: [{ id: 1, movieId: 1, movieTitle: '星际穿越', hallId: 1, hallName: '1号IMAX厅', startTime: '2026-06-28 14:30', price: 69.9 }, { id: 2, movieId: 1, movieTitle: '星际穿越', hallId: 2, hallName: '2号普通厅', startTime: '2026-06-28 19:00', price: 49.9 }] },

  // ═══ 订单模块 ═══
  'POST /order/lock-seats': { lockToken: 'lock-uuid-123', expireAt: Date.now() + 900000 },
  'POST /order/create': { id: 1001, status: 'PENDING', payToken: 'pay-uuid-456', totalPrice: 69.9 },
  'POST /order/1001/pay': { ok: true, status: 'PAID', ticketCode: '880624' },
  'GET /order/1001': { id: 1001, movieTitle: '星际穿越', cinemaName: '万达影城（朝阳店）', hallName: '1号IMAX厅', startTime: '2026-06-28 14:30', seats: ['5排6座'], totalPrice: 69.9, status: 'PAID', ticketCode: '880624' },
  'GET /order/list': { list: [{ id: 1001, movieTitle: '星际穿越', startTime: '2026-06-28 14:30', seats: ['5排6座'], totalPrice: 69.9, status: 'PAID' }] },

  // ═══ 推荐模块 ═══
  'GET /recommend/home': {
    hot: [{ id: 1, title: '星际穿越', rating: 9.4 }, { id: 2, title: '肖申克的救赎', rating: 9.7 }, { id: 3, title: '千与千寻', rating: 9.4 }, { id: 4, title: '盗梦空间', rating: 9.3 }],
    recommend: [{ id: 5, title: '泰坦尼克号', rating: 9.4 }, { id: 6, title: '楚门的世界', rating: 9.3 }, { id: 7, title: '放牛班的春天', rating: 9.3 }, { id: 8, title: '疯狂动物城', rating: 9.2 }]
  },
  'GET /recommend/similar/1': [{ id: 4, title: '盗梦空间', rating: 9.3 }, { id: 2, title: '肖申克的救赎', rating: 9.7 }],
  'GET /recommend/hot': [{ id: 1, title: '星际穿越', rating: 9.4 }, { id: 2, title: '肖申克的救赎', rating: 9.7 }, { id: 3, title: '千与千寻', rating: 9.4 }],

  // ═══ 后台模块 ═══
  'GET /admin/dashboard': { todayOrders: 128, todayBoxOffice: 8963, activeUsers: 3421, occupancyRate: 0.72 },
  'GET /admin/movies': { list: [] },
  'GET /admin/schedules': { list: [] },
  'GET /admin/orders': { list: [] },
  'GET /admin/users': { list: [{ id: 1, nickname: '影迷小明', email: 'test@movie.com', role: 'user', status: 'active' }, { id: 2, nickname: '管理员', email: 'admin@movie.com', role: 'admin', status: 'active' }] },
}

export function matchMock(method, url) {
  const key = method + ' ' + url
  return key in mocks ? { data: mocks[key] } : undefined
}
