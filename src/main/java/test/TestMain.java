package test;

import Service.DeptService;
import beans.Dept;
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

        DeptService service = (DeptService) context.getBean("myService");
        service.addDo();

        // 关闭测试
        // 只要这块地址有一个引用对象相关联， 那么这个bean 仍然是存在的，即使已经切断了跟 容器的关联
        Dept dept3 = (Dept) context.getBean("Dept");
        context.close();
        System.out.println("dept3 : " + dept3 );

    }
}
