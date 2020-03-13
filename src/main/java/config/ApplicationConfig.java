package config;

import beans.Dog;
import beans.Student;
import beans.Teacher;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import util.*;

//排除了 修饰了Controller和Service的类
@ComponentScan(value = "beans",excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ANNOTATION,value = {Controller.class, Service.class})
})
@Import(value = {Dog.class, MyImportSelector.class, MyImportBeanDefRegistrar.class})
@PropertySource("classpath:/config.properties")
@Configuration
public class ApplicationConfig {

    //此时Student类就已经成为了Spring中的相关组件
    @Bean(value = "jennyStudent")
    public Student student2(){
        return new Student();
    }

    // @lazy 专门用于@Bean, 单例模式的bean对象，此时Bean对象不会在Spring容器启动的时候被创建，只有在一个用户来访时才会创建
    @Lazy
    @Bean
    public Teacher teacher(){
        return new Teacher();
    }

    // condition 注解
    @Conditional(value = {LinuxCondition.class})
    @Bean
    public Teacher teacher1(){return new Teacher();}

    @Conditional(value = {WindowsCondition.class})
    @Bean
    public Student student1(){return new Student();}

    // BeanFactory
    @Bean
    public MyFactoryBean factoryBean(){
        return  new MyFactoryBean();
    }

    //
    @Bean
    public MyBeanPostProcessor postProcessor(){
        return new MyBeanPostProcessor();
    }
}
