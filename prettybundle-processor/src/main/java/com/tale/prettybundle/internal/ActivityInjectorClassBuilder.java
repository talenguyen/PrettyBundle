package com.tale.prettybundle.internal;

import android.os.Bundle;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.tale.prettybundle.ExtraBinder;
import com.tale.prettybundle.Injector;
import com.tale.prettybundle.PrettyBundle;

import java.io.IOException;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by tale on 2/5/15.
 */
public class ActivityInjectorClassBuilder {

    private final ActivityExtrasGrouped activityExtrasGrouped;

    public ActivityInjectorClassBuilder(ActivityExtrasGrouped activityExtrasGrouped) throws IllegalAccessException, NullPointerException {
        if (activityExtrasGrouped == null) {
            throw new NullPointerException("activityExtrasGrouped must not be null");
        } else if (activityExtrasGrouped.getActivityQualifiedClassName() == null
                || activityExtrasGrouped.getActivityQualifiedClassName().trim().equals("")) {
            throw new IllegalAccessException("ActivityExtrasGrouped.getActivityQualifiedClassName() must not return null");
        }

        this.activityExtrasGrouped = activityExtrasGrouped;
    }

    public void generateCode(Elements elementUtils, Filer filer) throws IOException {
        final String activityQualifiedClassName = activityExtrasGrouped.getActivityQualifiedClassName();
        final TypeElement typeElement = elementUtils.getTypeElement(activityQualifiedClassName);
        final String activityName = typeElement.getSimpleName().toString();

        final MethodSpec injectMethod = buildInjectMethod(elementUtils);

        ClassName injector = ClassName.get(Injector.class);
        ClassName activity = ClassName.get(activityExtrasGrouped.getPackageName(), activityName);

        final TypeSpec activityInjectorClass = TypeSpec.classBuilder(activityName + PrettyBundle.INJECTOR_SUFFIX)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(injector, activity))
                .addMethod(injectMethod).build();

        JavaFile javaFile = JavaFile.builder(activityExtrasGrouped.getPackageName(), activityInjectorClass)
                .build();
        javaFile.writeTo(filer);
    }

    private MethodSpec buildInjectMethod(Elements elementUtils) {
        final MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("inject")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeVariableName.get(activityExtrasGrouped.getActivityQualifiedClassName()), "target");
        addInjectStatement(methodBuilder, "target", elementUtils);
        return methodBuilder
                .build();
    }

    private void addInjectStatement(MethodSpec.Builder methodBuilder, String targetName, Elements elementUtils) {
        final List<ExtraAnnotatedClass> extraAnnotatedClasses = activityExtrasGrouped.getExtraAnnotatedClasses();
        if (extraAnnotatedClasses == null || extraAnnotatedClasses.size() == 0) {
            return;
        }
        methodBuilder.addStatement("final $T extras = target.getIntent().getExtras()", Bundle.class);
        methodBuilder.beginControlFlow("if(extras == null)")
                .addStatement("return")
                .endControlFlow();
        for (ExtraAnnotatedClass extraAnnotatedClass : extraAnnotatedClasses) {

            final ExtraBinder extraBinder = extraAnnotatedClass.getExtraBinder();
            methodBuilder
                    .beginControlFlow("if(extras.containsKey($S))", extraAnnotatedClass.getKey())
                    .addStatement("$L.setDataTypeClass($L.class)", ExtraBinder.class.getName() + "." + extraBinder.toString(), extraAnnotatedClass.getDataTypeQualifiedClassName())
                    .addStatement("$L.$L = $L.get(extras, $S)", targetName, extraAnnotatedClass.getKey(), ExtraBinder.class.getName() + "." + extraBinder.toString(), extraAnnotatedClass.getKey())
                    .endControlFlow();
        }
    }

}
