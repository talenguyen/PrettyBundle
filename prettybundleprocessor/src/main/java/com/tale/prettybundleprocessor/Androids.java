package com.tale.prettybundleprocessor;

import com.squareup.javapoet.ClassName;

/**
 * Created by tale on 3/25/15.
 */
public class Androids {
    public static ClassName bundleClass() {
        return ClassName.get("android.os", "Bundle");
    }

    public static ClassName contextClass() {
        return ClassName.get("android.content", "Context");
    }

    public static ClassName intentClass() {
        return ClassName.get("android.content", "Intent");
    }
}
