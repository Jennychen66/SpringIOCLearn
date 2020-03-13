package util;

import beans.Student;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

//@Component
public class MyFactoryBean implements FactoryBean<Student> {

    // 通知当前Spring容器，当前学生类的创建方式
    public Student getObject() throws Exception {
        return new Student();
    }

    //通知 Spring 容器，现在被管理的Bean对象所对应的类型
    public Class<?> getObjectType() {
        return Student.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
