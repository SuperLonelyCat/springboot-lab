#### Shiro过滤器
```text
1、/admin/**=anon ：无参，表示可匿名访问
2、/admin/user/**=authc ：无参，表示需要认证才能访问
3、/admin/user/**=authcBasic ：无参，表示需要httpBasic认证才能访问
4、/admin/user/**=ssl ：无参，表示需要安全的URL请求，协议为https
5、/home=user ：表示用户不一定需要通过认证，只要曾被 Shiro 记住过登录状态就可以正常发起 /home 请求
6、/edit=authc,perms[admin:edit]：表示用户必需已通过认证，并拥有 admin:edit 权限才可以正常发起 /edit 请求
7、/admin=authc,roles[admin] ：表示用户必需已通过认证，并拥有 admin 角色才可以正常发起 /admin 请求
8、/admin/user/**=port[8081] ：当请求的URL端口不是8081时，跳转到schemal://serverName:8081?queryString
9、/admin/user/**=rest[user] ：根据请求方式来识别，相当于 /admins/user/**=perms[user:get]或perms[user:post] 等等
10、/admin**=roles["admin,guest"] ：允许多个参数（逗号分隔），此时要全部通过才算通过，相当于hasAllRoles()
11、/admin**=perms["user:add:*,user:del:*"] ：允许多个参数（逗号分隔），此时要全部通过才算通过，相当于isPermitedAll()
```
---
#### IDEA热部署
##### DevTool方式（整体加载）
##### 1 引入jar包

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```
##### 2 配置IDEA

```text
(1) File --> Settings --> Build, Execution, Deployme --> Compiler --> Build project automatically 

(2) ctrl + shift + alt + / --> Registry --> compiler.autoMake.allow.when.app.running 
```
---
##### JRebel方式（局部加载）
##### 1 下载IDEA插件
```text
JRebel and XRebel for Intellij

设置插件Redeploy time: 1s
```
##### 2 配置IDEA

```text
(1) File --> Settings --> Build, Execution, Deployme --> Compiler --> Build project automatically 

(2) ctrl + shift + alt + / --> Registry --> compiler.autoMake.allow.when.app.running 
```
##### 3 启用项目
```text
JRebel --> springboot-shiro
```