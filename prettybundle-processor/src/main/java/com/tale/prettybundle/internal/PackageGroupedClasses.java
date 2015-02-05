package com.tale.prettybundle.internal;

import com.squareup.javapoet.JavaPoet;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;

/**
 * Created by tale on 2/5/15.
 */
public class PackageGroupedClasses {

    private static final String ACTIVITIES = "Activities";

    private final String packageName;

    public PackageGroupedClasses(String packageName) {
        this.packageName = packageName;
    }

    public void generateCode(Elements elementUtils, Filer filer) throws IOException {
        TypeSpec factoryClassSpec = TypeSpec.classBuilder(ACTIVITIES)
                .addModifiers(Modifier.PUBLIC)
                .build();

        JavaPoet javaPoet = new JavaPoet();
        javaPoet.add(packageName, factoryClassSpec).writeTo(filer);
    }
}
