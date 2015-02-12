package com.tale.prettybundle.internal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tale on 2/5/15.
 */
public class ActivityExtrasGrouped {

    private final String activityQualifiedClassName;
    private List<ExtraAnnotatedClass> extraAnnotatedClasses = new ArrayList<ExtraAnnotatedClass>();

    public ActivityExtrasGrouped(String activityQualifiedClassName) {
        this.activityQualifiedClassName = activityQualifiedClassName;
    }

    public void add(ExtraAnnotatedClass extraAnnotatedClass) {
        extraAnnotatedClasses.add(extraAnnotatedClass);
    }

    public String getActivityQualifiedClassName() {
        return activityQualifiedClassName;
    }

    public List<ExtraAnnotatedClass> getExtraAnnotatedClasses() {
        return extraAnnotatedClasses;
    }
}
