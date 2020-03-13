package util;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

@Component
public class WindowsCondition implements Condition {
    /**
     * conditionContext : Spring容器中的上下文环境
     * annotatedTypeMetadata ： @Condition 修饰类型信息
     * */
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String sysName = conditionContext.getEnvironment().getProperty("os.name");
        if (sysName.contains("Windows")){
            return true;
        }
        return false;
    }
}
