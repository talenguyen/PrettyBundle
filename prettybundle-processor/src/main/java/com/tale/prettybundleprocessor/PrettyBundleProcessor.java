package com.tale.prettybundleprocessor;

import com.google.auto.service.AutoService;
import com.tale.prettybundle.Extra;
import com.tale.prettybundle.ExtraBinder;

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
import javax.lang.model.element.Modifier;
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
    private ExtraUtilityClassBuilder extraUtilityClassBuilder = new ExtraUtilityClassBuilder();
    private Map<String, ExtraClassesGrouped> extraGroupedClassesMap = new Hashtable<String, ExtraClassesGrouped>();

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

            final ExtraAnnotatedClass extraAnnotatedClass = new ExtraAnnotatedClass(annotatedVariableElement, elementUtils, typeUtils);

            if (!isValidClass(extraAnnotatedClass)) {
                return true; // Error message printed, exit processing
            }

            final String activityQualifiedClassName = extraAnnotatedClass.getQualifiedClassName();
            ExtraClassesGrouped extraClassesGrouped = extraGroupedClassesMap.get(activityQualifiedClassName);
            if (extraClassesGrouped == null) {
                extraClassesGrouped = new ExtraClassesGrouped(activityQualifiedClassName);
                extraGroupedClassesMap.put(activityQualifiedClassName, extraClassesGrouped);
            }
            extraClassesGrouped.add(extraAnnotatedClass);

            if (!extraUtilityClassBuilder.contains(extraClassesGrouped)) {
                extraUtilityClassBuilder.add(extraClassesGrouped);
            }
        }

        try {
            // Generate Activities util class.
            extraUtilityClassBuilder.generateCode(elementUtils, typeUtils, filer);

            // Generate Activity$$Injector classes.
            for (ExtraClassesGrouped extraClassesGrouped : extraGroupedClassesMap.values()) {
                try {
                    new ExtraInjectorClassBuilder(extraClassesGrouped).generateCode(elementUtils, filer);
                } catch (IllegalAccessException e) {
                    error(null, e.getMessage());
                }
            }
            extraUtilityClassBuilder.clear();
            extraGroupedClassesMap.clear();
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
        // Verify if class is supported or not.
        if (extraAnnotatedClass.getSupportedType() == SupportedType.NOP) {
            error(extraAnnotatedClass.getAnnotatedVariableElement(), "Only support Activity, Fragment and Service");
        }
        // Verify modifiers is not private or protected
        if (extraAnnotatedClass.getAnnotatedVariableElement().getModifiers().contains(Modifier.PRIVATE)) {
            error(extraAnnotatedClass.getAnnotatedVariableElement(), "The data type must not declared by private modifier");
        } else if (extraAnnotatedClass.getAnnotatedVariableElement().getModifiers().contains(Modifier.PROTECTED)) {
            error(extraAnnotatedClass.getAnnotatedVariableElement(), "The data type must not declared by protected modifier");
        }
        // Check if data type is supported or not.
        if (!isDataTypeSupported(extraAnnotatedClass)) {
            error(extraAnnotatedClass.getAnnotatedVariableElement(), "Data type: %s is not supported", extraAnnotatedClass.getDataTypeQualifiedClassName());
            return false;
        }
        return true;
    }

    private boolean isDataTypeSupported(ExtraAnnotatedClass extraAnnotatedClass) {
        if (extraAnnotatedClass.getExtraBinder() == ExtraBinder.NOP) {
            return false;
        }
        return true;
    }

    private void error(Element element, String message, String... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(message, args), element);
    }
}
