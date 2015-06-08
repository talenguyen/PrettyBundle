package com.tale.prettybundleprocessor;

import com.tale.prettybundle.ExtraBinder;

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
    private SupportedType supportedType;

    public ExtraAnnotatedClass(VariableElement annotatedVariableElement, Elements elements, Types types) {
        this.annotatedVariableElement = annotatedVariableElement;
        key = annotatedVariableElement.getSimpleName().toString();
        // Get the full QualifiedTypeName
        final TypeElement parent = (TypeElement) annotatedVariableElement.getEnclosingElement();
        if (types.isSubtype(parent.asType(), elements.getTypeElement("android.app.Activity").asType())) {
            supportedType = SupportedType.ACTIVITY;
        } else if (types.isSubtype(parent.asType(), elements.getTypeElement("android.app.Service").asType())) {
            supportedType = SupportedType.SERVICE;
        } else if (types.isSubtype(parent.asType(), elements.getTypeElement("android.app.Fragment").asType())
                || types.isSubtype(parent.asType(), elements.getTypeElement("android.support.v4.app.Fragment").asType())) {
            supportedType = SupportedType.FRAGMENT;
        } else {
            supportedType = SupportedType.NOP;
        }
        qualifiedClassName = parent.getQualifiedName().toString();
        // Get the full Qualified of DataType.
        dataType = annotatedVariableElement.asType();
        dataTypeQualifiedClassName = dataType.toString();
        extraBinder = ExtraBinderProvider.get(dataTypeQualifiedClassName);
        if (extraBinder != ExtraBinder.NOP) {
            return;
        }
        // Check if data type is kind of Parcelable.
        if (types.isSubtype(dataType, elements.getTypeElement("android.os.Parcelable").asType())) {
            extraBinder = ExtraBinderProvider.get("android.os.Parcelable");
            if (extraBinder != ExtraBinder.NOP) {
                return;
            }
        }

        try {
            // Check if data type is kind of Array.
            dataTypeQualifiedClassName = ((ArrayType) dataType).getComponentType().toString();
            final TypeMirror componentTypeMirror = elements.getTypeElement(dataTypeQualifiedClassName).asType();
            if (types.isSubtype(componentTypeMirror, elements.getTypeElement("android.os.Parcelable").asType())) {
                extraBinder = ExtraBinderProvider.get("android.os.Parcelable[]");
                if (extraBinder != ExtraBinder.NOP) {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SupportedType getSupportedType() {
        return supportedType;
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
