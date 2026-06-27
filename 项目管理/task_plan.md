# 任务计划：电影推荐与票务系统

## 目标
完成 Vue3 + Spring Boot 电影票务 SPA 系统，3 人轮转开发 6 个模块。

## 当前阶段
阶段 3：模块一开发（zyy — 用户管理）

## 各阶段

### 阶段 1：需求分析与架构设计
- [x] 梳理需求文档
- [x] 系统架构设计（路由/状态/API/数据库）
- [x] 架构师审查（轻量化 + 安全 + 逻辑修复）
- [x] 更新 `系统架构/实现过程.md`
- **状态：** complete

### 阶段 2：骨架搭建 + 后端开发（A）
- [x] Vue3 + Vite 项目创建 + 依赖安装
- [x] 15 条路由 + 守卫（登录/管理员）
- [x] Pinia store（user / movie）
- [x] Axios 封装 + 7 个 API 模块 + Mock 系统
- [x] 11 个视图占位页面 + 3 个公共组件
- [x] 影院暗黑主题（theme.css + 页面美化）
- [x] Spring Boot 后端（8 实体 + 8 repo + 6 controller + JWT + H2）
- [x] ApiResponse 解包修复 + 6 个 bug 修复
- [x] Ponytail 审查清理
- **状态：** complete

### 阶段 3：模块一 — 用户管理（zyy）
- [ ] Login.vue — 登录表单 + 表单校验 + 调用 userStore.login
- [ ] Register.vue — 注册表单 + 密码强度校验 + 调用 register
- [ ] Profile.vue — 个人资料编辑 + 修改密码
- [ ] Member.vue — 会员中心（等级/积分/权益展示）
- [ ] 自检清单 10 项通过
- **状态：** in_progress

### 阶段 4：整合一（A）
- [ ] 拉取 zyy 代码
- [ ] 解决冲突（如有）
- [ ] 验证 `npm run build` 通过
- [ ] 全流程回归测试（登录→注册→资料→会员）
- **状态：** pending

### 阶段 5：模块二 — 影院与影片管理（fhf）
- [ ] 影片列表/详情 + 筛选
- [ ] 影院列表/详情
- [ ] 排片查询
- **状态：** pending

### 阶段 6：整合二（A）
- **状态：** pending

### 阶段 7：模块三 — 票务核心（A）
- [ ] SeatMap.vue — SVG 座位图 + 座位状态渲染
- [ ] Seat.vue — 选座流程 + 15 分钟倒计时
- [ ] Confirm.vue — 订单确认 + 积分抵扣 + 支付
- [ ] List.vue — 我的订单列表
- **状态：** pending

### 阶段 8：模块四 — 推荐系统（zyy）
- **状态：** pending

### 阶段 9：整合三（A）
- **状态：** pending

### 阶段 10：模块五 — 影评（fhf）
- **状态：** pending

### 阶段 11：整合四（A）
- **状态：** pending

### 阶段 12：模块六 — 后台管理（A）
- **状态：** pending

### 阶段 13：最终验收 + 答辩文档
- **状态：** pending

## 关键问题
1. zyy 和 fhf 什么时候开始开发？能按时提交吗？
2. 是否需要演示前部署？

## 已做决策
| 决策 | 理由 |
|------|------|
| 砍掉 social/ 独立模块 | 影评合并到影片详情，社区/好友不复存在 |
| 砍掉 coupons 表 | 优惠券是独立复杂功能 |
| 选座状态用 sessionStorage 而非 Pinia | 短暂 UI 状态，不适合全局持久化 |
| 前端 Mock 默认关闭 | 后端已完成，直接联调 |
| 后端用 H2 内嵌数据库 | 零配置，期末项目即开即用 |
| 密码从 data.sql 改为 DataInitializer 生成 | BCrypt 哈希需编码器生成 |

## 遇到的错误
| 错误 | 解决方案 |
|------|---------|
| `@Transactional` 导入 javax 包 → 事务不生效 | 改为 `org.springframework.transaction.annotation.Transactional` |
| 前端响应拦截器不解包 ApiResponse → 所有 API 返回 undefined | 新增 code===200 解包逻辑 |
| `m.getYear().equals(year)` NPE | 加 null 检查 |
| `order.getPayToken()` null 比较 NPE | 加 null 检查 |
| mock 字段 `cast` / `user` 与实体 `castList` / `userId` 不一致 | 全部对齐 |
| Maven 阿里云镜像超时 | 临时切 Maven Central，利用缓存 |

## 备注
- zyy 只改 `src/views/user/` 下 4 个文件
- fhf 只改 `src/views/movie/`、`src/views/cinema/`、`src/stores/movie.js`、`src/api/modules/movie.js`、`src/api/modules/cinema.js`
- 模块代码完成后再填写答辩文档
