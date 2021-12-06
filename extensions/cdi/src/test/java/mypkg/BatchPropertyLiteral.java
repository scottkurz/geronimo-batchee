package mypkg;

import javax.batch.api.BatchProperty;
import javax.enterprise.util.AnnotationLiteral;

public class BatchPropertyLiteral extends AnnotationLiteral<BatchProperty> implements BatchProperty {

    private String name;

    public BatchPropertyLiteral() {
        this(""); //???
    }

    public BatchPropertyLiteral(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

}
