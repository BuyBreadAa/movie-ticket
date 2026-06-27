# 模块一：用户管理 — 开发指南

**负责人**: zyy ｜ **提交截止**: 第一轮结束前

---

## 1. 获取源码

```bash
# 方式 A：从共享文件夹/USB 复制整个项目目录
# 方式 B：如果已配 Git remote
git clone <仓库地址>
cd js期末大作业/frontend
npm install
```

## 2. 启动开发环境

```bash
cd frontend
npm install        # 仅首次
npm run dev        # → http://localhost:5173
```

> 如果后端未启动，将 `frontend/src/api/mock.js` 第2行改为 `export const USE_MOCK = true` 即可独立开发。

## 3. 你只改这 4 个文件

```
src/views/user/
├── Login.vue      ← 登录页
├── Register.vue   ← 注册页
├── Profile.vue    ← 个人资料编辑
└── Member.vue     ← 会员中心
```

**不要改**以下文件（已由 A 完成，改了会冲突）：
- `src/router/index.js` — 路由表
- `src/stores/user.js` — Pinia 用户状态管理
- `src/api/modules/user.js` — 后端接口调用
- `src/api/request.js` — Axios 封装
- `src/styles/theme.css` — 全局样式
- 所有 `src/views/` 下非 `user/` 的页面
- 所有后端 Java 文件

## 4. 可用的 API（已封装好，直接 import 调用）

```js
import { login, register, getUserProfile, updateUserProfile, updatePassword, getMemberInfo } from '../../api/modules/user'
```

| 函数 | 参数 | 返回 |
|------|------|------|
| `login({ account, password })` | `account`: 手机号或邮箱, `password`: 密码 | `{ token: "eyJ..." }` |
| `register({ phone, email, password, nickname })` | 密码需 ≥8位 + 含字母和数字 | `{ ok: true }` |
| `getUserProfile()` | 无 | `{ id, phone, email, nickname, avatar, role, level, points }` |
| `updateUserProfile({ nickname, avatar, phone, email })` | 仅传要改的字段 | 更新后的 user 对象 |
| `updatePassword({ password })` | 新密码 | `{ ok: true }` |
| `getMemberInfo()` | 无 | `{ level, points, nextLevel, nextNeed }` |

> 返回值已经过拦截器解包，直接拿到 data 内层对象，无需 `.data.xxx`。

## 5. 可用的 Store（已封装好）

```js
import { useUserStore } from '../../stores/user'

const userStore = useUserStore()

// 状态
userStore.token          // JWT token 字符串
userStore.userInfo       // 用户对象 { id, phone, email, nickname, avatar, role, level, points }
userStore.memberInfo     // 会员对象 { level, points, nextLevel, nextNeed }
userStore.isLogin        // computed: !!token

// 方法
await userStore.login({ account, password })   // 登录 + 自动存 token + 自动拉 userInfo
userStore.logout()                              // 清空所有状态 + 清除 token
await userStore.fetchUserInfo()                 // 重新拉取用户信息
await userStore.updateProfile(data)             // 更新资料（自动更新本地 userInfo）
await userStore.fetchMember()                   // 拉取会员信息
```

## 6. 页面要求

### Login.vue — 登录
- **表单字段**: 账号（手机号/邮箱）+ 密码
- **提交**: 调用 `userStore.login({ account, password })`
- **成功后**: `router.push('/')` 跳转首页
- **失败**: 用 `ElMessage.error('账号或密码错误')` 提示
- **已登录**: 如果 `userStore.isLogin` 为 true，自动跳转到 `/`

### Register.vue — 注册
- **表单字段**: 手机号 + 邮箱（选填）+ 密码 + 确认密码
- **前端校验**:
  - 密码 ≥8 位
  - 密码必须包含字母和数字
  - 两次密码一致
- **提交**: 调用 `register({ phone, email, password, nickname })`
- **成功后**: 提示"注册成功"，跳转登录页
- **失败**: `ElMessage.error` 提示

### Profile.vue — 个人资料
- **进入页面时**: 调用 `userStore.fetchUserInfo()` 加载最新数据
- **表单字段**: 头像（可先用占位）、昵称、手机号、邮箱
- **保存**: 调用 `userStore.updateProfile(data)`
- **修改密码**: 可在此页或单独区域，调用 `updatePassword({ password })`

### Member.vue — 会员中心
- **进入页面时**: 调用 `userStore.fetchMember()` 加载会员数据
- **展示**: 会员等级、积分、下一等级、距升级还需积分
- **可选**: 积分记录列表（如时间充裕）

## 7. UI 规范

- **组件库**: Element Plus（已全局注册，直接用 `<el-xxx>`）
- **设计令牌**: CSS 变量已定义在 `theme.css`
  ```css
  --bg-deep / --bg-card    /* 背景色 */
  --text-primary / --text-secondary / --text-muted  /* 文字色 */
  --gold: #d4a843          /* 主题金 */
  --font-display: Georgia serif   /* 标题字体 */
  --font-body: system-ui          /* 正文字体 */
  --radius: 12px                 /* 圆角 */
  ```
- **消息提示**: `import { ElMessage, ElMessageBox } from 'element-plus'`
- **路由跳转**: `import { useRouter } from 'vue-router'` → `router.push('/xxx')`

## 8. 测试账号

| 角色 | 账号 | 密码 |
|------|------|------|
| 管理员 | `13800138000` | `12345678` |
| 普通用户 | `13900139000` | `12345678` |

## 9. 开发自检清单

- [ ] 可以用测试账号 / 新注册账号登录
- [ ] 登录失败有错误提示
- [ ] 登录成功后导航栏显示用户昵称
- [ ] 注册表单校验正常（密码强度、两次输入一致）
- [ ] 个人资料可编辑保存
- [ ] 密码可修改
- [ ] 会员中心显示等级和积分
- [ ] 未登录时访问 `/user/profile` 自动跳转登录页
- [ ] 页面风格与整体暗黑主题一致
- [ ] 控制台无报错

## 10. 提交方式

提交你修改的 4 个 `.vue` 文件。不要提交 `node_modules/`、`dist/` 或其他文件。
