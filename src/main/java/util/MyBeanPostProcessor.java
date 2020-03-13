package util;

import beans.Bird;
import beans.Dog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

public class MyBeanPostProcessor implements BeanPostProcessor {

    // bean : 当前Spring容器管理的对象，beanName : 当前对象在Spring容器所关联的name
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass() == Dog.class){
            try{
                Field field = bean.getClass().getDeclaredField("age");
                field.setAccessible(true);
                field.set(bean,15);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else if(bean.getClass() == Bird.class){
            try{
                Field field = bean.getClass().getDeclaredField("age");
                field.setAccessible(true);
                field.set(bean,8);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
