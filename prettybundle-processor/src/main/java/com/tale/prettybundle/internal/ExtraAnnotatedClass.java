package com.tale.prettybundle.internal;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by tale on 2/1/15.
 */
public class ExtraAnnotatedClass {

    private final VariableElement annotatedVariableElement;
    private final String key;
    private final String qualifiedClassName;
    private final String dataTypeQualifiedClassName;
    private final TypeMirror dataType;

    public ExtraAnnotatedClass(VariableElement annotatedVariableElement) {
        this.annotatedVariableElement = annotatedVariableElement;
        key = annotatedVariableElement.getSimpleName().toString();
        // Get the full QualifiedTypeName
        final TypeElement parent = (TypeElement) annotatedVariableElement.getEnclosingElement();
        qualifiedClassName = parent.getQualifiedName().toString();
        // Get the full Qualified of DataType.
        dataType = annotatedVariableElement.asType();
        dataTypeQualifiedClassName = dataType.toString();
    }

    public VariableElement getAnnotatedVariableElement() {
        return annotatedVariableElement;
    }

    public String getKey() {
        return key;
    }

    public String getDataTypeQualifiedClassName() {
        return dataTypeQualifiedClassName;
    }

    public String getQualifiedClassName() {
        return qualifiedClassName;
    }

    public TypeMirror getDataType() {
        return dataType;
    }
}
