package com.tale.prettybundleprocessor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.tale.prettybundle.ExtraBinder;

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
public class ExtraUtilityClassBuilder {

    private static final String ACTIVITIES = "Activities";

    private static final String FRAGMENTS = "Fragments";

    private static final String SERVICES = "Services";

    private static final String packageName = "com.tale.prettybundle";

    private final Map<String, ExtraClassesGrouped> activityExtrasGroupedMap = new LinkedHashMap<String, ExtraClassesGrouped>();

    private final Map<String, ExtraClassesGrouped> serviceExtrasGroupedMap = new LinkedHashMap<String, ExtraClassesGrouped>();

    private final Map<String, ExtraClassesGrouped> fragmentExtrasGroupedMap = new LinkedHashMap<String, ExtraClassesGrouped>();

    public ExtraUtilityClassBuilder() {
    }

    public void add(ExtraClassesGrouped extraClassesGrouped) {
        if (extraClassesGrouped == null
                || extraClassesGrouped.getExtraAnnotatedClassName() == null
                || extraClassesGrouped.getExtraAnnotatedClassName().trim().equals("")) {
            return;
        }

        final SupportedType supportedType = extraClassesGrouped.getSupportedType();
        if (supportedType == SupportedType.ACTIVITY) {
            // We replace the existed with the new or just add.
            activityExtrasGroupedMap.put(extraClassesGrouped.getExtraAnnotatedClassName(), extraClassesGrouped);
        } else if (supportedType == SupportedType.SERVICE) {
            serviceExtrasGroupedMap.put(extraClassesGrouped.getExtraAnnotatedClassName(), extraClassesGrouped);
        } else if (supportedType == SupportedType.FRAGMENT) {
            fragmentExtrasGroupedMap.put(extraClassesGrouped.getExtraAnnotatedClassName(), extraClassesGrouped);
        }
    }

    public boolean contains(ExtraClassesGrouped extraClassesGrouped) {
        if (extraClassesGrouped == null
                || extraClassesGrouped.getExtraAnnotatedClassName() == null
                || extraClassesGrouped.getExtraAnnotatedClassName().trim().equals("")) {
            return false;
        }
        final SupportedType supportedType = extraClassesGrouped.getSupportedType();
        if (supportedType == SupportedType.ACTIVITY) {
            return activityExtrasGroupedMap.containsKey(extraClassesGrouped.getExtraAnnotatedClassName());
        } else if (supportedType == SupportedType.SERVICE) {
            return serviceExtrasGroupedMap.containsKey(extraClassesGrouped.getExtraAnnotatedClassName());
        } else if (supportedType == SupportedType.FRAGMENT) {
            return fragmentExtrasGroupedMap.containsKey(extraClassesGrouped.getExtraAnnotatedClassName());
        }
        return true; // We don't want to save not supported type.
    }

    public void generateCode(Elements elementUtils, Types typeUtils, Filer filer) throws IOException {
        if (activityExtrasGroupedMap.size() > 0) {
            generateCode(ACTIVITIES, activityExtrasGroupedMap, elementUtils, typeUtils, filer);
        }
        if (serviceExtrasGroupedMap.size() > 0) {
            generateCode(SERVICES, serviceExtrasGroupedMap, elementUtils, typeUtils, filer);
        }
        if (fragmentExtrasGroupedMap.size() > 0) {
            generateCode(FRAGMENTS, fragmentExtrasGroupedMap, elementUtils, typeUtils, filer);
        }
    }

    private void generateCode(String className, Map<String, ExtraClassesGrouped> extrasGroupedMap, Elements elementUtils, Types typeUtils, Filer filer) throws IOException {
        final TypeSpec.Builder activitiesClassBuilder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC);

        final List<MethodSpec> methods = getMethods(extrasGroupedMap, elementUtils, typeUtils);

        if (methods == null || methods.size() == 0) {
            // Nothing created if there is no @Extra annotation is Added.
            return;
        }
        for (MethodSpec method : methods) {
            activitiesClassBuilder.addMethod(method);
        }

