package com.tale.prettybundle.internal;

import com.google.auto.service.AutoService;
import com.tale.prettybundle.Extra;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class PrettyBundleProcessor extends AbstractProcessor {

    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;
    private ActivitiesClassBuilder activitiesClassBuilder = new ActivitiesClassBuilder();
    private Map<String, ActivityExtrasGrouped> extraGroupedClassesMap = new Hashtable<String, ActivityExtrasGrouped>();

    @Override public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override public Set<String> getSupportedAnnotationTypes() {
        Set<String> result = new LinkedHashSet<String>();
        result.add(Extra.class.getCanonicalName());
        return result;
    }

    @Override public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(Extra.class)) {

            if (annotatedElement.getKind() != ElementKind.FIELD) {
                error(annotatedElement, "only fields can be annotated with @%s", Extra.class.getSimpleName());
                return true;
            }

            final VariableElement annotatedVariableElement = (VariableElement) annotatedElement;

            final ExtraAnnotatedClass extraAnnotatedClass = new ExtraAnnotatedClass(annotatedVariableElement);

            if (!isValidClass(extraAnnotatedClass)) {
                return true; // Error message printed, exit processing
            }

            final String activityQualifiedClassName = extraAnnotatedClass.getQualifiedClassName();
            ActivityExtrasGrouped activityExtrasGrouped = extraGroupedClassesMap.get(activityQualifiedClassName);
            if (activityExtrasGrouped == null) {
                activityExtrasGrouped = new ActivityExtrasGrouped(activityQualifiedClassName);
                extraGroupedClassesMap.put(activityQualifiedClassName, activityExtrasGrouped);
            }
            activityExtrasGrouped.add(extraAnnotatedClass);

            if (!activitiesClassBuilder.contains(activityExtrasGrouped)) {
                activitiesClassBuilder.add(activityExtrasGrouped);
            }
        }

        try {
            activitiesClassBuilder.generateCode(elementUtils, typeUtils, filer);
            activitiesClassBuilder.clear();
        } catch (IOException e) {
            error(null, e.getMessage());
        }

        return true;
    }

    private String getPackageOfActivity(String activityQualifiedClassName) {
        final TypeElement typeElement = elementUtils.getTypeElement(activityQualifiedClassName);
        return elementUtils.getPackageOf(typeElement).getQualifiedName().toString();
    }

    private boolean isValidClass(ExtraAnnotatedClass extraAnnotatedClass) {
        return true;
    }

    private void error(Element element, String message, String... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(message, args), element);
    }
}
