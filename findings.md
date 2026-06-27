# 发现与决策

## 需求
- 电影推荐与票务系统 SPA
- 6 大模块：用户管理、影院影片、票务核心、推荐系统、影评、后台管理
- 3 人轮转开发，A 负责骨架+整合+模块三/六

## 研究发现

### 架构审查发现
- social/ 是范围蔓延 → 砍掉独立页面，影评合并到影片详情
- 35 个 API 可精简到 ~25 → 砍验证码/重置密码/签到/想看/社交
- orderStore 选座状态不适合 Pinia → 改用 sessionStorage
- 6 轮分工可缩减但保留（团队开发便利）

### 安全审查发现
- Token localStorage 存 XSS 风险 → 备选 httpOnly cookie，期末阶段用 localStorage + CSP 提示
- 支付需幂等键 → payToken 唯一约束
- 角色提权防护 → PUT /profile 白名单字段

## 技术决策
| 决策 | 理由 |
|------|------|
| Vue3 + Vite + Element Plus + Pinia | 期末要求技术栈 |
| Spring Boot 3.3 + JPA + H2 | 后端简单实现，零配置 |
| JWT Bearer Token 认证 | 无状态，前端存 localStorage |
| BCrypt 密码编码 | 安全基线 |
| Axios 拦截器解包 ApiResponse.data | 前端代码不用 .data.xxx，简化调用 |
| 全局 USE_MOCK 开关 | 一键切换前端独立/联调模式 |
| H2 内存数据库 + DataInitializer | 种子数据含 2 用户 8 影片 4 影院 |

## 遇到的问题
| 问题 | 解决方案 |
|------|---------|
| 前后端 ApiResponse 包裹/解包不匹配 | 响应拦截器解包 body.data |
| Movie entity 字段名 castList vs mock cast | 统一为 castList |
| Maven 阿里云镜像连不上 | 临时注释镜像，用 Maven Central |
| Git Bash 路径中文编码 | Windows CRLF 警告可忽略 |

## 资源
- GitHub: https://github.com/BuyBreadAa/movie-ticket
- Element Plus 文档: https://element-plus.org/zh-CN/
- Vue 3 文档: https://cn.vuejs.org/
- Pinia 文档: https://pinia.vuejs.org/zh/
- Spring Boot 3.3: https://docs.spring.io/spring-boot/

## 视觉/浏览器发现
- 影院暗黑主题（#0a0a0f + 聚光灯金 #d4a843）已应用
- Hero 聚光灯扫射动画 + 卡片呼吸发光效果
- Element Plus 暗色变量已覆写
- 页面切换 fade-rise 过渡已配置
