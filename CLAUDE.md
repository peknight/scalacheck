# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 模块定位

ScalaCheck 扩展模块，暴露 ScalaCheck 库的 package-private `Gen` 内部 API（`Gen.gen`、`Gen.doApply`），供 `gen` 模块构建自定义 Gen 时使用。通过将代码放在 `org.scalacheck` 包下实现访问。

## 构建

```bash
sbt clean compile          # 编译
sbt test                   # 测试（当前无测试）
sbt +publishLocal          # 跨 Scala 版本发布到本地
```

跨平台构建（JVM / Scala.js / Scala Native），源码全部在 `scalacheck-core/shared/`，无平台特定代码。

## 架构

```
scalacheck/                          # 根项目，聚合三个平台子项目
  scalacheck-core/
    shared/src/main/scala/org/scalacheck/
      GenOps.scala                   # 唯一源文件：暴露 Gen 内部 API
    jvm/ | js/ | native/             # 平台子项目，无额外源码
```

**GenOps** 提供两个方法：
- `gen[T]((Parameters, Seed) => R[T]): Gen[T]` — 从底层生成函数构造 Gen（包装 `Gen.gen`）
- `doApply[T](gen)(Parameters, Seed): R[T]` — 用给定参数和种子求值 Gen（包装 `Gen.doApply`）

## 依赖

- 仅依赖 `org.scalacheck::scalacheck`（通过 `gav.scalaCheck` 引入，版本在 `build-gav` 中管理）
- Native 平台通过 `crossDependencyOverrides` 解决 scala-native test-interface 版本冲突
