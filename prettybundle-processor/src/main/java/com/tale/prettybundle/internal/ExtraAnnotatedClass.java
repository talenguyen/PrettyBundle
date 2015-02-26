package com.tale.prettybundle.internal;

import com.tale.prettybundle.ExtraBinder;
import com.tale.prettybundle.ExtraBinderProvider;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Created by tale on 2/1/15.
 */
public class ExtraAnnotatedClass {

    private final VariableElement annotatedVariableElement;
    private final String key;
    private final String qualifiedClassName;
    private String dataTypeQualifiedClassName;
    private final TypeMirror dataType;
    private ExtraBinder extraBinder;

    public ExtraAnnotatedClass(VariableElement annotatedVariableElement, Elements typeUtils, Types elementUtils) {
        this.annotatedVariableElement = annotatedVariableElement;
        key = annotatedVariableElement.getSimpleName().toString();
        // Get the full QualifiedTypeName
        final TypeElement parent = (TypeElement) annotatedVariableElement.getEnclosingElement();
        qualifiedClassName = parent.getQualifiedName().toString();
        // Get the full Qualified of DataType.
        dataType = annotatedVariableElement.asType();
        dataTypeQualifiedClassName = dataType.toString();
        extraBinder = ExtraBinderProvider.get(dataTypeQualifiedClassName);
        if (extraBinder != ExtraBinder.NOP) {
            return;
        }
        // Check if data type is kind of Parcelable.
        if (elementUtils.isSubtype(dataType, typeUtils.getTypeElement("android.os.Parcelable").asType())) {
            extraBinder = ExtraBinderProvider.get("android.os.Parcelable");
            if (extraBinder != ExtraBinder.NOP) {
                return;
            }
        }

        try {
            // Check if data type is kind of Array.
            dataTypeQualifiedClassName = ((ArrayType) dataType).getComponentType().toString();
            final TypeMirror componentTypeMirror = typeUtils.getTypeElement(dataTypeQualifiedClassName).asType();
            if (elementUtils.isSubtype(componentTypeMirror, typeUtils.getTypeElement("android.os.Parcelable").asType())) {
                extraBinder = ExtraBinderProvider.get("android.os.Parcelable[]");
                if (extraBinder != ExtraBinder.NOP) {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public VariableElement getAnnotatedVariableElement() {
        return annotatedVariableElement;
    }

    public String getKey() {
        return key;
    }

    public ExtraBinder getExtraBinder() {
        return extraBinder;
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
