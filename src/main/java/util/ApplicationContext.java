package util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationContext {

    private static Document config;
    private static Class classFile;
    private static Map<String, Object> staticContainer = new HashMap(); // single 对象

    static {
        // IOC 容器啟動時，加載配置文件
        SAXReader saxObj = new SAXReader();
        try {
            config = saxObj.read("src/main/java/applicationContext.xml");
            createBean();
            System.out.println("IOC在啟動時去尋找配置文件");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 容器启动时，加载single类
    private static void createBean() throws Exception {
        String xPath = "//bean[@scope='single']";
        List<Element> beanList = config.selectNodes(xPath);
        for (Element property : beanList) {
            String classPath = property.attributeValue("class");
            // 根据类名找到类，然后 newInstance, 则可以获得当前对象的实例对象
            Object obj = Class.forName(classPath).newInstance();
            staticContainer.put(classPath, obj);
            // 此时 这个staticContainer 单例object container就可以在容器加载时被应用
        }

    }


    // 1. 根據開發人員提供的id 編號返回Bean
    public Object getBean(String beanId) {
        String xPath = "//bean[@id = '" + beanId + "']";
        Object bean = null;
        List<Element> list = null;
        String classPath = null;

        list = config.selectNodes(xPath);
        classPath = list.get(0).attributeValue("class");
        bean = staticContainer.get(classPath); // 此时应该每次申请的都是单例
        // 反射機制
        try {
            classFile = Class.forName(classPath);
            if (bean == null) {
                bean = classFile.newInstance();
            }
            init(bean, list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean; // bean
    }

    //prototype/ single
    // 单例，只有在被调用时才会被处理
    // 多例， 在启动的时候被处理
    private void init(Object bean, Element beanElement) throws Exception {
        System.out.println("IOC 容器开始对bean 对象进行 初始化");
        //检索当前标签是否有子标签
        List<Element> propertyList = beanElement.elements("property");
        if (propertyList == null || propertyList.size() == 0) {
            return;
        }
        //开始赋值处理
        for (Element property : propertyList) {
            String filedName = property.attributeValue("name");
            String value = property.attributeValue("value");

            Field fieldObj = classFile.getDeclaredField(filedName);// filedObj : private int deptNo
            String filedType = fieldObj.getType().getName(); // int

            String ref = property.attributeValue("ref");

            if (ref == null || "".equals(ref)) {
                setValue(bean, fieldObj, filedType, value);
            } else {
                setDI(bean,ref,fieldObj);
            }
        }


    }

    private void setValue(Object bean, Field field, String typeName, String value) throws IllegalAccessException {
        field.setAccessible(true);
        if ("java.lang.String".equals(typeName)) {
            field.set(bean, value);
        } else if ("int".equals(typeName)) {
            // 通过反射做一个赋值
            field.set(bean, Integer.valueOf(value));
        }
    }

    // 依赖注入的实现
    private void setDI(Object bean, String ref, Field field) throws Exception {
        String xPath = "//bean[@id = '" + ref + "']";
        // 根据XPath 找到bean 标签 <bean id = "MyDo" class="Do.DeptDo" scope="single"></bean>
        Element element = (Element) config.selectNodes(xPath).get(0);
        //根据该标签找到class 地址
        String classPath = element.attributeValue("class");
        // 看静态容器中是否存在该静态标签
        Object subBean = staticContainer.get(classPath);
        if (subBean == null) {
            // 自己创建新的instance
            subBean = Class.forName(classPath).newInstance();
        }
        field.setAccessible(true);
        // 注入
        field.set(bean, subBean);
    }

    // 关闭容器
    public  void close(){
        staticContainer.clear();
    }
}
