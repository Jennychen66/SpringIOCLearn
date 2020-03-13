package util;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

@Component
public class LinuxCondition implements Condition {
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        if(conditionContext.getEnvironment().getProperty("os.name").contains("Linux")){
            return  true;
        }

        return false;
    }
}
