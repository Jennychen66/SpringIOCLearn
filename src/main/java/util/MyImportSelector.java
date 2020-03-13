package util;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        String className[] = {"beans.Car"};
        return className;
    }
}
