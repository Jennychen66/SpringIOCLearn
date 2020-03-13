package test;

import Service.DeptService;
import beans.Dept;
import beans.Dog;
import beans.Student;
import beans.Teacher;
import config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import util.ApplicationContext;

public class TestMain {
    public  static void main(String[] args){
        // 在加载IOC容器的过程中， Dept 的构造函数就已经被调用了
        ApplicationContext context = new ApplicationContext();
        Dept dept = (Dept) context.getBean("Dept");

//        System.out.println(dept.toString());
        Dept dept1 = (Dept) context.getBean("Dept");
        // 此时这两个部门对象应该是同一个
        // 1. run result:
//        Dept constructor function is invoked
//                IOC在啟動時去尋找配置文件
//        IOC 容器开始对bean 对象进行 初始化
//        IOC 容器开始对bean 对象进行 初始化
//        dept: Dept [deptNo]= '10', [deptName] = 'math
//        dept1: Dept [deptNo]= '10', [deptName] = 'math
        System.out.println("dept: "+ dept);
        System.out.println("dept1: " + dept1);


        // 此时如果想要创建多例对象，直接更改applicationContext.xml 中的配置，把scope="single" 换成别的，
        // IOC容器被启动之后，构造函数被调用
        // 2. run result :
//        IOC在啟動時去尋找配置文件
//        Dept constructor function is invoked
//        IOC 容器开始对bean 对象进行 初始化
//        Dept constructor function is invoked
//        IOC 容器开始对bean 对象进行 初始化
//        dept: beans.Dept@6504e3b2
//        dept1: beans.Dept@515f550a

        // 依赖注入测试

//        DeptService service = (DeptService) context.getBean("myService");
//        service.addDo();

        // 关闭测试
        // 只要这块地址有一个引用对象相关联， 那么这个bean 仍然是存在的，即使已经切断了跟 容器的关联
//        Dept dept3 = (Dept) context.getBean("Dept");
//        context.close();
//        System.out.println("dept3 : " + dept3 );

        // II : Spring IOC test
        AnnotationConfigApplicationContext configContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        String beanNames[] = configContext.getBeanDefinitionNames();
        System.out.println("Spring container is starting...");
        for(String beanName:beanNames){
            System.out.println(beanName);
        }

        // 索要两次Student对象
        Student stu2 = (Student)configContext.getBean("jennyStudent");
        Student stu1 = (Student)configContext.getBean("jennyStudent");
        System.out.println(stu1);
        System.out.println(stu2);
        // 此时两个Bean对象完全一样，因为由@Bean 修饰，是单例
        // beans.Student@10e92f8f
        //beans.Student@10e92f8f

        // 单例对象的创建时机是在容器启动的时候

        // 通过容器索要Teacher对象
        Teacher teacher = (Teacher)configContext.getBean("teacher"); // 此时Teacher类的构造函数才会被调用

        // 通过BeanFactoy 获得Student 对象
        Student stuBean = (Student)configContext.getBean("factoryBean");
        System.out.println(stuBean);
//        configContext.close();
//        System.out.println("Spring container is closed");
//        // 对象在外部被一个对象所关联，即便container关闭，改对象一直存在
//        System.out.println(stuBean);

        // 看看是否含有dog 类
        Dog dog2 = (Dog) configContext.getBean("beans.Dog");
        System.out.println(dog2);


    }
}
