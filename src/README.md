IOC 控制反转: 只是Spring 提供的诱饵，改变程序员的获得对象的方式---》 AOP容器
容器 ，对对象进行管理（对象： bean）,作用：
1. 创建对象
2. 初始化
3. 对象创建次数
4. 依赖注入
3. 切断IOC容器与Bean 的关系

服务层：DO层
IOC 服务包： dom4j.jar + reflect
當某個類需要交給IOC處理的時候， 要做註冊


AOP 容器： 代理设计模式
// SpringIOC 容器的注解式开发 -- 面向注解的方式

1. 对于业务中的对象的创建、初始化、销毁都不应该由开发人员关注，而由Spring 容器去处理，控制权反转（IOC）
2. bean 和 组件
   组件： 委托Spring框架进行管理的Java类
   bean : 实例对象，是在Spring框架被管理的实例对象
3. Spring IOC 容器实现的功能
   1） 动态组件注册
   2） bean对象初始化管理
   3） 依赖注入
   4） bean对象初始化前后，进行次要业务
   【mvn repository】: https://mvnrepository.com/ 新建一个maven 工程之后依赖 spring-context
4. @Configuration
   1) 作用 替换configuration文件， 声明一个可以被作为配置文件 的Java类

5. 组件注册的方式
   1） @Bean
       可以将一个Java类交给Spring管理，是Spring框架提供的注解， @Bean 需要在配置类中使用
       默认时，以@Bean 修饰的Bean对象对应的关键字就是【方法名】 （如；student2）;
       如果在@Bean 中指定了当前Bean对象所对应的关键字 @Bean(value=["stu1","stu2"]), 此时Bean对象在Spring容器中对应的关键字就是stu1
       或者stu2 (如 jennyStudent);
       所有通过@Bean修饰的Bean对象默认都是单例;
       对于单例的Bean对象， 可以通过@Lazy 来延缓Bean对象的创建时机，@lazy 专门用于@Bean, 单例模式的bean对象，此时Bean对象不会在Spring容器启动的时候被创建，只有在一个用户来访时才会创建
       延迟创建的目的：节省内存（是否有用户来访问不确定的情况下可以用延迟创建）
   2） @ComponentScan
        Spring框架提供的注解，会自动将指定包下的类（@Controller, @Service, @repository ,@Component ）加载到Spring容器中
        该注解需要在配置类中使用
        deptComponent
        deptController
        deptDo
        deptService
        可以决定将哪些类加入，哪些类排除在Spring容器外
        //
        filterType : ANNOTATION,
                         ASSIGNABLE_TYPE, 根据指定类型
                         ASPECTJ,表达式过滤
                         REGEX,正则表达式过滤
                         CUSTOM;开发人员自定义
        //
        自定义filter规则：
        @ComponentScan(value = "beans",excludeFilters = {
                @Filter(type= FilterType.CUSTOM,value = 自定义的过滤规则类.class)
        })
        需要自定义 Condition的条件实现类

   3)  @Conditional
       Spring框架提供的注解，动态决定当前的Java类是否有资格添加到Spring 容器中，在配置类中进行相关使用

   4） FactoryBean 接口
        Spring 默认情况下如果不做修改认为它所管理的对象是 singleton的
        处理方式：  1. 用@Component 修饰MyFactoryBean 2. 用配置类处理，把FactoryBean 当做是 @Bean


   5)  @Import
        负责将没有任何修饰的Java类导入Spring容器中，
        使用方式：  1.@Import({one.class,two.class}) 2. @Import({ImportSelector 接口实现类}) 返回需要添加的接口路径
                    3.@Import({自定义Bean 对象注册实现类})，可以直接将类注册到Spring容器中 (ImportBeanDefinitionRegistrar)



