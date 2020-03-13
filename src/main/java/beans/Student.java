package beans;

import org.springframework.beans.factory.annotation.Value;

// 如果程序运行在windows下，注册Student 类
public class Student {

    public Student(){
        System.out.println("constructor invoked of Student");
    }
    @Value("Allen")
    private String name;  // @Value 要匹配 @PropertySource 使用
    @Value("#{28-2}")  // SPEL为属性赋值
    private int age;
    @Value("${student.home}") // 读取来自外部properties 属性文件内容
    private String street;

    @Override
    public String toString() {
        return "name: "+ this.name + " age: " + this.age + " street: " + this.street;
    }
}
