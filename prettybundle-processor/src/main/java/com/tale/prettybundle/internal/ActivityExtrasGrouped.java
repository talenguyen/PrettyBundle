package com.tale.prettybundle.internal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tale on 2/5/15.
 */
public class ActivityExtrasGrouped {

    private final String packageName;
    private final String activityQualifiedClassName;
    private List<ExtraAnnotatedClass> extraAnnotatedClasses = new ArrayList<ExtraAnnotatedClass>();

    public ActivityExtrasGrouped(String activityQualifiedClassName) {
        this.activityQualifiedClassName = activityQualifiedClassName;
        final int lastIndexOfDot = activityQualifiedClassName.lastIndexOf(".");
        packageName = activityQualifiedClassName.substring(0, lastIndexOfDot);
    }

    public void add(ExtraAnnotatedClass extraAnnotatedClass) {
        extraAnnotatedClasses.add(extraAnnotatedClass);
    }

    public String getActivityQualifiedClassName() {
        return activityQualifiedClassName;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<ExtraAnnotatedClass> getExtraAnnotatedClasses() {
        return extraAnnotatedClasses;
    }
}
