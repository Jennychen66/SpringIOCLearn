package util;

import beans.Animal;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefRegistrar implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 1.获得对象定义的管理器，负责帮助某一个Java类进行包装，
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Animal.class);
        // 2. 创建当前Java 类的实例对象
        BeanDefinition obj = builder.getBeanDefinition();
        // 3.通过Spring的bean 注册器，将当前的实例对象添加到Sring 容器中
        registry.registerBeanDefinition("animal-class",obj);
    }
}
