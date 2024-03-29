<p align="center">
  <a href="https://search.maven.org/artifact/com.easymybatis.freamwork/spring-easymybatis-core">
    <img alt="996icu" src="https://img.shields.io/maven-central/v/com.easymybatis.freamwork/spring-easymybatis-core">
  </a>
  <a href="https://github.com/996icu/996.ICU/blob/master/LICENSE">
    <img alt="996icu" src="https://img.shields.io/badge/license-NPL%20(The%20996%20Prohibited%20License)-blue.svg">
  </a>

  <a href="https://github.com/onlyGuo/easymybatis/blob/master/LICENSE">
    <img alt="code style" src="https://img.shields.io/github/license/onlyGuo/nginx-gui.svg?style=popout">
  </a>
</p>

使用EasyMybatis, 可以省去你编写mapper文件的烦恼. 甚至省去你自动生成代码的操作! 你无需再因为数据库中某个字段的改动而去修改编大量的xml, 甚至你无需生成死板的sql模板和mapper文件, EasyMybatis在每次程序启动时会根据你的实体类在内存中直接生成你需要的SQL, 和Mapper. 他不会释放文件到内存, 也不会生成繁琐的代码, 使你的项目看起来更清爽.

## 解决的痛点
- 不用每个实体类都要定义一个mapper文件.
- 不用编写重复的增删改查的SQL.
- 不用自动生成繁琐的且重复的增删改查的Mapper文件.
- 降低Mapper文件数, 降低代码行数, 提高阅读性.
- 动态在内存中生成Mapper副本, 不用因为数据库中一个字段的微小改动而去修改大量的Mapper文件.
- 降低了SQL后期的维护风险.
- 根据业务动态生成理想的SQL.
- 防止某类SQL拼接造成的SQL注入风险.
- 实现以上功能时, 保留了Mybatis原生的所有特性.
- 可以抽出更多时间陪你的夫人.

## 交流QQ群：
274862188

## 如何使用?
#### 通过maven直接引入
在你的项目依赖中直接引入如下依赖即可

````xml
<!-- EasyBatis集成 -->
<dependency>
    <groupId>com.easymybatis.freamwork</groupId>
    <artifactId>spring-easymybatis-core</artifactId>
    <version>0.3.8.RELEASE</version>
</dependency>
````
bbb
#### 通过Gradle直接引入
````
implementation 'com.easymybatis.freamwork:spring-easymybatis-core:0.3.8.RELEASE'
````

#### 拉取本项目的GitHub代码, 编译并安装到你的项目中

1. 拉取 https://github.com/onlyGuo/easymybatis.git
2. 执行`mvn clean install`以安装到你的仓库中
3. 如果你是maven项目, 在pom中引入依赖; 如果你是javaEE项目, 将你编译好的jar包放到lib目录中.

## 实体类定义
为保证实体类多项目的通用性, 不要将新的实体类定义到你应用的项目中.
应抽离出来, 定义在一个独立的项目中, 以此来供给多个项目引用使用. 
主要解决以下问题:
- 方便管理;
- 避免了在项目之间通信的时候, 由于两方所使用的实体类字段不一致导致数据异常的问题
- 避免了实体类字段发生变动时, 每个项目都需要修改一遍的问题.

#### 命名规范
- 持久化对象(对应数据库的表): XXXPO
- 值对象(可以是任何场景中的值): XXXVO
- 系列化对象(跨服务之间的通信对象, 或与客户端通信的对象) XXXDTO

> 一般情况下, 使用VO即可, 在复杂场景中, 可以PO与VO分开来使用. 在一些特殊场景中, (例如统计图等相关接口中) 需要给客户端传利高度封装的定制对象时使用DTO.

#### 一个复杂的实体类定义
````java
/**
 * @author gsk 
 * `@TableName`注解中的name值 标识该实体类对应数据库中的表名
 * 继承PO类, 则表示这个实体类可以对数据库中对应的表进行操作
 */
@TableName(name = "persion_info")
public class PersionPO extends PO{
    
    /**
     * 对应数据库的主键字段(该注解支持字段的类型有: int, long, String)
     */
    @ID
    private Long id;
    
    /**
     * `@FieldName`注解中的name值 标识该字段对应数据库表中的指定字段名
     */
    @FieldName(name = "persion_name")
    private String name;
    
    /**
     * 不使用@FieldName 注解时, 系统会使用当前字段名作为数据库表中的字段名进行操作
     * (会转换为下划线命名方式)
     * 例如该字段, 系统会默认认为它对应数据库的字段名为`age_num`
     */
    private String ageNum;
    
    /**
     * 该`@TempField`注解标识这个字段不参与数据库操作.
     */
    @TempField
    private int nicker;
    
    // getter .... setter
    
}
````

