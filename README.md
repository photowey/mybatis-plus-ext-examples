# `Mybatis-Plus` 扩展项目 `Mybatis-Plus-Ext` 示例工程集

-- -
## 子项目
- `mybatis-plus-ext-example-condition-annotation`  //  测试条件注解(`@Eq @Like ...`)  
- `mybatis-plus-ext-example-mapper`  // 扩展 `BaseMapper`  
- `mybatis-plus-ext-example-meta`  // 抽象 `MetaObjectHandler`  
- `mybatis-plus-ext-example-mysql`  // `mysql` 数据访问依赖  
- `mybatis-plus-ext-example-query`  // 扩展 `QueryWrapper` 和 `LambdaQueryWrapper`  
- `mybatis-plus-ext-example-web`  // `web` 项目依赖  



## 测试

```shell
# 1.Clone
$ git clone https://github.com/photowey/mybatis-plus-ext.git

# 2.Install
$ mvn clean -DskipTests source:jar deploy
# Or
$ cd ./mybatis-plus-ext
$ ./release.cmd

# 3.测试
# 见对应项目的 ut
```