        JavaFile.builder(packageName, activitiesClassBuilder.build()).build().writeTo(filer);
    }

    private List<MethodSpec> getMethods(Map<String, ExtraClassesGrouped> extrasGroupedMap, Elements elementUtils, Types typeUtils) {
        final int size = extrasGroupedMap.size();
        if (size == 0) {
            return null;
        }
        final List<MethodSpec> methodSpecs = new ArrayList<MethodSpec>(size);
        for (ExtraClassesGrouped extraClassesGrouped : extrasGroupedMap.values()) {
            final MethodSpec methodSpec = createMethodSpec(elementUtils, typeUtils, extraClassesGrouped);
            if (methodSpec != null) {
                methodSpecs.add(methodSpec);
            }
        }
        return methodSpecs;
    }

    private MethodSpec createMethodSpec(Elements elementUtils, Types typeUtils, ExtraClassesGrouped extraClassesGrouped) {
        switch (extraClassesGrouped.getSupportedType()) {
            case ACTIVITY:
            case SERVICE:
                return createIntentMethodSpec(elementUtils, typeUtils, extraClassesGrouped);
            case FRAGMENT:
                return createFragmentMethodSpec(elementUtils, typeUtils, extraClassesGrouped);
        }
        return null;
    }

    private MethodSpec createIntentMethodSpec(Elements elementUtils, Types typeUtils, ExtraClassesGrouped extraClassesGrouped) {
        final String activityQualifiedClassName = extraClassesGrouped.getExtraAnnotatedClassName();
        final TypeElement typeElement = elementUtils.getTypeElement(activityQualifiedClassName);
        final String activityName = typeElement.getSimpleName().toString();

        // Declare method name.
        final MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder("create" + activityName + "Intent")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(Intent.class);

        // Build parameters.
        // Add Context object.
        methodSpecBuilder.addParameter(Context.class, "context");
        buildParameters(methodSpecBuilder, extraClassesGrouped);

        // Declare bundle object.
        methodSpecBuilder.addStatement("$T bundle = new $T()", Bundle.class, Bundle.class);
        // Put extras base on key, value to bundle.
        bindExtras(methodSpecBuilder, extraClassesGrouped, "bundle");

        // Build and return Intent.
        return methodSpecBuilder.addStatement("$T intent = new $T(context, $L)", Intent.class, Intent.class, extraClassesGrouped.getExtraAnnotatedClassName() + ".class")
                .addStatement("intent.putExtras(bundle)")
                .addStatement("return intent")
                .build();
    }

    private MethodSpec createFragmentMethodSpec(Elements elementUtils, Types typeUtils, ExtraClassesGrouped extraClassesGrouped) {
        final String fragmentQualifiedClassName = extraClassesGrouped.getExtraAnnotatedClassName();
        final TypeElement typeElement = elementUtils.getTypeElement(fragmentQualifiedClassName);
        final String fragmentName = typeElement.getSimpleName().toString();

        // Declare method name.
        final MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder("create" + fragmentName)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(TypeName.get(elementUtils.getTypeElement(fragmentQualifiedClassName).asType()));

        // Build parameters.
        buildParameters(methodSpecBuilder, extraClassesGrouped);

        // Declare bundle object.
        methodSpecBuilder.addStatement("$T args = new $T()", Bundle.class, Bundle.class);
        // Put extras base on key, value to bundle.
        bindExtras(methodSpecBuilder, extraClassesGrouped, "args");

        // Build and return Intent.
        return methodSpecBuilder.addStatement("$L fragment = new $L()", fragmentQualifiedClassName, fragmentQualifiedClassName)
                .addStatement("fragment.setArguments(args)")
                .addStatement("return fragment")
                .build();
    }

    private void bindExtras(MethodSpec.Builder methodSpecBuilder, ExtraClassesGrouped extraClassesGrouped, String targetName) {
        final List<ExtraAnnotatedClass> extraAnnotatedClasses = extraClassesGrouped.getExtraAnnotatedClasses();
        if (extraAnnotatedClasses == null || extraAnnotatedClasses.size() == 0) {
            return;
        }
        for (ExtraAnnotatedClass extraAnnotatedClass : extraAnnotatedClasses) {
            final ExtraBinder extraBinder = extraAnnotatedClass.getExtraBinder();
            if (extraBinder == ExtraBinder.INTEGER
                    || extraBinder == ExtraBinder.LONG
                    || extraBinder == ExtraBinder.FLOAT
                    || extraBinder == ExtraBinder.DOUBLE
                    || extraBinder == ExtraBinder.BOOLEAN
                    || extraBinder == ExtraBinder.BYTE
                    || extraBinder == ExtraBinder.SHORT
                    || extraBinder == ExtraBinder.CHAR
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

    private void buildParameters(MethodSpec.Builder methodSpecBuilder, ExtraClassesGrouped extraClassesGrouped) {
        final List<ExtraAnnotatedClass> extraAnnotatedClasses = extraClassesGrouped.getExtraAnnotatedClasses();
        if (extraAnnotatedClasses == null || extraAnnotatedClasses.size() == 0) {
            return;
        }
        for (ExtraAnnotatedClass extraAnnotatedClass : extraAnnotatedClasses) {
            methodSpecBuilder.addParameter(TypeName.get(extraAnnotatedClass.getDataType()), extraAnnotatedClass.getKey());
        }
    }

    public void clear() {
        activityExtrasGroupedMap.clear();
        serviceExtrasGroupedMap.clear();
        fragmentExtrasGroupedMap.clear();
    }
}
