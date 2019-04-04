# springboot-scaffold
SpringBoot脚手架，适用于新项目搭建，不断集成技术栈...

## 示例代码
* [springboot-base](https://github.com/zhouhui5116/springboot-scaffold/tree/master/springboot-base) : 新项目简洁脚手架搭建，开箱即用，满足日常开发需要  
* [springboot-websocket](https://github.com/zhouhui5116/springboot-scaffold/tree/master/springboot-websocket) : 集成WebSocket，演示单聊、群聊的即时通讯功能  
* [springboot-shiro-jwt](https://github.com/zhouhui5116/springboot-scaffold/tree/master/springboot-shiro-jwt) : 集成Shiro和JWT，实现无状态模式下的角色权限控制
* [springboot-redis](https://github.com/zhouhui5116/springboot-scaffold/tree/master/springboot-redis) : 集成Redis，使用RedisTemplate对常用的数据结构进行存取

## springboot-base
* SpringBoot : 基于约定大于配置原则的开发框架，版本1.5.7.RELEASE
* SpringMVC : 基于MVC模式的web框架，用于控制转发前端请求
* MyBatis : 持久层框架，支持定制化SQL、存储过程以及高级映射
* Druid : 数据库连接池，多数据源，可进行连接配置和SQL监控
* 数据库 : MySQL5.6，免费开源的关系型数据库
* 缓存 : Ehcache，可替代为更高性能的redis
* JDK : 版本1.8，支持Lamda表达式、Optional空对象判断
* 分页插件 : 使用com.github.pagehelper并进行封装
* Lombok : 消除getter/setter等冗余，使编码更优雅
* 过滤器/拦截器 : 过滤器实现数据过滤，拦截器实现权限拦截
* JWT : 身份验证方案，含如何创建和解析JWT数据方法
* API管理工具 : Swagger2，可视化接口管理和测试工具
* 单元测试 : JUnit，SpringBoot内置，引入即可使用
* 异常处理 : ControllerAdvice,捕捉处理全局异常
* 日志框架 : SLF4J，基于门面模式的日志管理框架
* Json工具 : Alibaba FastJson，用于处理Json数据
* 模板引擎 : FreeMarker，轻巧能实现严格的MVC分离

## springboot-websocket
* WebSocket解释 : HTML5开始提供的在单个TCP连接上进行全双工通讯的协议
* @ServerEndpoint : 用于将目前的类定义成一个WebSocket服务器
* @OnOpen : 打开一个新连接，即有新连接时，会调用被此注解的方法
* @onClose : 一个WebSocket客户端关闭连接时调用
* @onMessage : 当服务器接收到客户端发送的消息时所调用的方法
* @PathParam : 用于接收uri参数，与@PathVariable功能类似

## springboot-shiro-jwt
* Shiro : 安全框架,执行身份验证、授权等管理，支持注解
* JWT : 解决跨域身份验证、前后端分离模式下会话不易管理弊端，支持过期时间
* 加密 : 这里用统一指定的SECRET，也可用用户的密码作对应的密钥
* RBAC : 基于角色的访问控制,用户通过角色与权限进行关联的一种模型
* 自定义Filter : 继承BasicHttpAuthenticationFilter，重写以下方法，执行顺序:preHandle->isAccessAllowed->isLoginAttempt->executeLogin
* 自定义Realm : 继承AuthorizingRealm，重写doGetAuthenticationInfo，用于认证用户信息；重写doGetAuthorizationInfo，用于授予用户权限
* @RequiresRoles : 标注该注解下的方法须拥有指定角色才可访问
* @RequiresPermissions : 标注该注解下的方法须拥有指定权限才可访问
