> #cloud-shop

## spring-cloud的架构。

- Eureka【服务注册中心】
    - 集群搭建
        - 在C:\Windows\System32\drivers\etc下面有一个hosts文件
            添加 
            - 127.0.0.1 eureka7001.com
            - 127.0.0.1 eureka7002.com
        - 过程
            - 主启动类添加注解：@EnableEurekaServer
            - yml
                ```yaml
                  server:
                    port: 7001
                  eureka:
                    instance:    
                      #hostname: 127.0.0.1:7001/eureka #单机版
                      hostname: eureka7001.com   #集群版
                    client:
                      #false表示不向注册中心注册自己
                      register-with-eureka: false
                      #false表示自己就是注册中心。我的职责就是维护服务实例，发现服务
                      fetch-registry: false
                      service-url:
                        
                        defaultZone: http://127.0.0.1:7001/eureka  #单机
                        
                        #defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/   #集群
                  # server:
                  #   enable-self-preservation: false # 关闭自我保护机制 保证不可用服务及时清除
                  #   eviction-interval-timer-in-ms: 2000
                ```   
            - pom.xml
                ```xml
                   <!--eureka-server-->
                  <dependency>
                      <groupId>org.springframework.cloud</groupId>
                      <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
                  </dependency>
                ```             
        - 查看：http://eureka7001.com:7001/
- Ribbon【服务调用】
    - ribbon其实是一个软负载均衡的客户端组件，它可以和其他所需请求的客户端结合，与eureka结合只是一个实例而已
    - pom.xml[引入这个eureka客户端组件就可以了，他已经整合了ribbon]
        ```xml
          <!--eureka-server-->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
          </dependency>
        ```
    - IRule【默认自带负载均衡】轮询，随机
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
- Bus【消息总线】
    - 一般与config配合使用，解决客户端配置文件及时更新不需要上一步的post请求
    - 前提bus只能支持 RabbitMq与kafka
    - 两种方式进行config的数据同步。
        - 1.config客户端进行【类似2】
        - 2.config服务端进行全局通知【推荐】
            - config服务端配置【消息总线】
                - 需要添加
                    - pom.xml
                       ```xml
                            <!-- 添加消息总线RabbitMQ支持 -->
                            <dependency>
                                <groupId>org.springframework.cloud</groupId>
                                <artifactId>spring-cloud-starter-bus-amqp</artifactId>
                            </dependency> 
                       ```       
                    - yml
                        ```yaml
                           ##rabbitmq相关配置，暴漏bus刷新配置的端点
                           management:
                             endpoints:  #暴露bus刷新配置的端点
                               web:
                                 exposure:
                                   include: "bus-refresh"
                           
                           spring:
                             application:
                               name: cloud-config-center
                             cloud:
                               config:
                                 server:
                                   git:
                                     uri: https://github.com/1484913161/cloud-shop-config.git
                                     search-paths:
                                       - cloud-shop-config
                                 label: master
                             #rabbitmq相关配置
                             rabbitmq:
                               host: localhost
                               port: 5672
                               username: guest
                               password: guest
                        ```  
            - config客户端【添加消息总线支持】
                - 需要添加
                    - pom.xml
                         ```xml
                              <!-- 添加消息总线RabbitMQ支持 -->
                              <dependency>
                                  <groupId>org.springframework.cloud</groupId>
                                  <artifactId>spring-cloud-starter-bus-amqp</artifactId>
                              </dependency> 
                         ``` 
                    - yml
                        ```yaml
                          spring:
                            application:
                              name: cloud-config-center
                            cloud:
                              config:
                                server:
                                  git:
                                    uri: https://github.com/1484913161/cloud-shop-config.git
                                    search-paths:
                                      - cloud-shop-config
                                label: master
                            #rabbitmq相关配置
                            rabbitmq:
                              host: localhost
                              port: 5672
                              username: guest
                              password: guest
                          #暴露监控端点
                          management:
                             endpoints:
                                web:
                                   exposure:
                                      include: "*"
                        ```
            - 最后
                - 更改GitHub内容
                - 发送请求【curl -X POST http://127.0.0.1:3344/actuator/bus-refresh】 
                    - 注意【端口号：3344这是我的项目端口号】
                        - cmd 进入黑窗口输入进行发送
                    - 我们是在服务端进行总线的。   
        - 2.1定点通知【该通知的通知，不该通知的不通知】
            - 公式：http://localhost:config服务端配置中心的端口号/actuator/bus-refresh/{destination}
            - 分析公式：destination就是服务名称加端口号【表示通知这个】
            - 本地：curl -X POST http://127.0.0.1:3344/actuator/bus-refresh/config-client:3355       
- Stream【消息驱动】
    - 为什么要引入 

- Nacos  
    - 如项目中创建的cloudalibaba-provider-payment9001与9002还有consumer-nacos-order83
    - 通过测试发现nacos中有负载均衡【83端口轮训调用9001与9002两个生产者】        
    - nacos支持CP与AP的模式切换
        - C是所有节点在同一时间看到的数据是否一致；而A的定义是所有的请求都会收到响应
        - 何时选择使用何种模式
          - 如果不需要存储服务级别的信息且服务实例是通过nacos-client注册，并能保持心跳上报那么可以选择AP模式。当前主流的服务SprignCloud 和dubbo服务，都适合用于AP模式，Ap模式为了服务的可能性减弱了一致性，因此AP模式只
                      