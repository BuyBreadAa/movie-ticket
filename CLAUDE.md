# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

电影推荐与票务系统 — JavaScript 期末大作业。SPA 单页面应用。

## 技术栈

- **前端**: Vue3 + Vite + JavaScript + Element Plus + Axios
- **后端**: Java（Spring Boot 等）
- 前后端分离，前端通过 Axios 调用后端 REST API

## 开发命令

```bash
cd frontend && npm install          # 安装依赖
cd frontend && npm run dev          # 启动开发服务器
cd frontend && npm run build        # 生产构建
```

## 开发流程（强制）

本项目遵循**设计先行**流程，每次开发新模块必须按顺序执行：

1. **Phase 0**: 理解当前上下文（读取已有代码、文档）
2. **Phase 1**: 编写设计文档到 `系统架构/实现过程.md`
3. **Phase 2**: 用户审阅设计文档
4. **Phase 3**: 自审设计 — 检查接口契约、数据流、边界情况
5. **Phase 4**: 修复设计问题
6. **Phase 5**: 用户确认设计
7. **Phase 6**: 开始编码

**重要**: 模块代码完成并验收通过后，再将完整文档写入对应人员的答辩文档（如 `张杨扬的开发文档/模块X-XXX.md`）。答辩文档需要基于实际代码来写。

## 团队分工与模块轮转

3 人轮流开发 JS 模块，用户 A 负责搭建骨架和整合：

| 轮次 | 成员 A（用户） | 成员 zyy | 成员 fhf |
|------|--------------|---------|---------|
| 第一轮 | 搭建整体骨架 + 整合 | 模块一 | 模块二 |
| 第二轮 | 模块三 + 整合 | 模块四 | 模块五 |
| 第三轮 | 模块六 + 整合 | — | — |

按需求文档，六大模块：
1. 用户管理模块（注册登录、会员、积分）
2. 影院与影片管理（影院/影厅/座位/排片、影片信息/评分）
3. 票务核心功能（选座购票、订单、支付）
4. 推荐系统（内容推荐、协同过滤、热门推荐）
5. 营销与社交功能（优惠券、影评、好友、社区）
6. 后台管理功能（数据看板、内容管理、运营管理）

## 架构约定

- SPA 单页面，Vue Router 管理路由
- Element Plus 作为 UI 组件库，优先使用其内置组件
- 座位图使用 SVG/Canvas 实现可视化选座
- 支付倒计时 15 分钟
- 前端状态管理使用 Pinia（Vue3 官方推荐）
