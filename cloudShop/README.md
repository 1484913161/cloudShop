> #cloud-shop

## spring-cloud的架构。

- Eureka【服务注册中心】
    - 集群搭建
        - 在C:\Windows\System32\drivers\etc下面有一个hosts文件
            添加 
            - 127.0.0.1 eureka7001.com
            - 127.0.0.1 eureka7002.com
- Rebbin【服务调用】
    - 
- LoadBalancer【服务调用】
    -
- OpenFeign【服务调用】
    - 注解 启动类加入@EnableFeignClients
    - 注解 接口中加入@FeignClient
- Hystrix【服务降级】
    - 服务降级，服务熔断，限流。
        - 服务降级【@HystrixCommand(在service层加入这个注解)详细请看【cloud-provider-hystrix-payment8001】】
        - 服务熔断【hystrix-dashboard】
            - 【cloud-consumer-hystrix-dashboard9001】
- GateWay【网关】
    - 路由【route】，断言【predicates】，过滤链【Filter】
        - 路由
            - gateway:
            -      开启从注册中心创建路由的功能，利用微服务名字进行路由
            -        discovery:
            -           locator:
            -             enabled: true # 开启从注册中心创建路由的功能，利用微服务名字进行路由
        - 断言
            - 根据条件进行判断是否符合【例如：规定这个接口在哪个事件可以访问】
        - 过滤链【一般都是自定义，实现下面接口】
            - implements GlobalFilter,Ordered        
    - 注意事项
        - 不需要配置maven坐标
        	- spring-boot-starter-web
        	- spring-boot-starter-actuator
- Config【服务配置】
    - 客户端实时获取github数据
        - 1.客户端实时获取数据需要在控制层添加注解:@RefreshScope[]刷新功能
        -  2.config客户端刷新github配置文件【进行post请求操作】
       curl -X POST http://127.0.0.1:3355/actuator/refresh
          