## 数据库操作类(DAO)
#### 创建DAO
简单而言, 数据库操作类直接继承BaseDaoImpl即可实现几乎所有的数据库操作, 
尽量避免使用自动生成Mapper, 避免日后实体类有改动时, Mapper要同步修改, 
避免实体类和mapper不同步问题. 代码如下: 
````java
/**
 * @author gsk 
 */
@Repository
public class PersionDao extends BaseDaoImpl<PersionPO, Long> {
    // 此处无需写过多的代码. BaseDaoImpl本身实现了公共的数据库操作.
}
````
#### 声明和使用DAO
一个简单的代码实例, 无需写SQL, 简单的一行代码就实现了多条件, 分页, 排序等功能: 
````java
/**
 * @author gsk 
 */
@Service
public class PersionServiceImpl implements PersionService {
    
    @Resource
    private PersionDao persionDao;
    
    /**
     * 列出年轻的小姐姐列表, 并案年龄倒序排序
     * @param pageNum
     *      页码
     * @param length
     *      每页长度
     * @return 
     */
    public List<PersionPO> listYoungGirl(int pageNum, int length){
        long startNum = (pageNum - 1) * length;
        // 18岁及18岁以下的定义为年轻, sex = 0 定义为女
        return persionDao.list(Method.where("age_num", C.XE, 18).and("sex", C.EQ, 0).limit(startNum, length).orderBy("age_num desc"));
    }
}
````
此外, easymybatis 已经提供了分页查询的方法, 因此, 以上代码可以写成这样:
````java
/**
 * @author gsk 
 */
@Service
public class PersionServiceImpl implements PersionService {
    
    @Resource
    private PersionDao persionDao;
    
    /**
     * 列出年轻的小姐姐列表, 并案年龄倒序排序
     * @param pageNum
     *      页码
     * @param length
     *      每页长度
     * @return 
     */
    public ResultPage<PersionPO> listYoungGirl(int pageNum, int length){
        // 18岁及18岁以下的定义为年轻, sex = 0 定义为女
        return persionDao.list(Method
            .where("age_num", C.XE, 18)
            .and("sex", C.EQ, 0)
            .orderBy("age_num desc"), pageNum, length);
    }
}
````
`ResultPage` 是一个泛型的Bean容器, 中包含一个`list`, `thisPage`, `totalPageSize`, `totalSize`, `pageSize`等字段
#### DAO中的Method表达式
> DAO中, 大部分的操作会用到Method表达式, Method表达式为链式操作, 且不用在意顺序. 
可以根据业务判断和流程在不同的场景中动态构建你所需的`SQL WHERE 表达式`, 它表达了我们要查询的数据库的目标.
> 主要分为[字段名, 比较符, 比较值] 三部分.

示例(目标为: 18岁的人群中的小姐姐按照年龄倒序的前十条):
````java
// 18岁的人群
WherePrams where = Method.where("age_num", C.EQ, 18);
// 中的小姐姐
where.and("sex", C.EQ, 0);
// 按照年龄倒序
where.orderBy("age_num desc");
// 的前十条
where.limit(0, 10);
````
#### 解决Method表达式中的硬编码
> 以上示例中都存在了字段的硬编码, 这与EasyMybatis的原则相违背, 因此建议使用EasyMybatis提供的SFun进行强约束编程.

以上代码适当修改后如下示例:
````java
// 18岁的人群
WherePrams where = Method.where(PersionPO:getAgeNum, C.EQ, 18);
// 中的小姐姐
where.and(PersionPO:getSex, C.EQ, 0);
// 按照年龄倒序
where.orderBy(Sort.of(PersionPO:getAgeNum, OrderBy.DESC));
// 的前十条
where.limit(0, 10);
````


> 当不确定首要条件的时候, 可以用createDefault()方法创建一个无目标的表达式.

示例(当传入年龄 <= 18时, 取出10个18岁小姐姐, 否则取出20个指定年龄的小哥哥)
````java
public List<PersionPO> list(int age){
    WherePrams where = Method.createDefault();
    
    if(age <= 18){
        where.and(PersionPO:getAgeNum, C.EQ, 18).and(PersionPO:getSex, C.EQ, 0).limit(0, 10);
    }else{
        where.and(PersionPO:getAgeNum, C.EQ, age).and(PersionPO:getSex, C.EQ, 1).limit(0, 20);
    }
    
    return persionDao.list(where);
}
````
#### DAO 的高级用法(联表查询)
> 通常情况下, 在一些稍微复杂的场景中, 会用到联表. EasyMybatis当前提供了基于Left Join的联表查询方案.
只需要在查询方法中加入以下表达式:

