package com.tale.prettybundleprocessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tale on 2/5/15.
 */
public class ExtraClassesGrouped {

    private final String packageName;
    private final String extraAnnotatedClassName;
    private List<ExtraAnnotatedClass> extraAnnotatedClasses = new ArrayList<ExtraAnnotatedClass>();
    private SupportedType supportedType = SupportedType.NOP;

    public ExtraClassesGrouped(String extraAnnotatedClassName) {
        this.extraAnnotatedClassName = extraAnnotatedClassName;
        final int lastIndexOfDot = extraAnnotatedClassName.lastIndexOf(".");
        packageName = extraAnnotatedClassName.substring(0, lastIndexOfDot);
    }

    public void add(ExtraAnnotatedClass extraAnnotatedClass) {
        if (extraAnnotatedClass == null) {
            return;
        }
        if (supportedType == SupportedType.NOP) {
            supportedType = extraAnnotatedClass.getSupportedType();
        }
        extraAnnotatedClasses.add(extraAnnotatedClass);
    }

    public String getExtraAnnotatedClassName() {
        return extraAnnotatedClassName;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<ExtraAnnotatedClass> getExtraAnnotatedClasses() {
        return extraAnnotatedClasses;
    }

    public SupportedType getSupportedType() {
        return supportedType;
    }
}
