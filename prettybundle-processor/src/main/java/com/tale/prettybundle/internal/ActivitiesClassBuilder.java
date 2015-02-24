package com.tale.prettybundle.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.tale.prettybundle.ExtraBinder;
import com.tale.prettybundle.ExtraBinderProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Created by tale on 2/5/15.
 */
public class ActivitiesClassBuilder {

    private static final String ACTIVITIES = "Activities";

    private static final String packageName = "com.tale.prettybundle";

    private final Map<String, ActivityExtrasGrouped> activityExtrasGroupeds = new LinkedHashMap<String, ActivityExtrasGrouped>();

    public ActivitiesClassBuilder() {
    }

    public void add(ActivityExtrasGrouped activityExtrasGrouped) {
        if (activityExtrasGrouped == null
                || activityExtrasGrouped.getActivityQualifiedClassName() == null
                || activityExtrasGrouped.getActivityQualifiedClassName().trim().equals("")) {
            return;
        }

        // We replace the existed with the new or just add.
        activityExtrasGroupeds.put(activityExtrasGrouped.getActivityQualifiedClassName(), activityExtrasGrouped);
    }

    public boolean contains(ActivityExtrasGrouped activityExtrasGrouped) {
        if (activityExtrasGrouped == null
                || activityExtrasGrouped.getActivityQualifiedClassName() == null
                || activityExtrasGrouped.getActivityQualifiedClassName().trim().equals("")) {
            return false;
        }
        return activityExtrasGroupeds.containsKey(activityExtrasGrouped.getActivityQualifiedClassName());
    }

    public void generateCode(Elements elementUtils, Types typeUtils, Filer filer) throws IOException {

        final List<MethodSpec> methods = getMethods(elementUtils, typeUtils);

        if (methods == null || methods.size() == 0) {
            // Nothing created if there is no @Extra annotation is Added.
            return;
        }

        final TypeSpec.Builder activitiesClassBuilder = TypeSpec.classBuilder(ACTIVITIES)
                .addModifiers(Modifier.PUBLIC);

        for (MethodSpec method : methods) {
            activitiesClassBuilder.addMethod(method);
        }

        JavaFile.builder(packageName, activitiesClassBuilder.build()).build().writeTo(filer);
    }

    private List<MethodSpec> getMethods(Elements elementUtils, Types typeUtils) {
        final int size = activityExtrasGroupeds.size();
        if (size == 0) {
            return null;
        }
        final List<MethodSpec> methodSpecs = new ArrayList<MethodSpec>(size);
        for (ActivityExtrasGrouped activityExtrasGrouped : activityExtrasGroupeds.values()) {
            final MethodSpec methodSpec = createMethodSpec(elementUtils, typeUtils, activityExtrasGrouped);
            if (methodSpec != null) {
                methodSpecs.add(methodSpec);
            }
        }
        return methodSpecs;
    }

    private MethodSpec createMethodSpec(Elements elementUtils, Types typeUtils, ActivityExtrasGrouped activityExtrasGrouped) {
        final String activityQualifiedClassName = activityExtrasGrouped.getActivityQualifiedClassName();
        final TypeElement typeElement = elementUtils.getTypeElement(activityQualifiedClassName);
        final String activityName = typeElement.getSimpleName().toString();

        // Declare method name.
        final MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder("create" + activityName + "Intent")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(Intent.class);

        // Build parameters.
        // Add Context object.
        methodSpecBuilder.addParameter(Context.class, "context");
        buildParameters(methodSpecBuilder, activityExtrasGrouped, elementUtils, typeUtils);

        // Declare bundle object.
        methodSpecBuilder.addStatement("$T bundle = new $T()", Bundle.class, Bundle.class);
        // Put extras base on key, value to bundle.
        bindExtras(methodSpecBuilder, activityExtrasGrouped, "bundle");

        // Build and return Intent.
        return methodSpecBuilder.addStatement("$T intent = new $T(context, $L)", Intent.class, Intent.class, activityExtrasGrouped.getActivityQualifiedClassName() + ".class")
                .addStatement("intent.putExtras(bundle)")
                .addStatement("return intent")
                .build();
    }

    private void bindExtras(MethodSpec.Builder methodSpecBuilder, ActivityExtrasGrouped activityExtrasGrouped, String targetName) {
        final List<ExtraAnnotatedClass> extraAnnotatedClasses = activityExtrasGrouped.getExtraAnnotatedClasses();
        if (extraAnnotatedClasses == null || extraAnnotatedClasses.size() == 0) {
            return;
        }
        for (ExtraAnnotatedClass extraAnnotatedClass : extraAnnotatedClasses) {
            final ExtraBinder extraBinder = ExtraBinderProvider.get(extraAnnotatedClass.getDataTypeQualifiedClassName());
            if (extraBinder == ExtraBinder.INTEGER
                    || extraBinder == ExtraBinder.LONG
                    || extraBinder == ExtraBinder.FLOAT
                    || extraBinder == ExtraBinder.DOUBLE
                    || extraBinder == ExtraBinder.BOOLEAN
                    ) {
                methodSpecBuilder.addStatement("$L.set($L, $S, $L)", ExtraBinder.class.getName() + "." + extraBinder.toString(), targetName, extraAnnotatedClass.getKey(), extraAnnotatedClass.getKey());
            } else {
                methodSpecBuilder
                        .beginControlFlow("if($L != null)", extraAnnotatedClass.getKey())
                        .addStatement("$L.set($L, $S, $L)", ExtraBinder.class.getName() + "." + extraBinder.toString(), targetName, extraAnnotatedClass.getKey(), extraAnnotatedClass.getKey())
                        .endControlFlow();
            }
        }
    }

    private void buildParameters(MethodSpec.Builder methodSpecBuilder, ActivityExtrasGrouped activityExtrasGrouped, Elements elementUtils, Types typeUtils) {
        final List<ExtraAnnotatedClass> extraAnnotatedClasses = activityExtrasGrouped.getExtraAnnotatedClasses();
        if (extraAnnotatedClasses == null || extraAnnotatedClasses.size() == 0) {
            return;
        }
        for (ExtraAnnotatedClass extraAnnotatedClass : extraAnnotatedClasses) {
            methodSpecBuilder.addParameter(TypeName.get(extraAnnotatedClass.getDataType()), extraAnnotatedClass.getKey());
        }
    }

    public void clear() {
        activityExtrasGroupeds.clear();
    }
}