````java
Join.LeftJoin(
    <T extends PO> <T>Class 外表class, 
    WhereParams 关联条件, 
    SFunction 预JOIN的外表字段
);
````
比如如下需求需根据条件列出所有文章的评论列表, 并且评论列表中要携带它对应文章的标题, 需要查询如下数据:

| 评论ID | 回复人ID | 所属文章标题 | 发言人昵称 | 发言人电子邮箱 | 发布内容 | 发布时间 |
|  ----  | ----  | ----  | ----  | ----  | ----  | ----  |
|...|...|...|...|...|...|...|

> 已知文章表和评论表, 评论表中有所属文章ID, 需要通过文章ID将文章标题查询出来放到评论列表中.
````java
// 首先评论列表实体类中应加入`title`字段, 这个字段与文章实体中的title对应, 并标注`@Template`注解
@TempField
private String title;
````

代码示例:
````java
/**
 * 列出评论列表
 * @param keyword
 *      关键字
 * @param deleted
 *      是否已删除
 * @param page
 *      页码
 * @param limit
 *      每页条数
 * @return
 */
@Override
public ResultPage<ArticleComment> list(String keyword, Integer status, int page, int limit) {
    WherePrams where = Method.where(ArticleComment::isDeleted, C.EQ, false);
    if (null != status && 2 != status){
        where.and(ArticleComment::getStatus, C.EQ, status);
    }
    if (!StringUtils.isEmpty(keyword)){
        where.and(Method.where(ArticleComment::getContent, C.LIKE, keyword)
                .or(ArticleComment::getNicker, C.LIKE, keyword).or(ArticleComment::getEmail, C.LIKE, keyword));
    }
    return articleCommentDao.list(where,
            LeftJoin.join(Article.class, Method.where(Article::getId, C.EQ, ArticleComment::getArticleId),
                    Article::getTitle),page, limit);
}
````
执行此段代码时, 开启DEBUG级别日志, 并开启SQL格式化, 可以看到SQL执行结果为:
````sql
2020-11-10 12:52:14.967 DEBUG 28226 --- [nio-8080-exec-5] com.aiyi.blog.dao.ArticleCommentDao      : SQL => 

    SELECT
        blog_article_comment.id AS id,
        blog_article_comment.article_id AS articleId,
        blog_article_comment.parent_id AS parentId,
        blog_article_comment.nicker AS nicker,
        blog_article_comment.email AS email,
        blog_article_comment.content AS content,
        blog_article_comment.create_time AS createTime,
        blog_article_comment.status AS status,
        blog_article_comment.deleted AS deleted ,
        blog_article.title AS title 
    FROM
        blog_article_comment AS blog_article_comment 
    LEFT JOIN
        blog_article 
            ON  blog_article.id = blog_article_comment.article_id 
    WHERE
        blog_article_comment.deleted = #{param_0} 
        AND (
            blog_article_comment.content like #{param_2} 
            OR blog_article_comment.nicker like #{param_3} 
            OR blog_article_comment.email like #{param_3}
        ) LIMIT 0 , 10 

````
#### DAO 的高级用法(自定义SQL)
- 用法一, 封装自定义SQL

> 直接在DAO实现类中添加自定义SQL方法即可, 假设每个人都有一个朋友, 
并且他们是自关联的表, 这个时候要查询出所有小哥哥的女朋友, 并且分页展示, 
这个时候需要执行一个比较复杂的SQL, 如下:

````sql
SELECT res.* FROM persion_info WHERE res.id IN (
    SELECT friendId FROM persion_info WHERE sex = 1
) res LIMIT 0, 10;

````
- 让DAO原生支持这个SQL

只需建立这个SQL的实现方法即可.


````java
/**
 * @author gsk 
 */
@Repository
public class PersionDao extends BaseDaoImpl<PersionPO, Long> {
    
    /**
     * 出所有小哥哥的女朋友, 并且分页展示
     * @param pageNum
     *      页码
     * @param length
     *      每页条数
     * @return 
     */
    List<PersionPO> listPersionFriend(int pageNum, int length){
        int start = (pageNum - 1) * length;
        String sql = "SELECT res.* FROM WHERE res.id IN (" + 
                     "    SELECT friendId FROM persion_info WHERE sex = ?" + 
                     ") res LIMIT ?, ?;";
        return list(sql, 1, start, length);
    }
}
````
- 使用内部函数优化SQL语句

在以上代码中, 要查询的字段是 res.*, 这个过程其实比较耗时, 我需要更规范的写法: `SELECT res.id, res.name, res.xxx ... FROM`这类的.
再比如`SELECT friendId FROM persion_info`中的`persion_info`这个表名, 也是写死的, 假设表名变更或者字段变更的话, 
则需要修改业务代码, 这个时候使用内部函数来动态得到要查询的字段和表名, 便可解决后期的SQL维护问题. 
如下:

````java
List<PersionPO> listPersionFriend(int pageNum, int length){
    int start = (pageNum - 1) * length;
    
    // 动态生成SQL, 填充字段列表和表名
    String sql = "SELECT %s FROM WHERE id IN (" + 
                 "    SELECT friendId FROM %s WHERE sex = ?" + 
                 ") LIMIT ?, ?;";
    sql = String.format(sql, selectSql(), tableName());
    
    // 执行SQL
    return list(sql, 1, start, length);
}
````
#### DAO的高级用法(使用Mybatis特性: Mapper)
继续以以上SQL为例, 将以上Sql使用Mapper模板来执行: 
1,建立Mapper.xml

````xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.front.pay.dao.PersionDao">
    <select id="listPersionFriend" parameterType="hashmap" resultMap="com.front.entity.PersionPO">
        SELECT ${selectSql} FROM WHERE id IN(
            SELECT friendId FROM ${tableName} WHERE sex = #{sex}
        ) res LIMIT #{start}, #{length};
    </select>
</mapper>
````
2,添加DAO方法映射


````java
/**
 * @author gsk 
 */
@Repository
public class PersionDao extends BaseDaoImpl<PersionPO, Long> {
    
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;
    
    /**
     * 出所有小哥哥的女朋友, 并且分页展示
     * @param pageNum
     *      页码
     * @param length
     *      每页条数
     * @return 
     */
    List<PersionPO> listPersionFriend(int pageNum, int length){
        Map<String, Object> params = new HashMap<>();
        params.put("start", (pageNum - 1) * length);
        params.put("length", length);
        params.put("selectSql", selectSql());
        params.put("tableName", tableName());
        
        return sqlSessionTemplate.selectList("listPersionFriend", params);
    }
}
````

其实以上语句是可以使用强大的Method表达式动态生成的, 善用`Script`对象, 它可以生成你想要的SQL脚本而不执行它:
````java
Method.where("id", C.IN, 
    Script.of("SELECT parentId FROM ", tableName(), " ", Method.where("sex", 1))
).limit(1, 10);
````
## 如何使用缓存?
EasyMybatis内置了本地缓存工具类, 可以临时缓存一些内容, 适用于轻量级开发.
需要注意的是,缓存的内容不会进行持久化, 当项目重启后, 缓存会丢失.
- 简单的缓存示例

缓存用户信息2小时, 定义Key的前缀是"CACHE_LOGIN_KEY", 后面跟TOKEN(例如: abc), 那么他的Key是`CACHE_LOGIN_KEY:abc`

可以使用util.cache.Key对象构建:

``` java 
Key.as(String ... keys)
```

代码实例
```java
User user = new User();
CacheUtil.put(Key.as("CACHE_LOGIN_KEY", "abc"), user, TimeUnit.HOURS, 2);
```
- 带有回调的缓存

定义一个Runnable, 当缓存失效时, 输出`Over!`
```java
User user = new User();
CacheUtil.put(Key.as("CACHE_LOGIN_KEY", "abc"), user, TimeUnit.HOURS, 2, () -> {
    System.out.println("Over!");
});
```

## 带有强约束的排序方法
当查询条件中使用SFunction进行字段约束时, 对应的排序方法也应使用强约束.
可以使用`Sort`构建一个强约束的排序规则.

假设查询姓名中包含'张'的用户, 并且将结果按照姓名字段倒序排列, 当姓名一致时,再根据ID进行正序排列.

实例:
```java
Method.where(User::getName, C.LIKE, '张')
    .orderBy(
        Sort.of(User:getName, OrderBy.DESC).and(User:getId, OrderBy.ASC)
    );
```
## 业务锁
注意：这里的业务锁为本地业务锁，不可用于分布式，若需要分布式锁，可以配合Redis实现。
业务锁使用方式：
````java
CacheUtil.lock(Key.as("锁的KEY，不同的KEY之间的锁不会干扰，一般情况为业务的形参"),() -> {
    // 锁的代码
});
````
示例：
````java
public void registerUser(User user){
    // 在锁内执行防止并发重复注册
    CacheUtil.lock(Key.as("Register", user.getUsername),() -> {
        User dbUser = userDao.get(Method.where(User::getUsername, C.EQ, user.getUsername());
        if(null != dbUser){
            throw new ValidationException("该用户名已被注册");
        }
    });
    // 执行后续业务
    userDao.add(user);
}
````
lock方法可以设定超时时间
````java
CacheUtil.lock(超时时间毫秒，锁的KEY, 锁内业务实现代码);
````
lock默认的业务key为".default", 若不指定KEY，则使用默认key，也就是全局业务的锁
````java
CacheUtil.lock(锁内业务实现代码);
````
... 待续
