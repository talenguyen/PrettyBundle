package com.tale.prettybundle.internal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tale on 2/5/15.
 */
public class ExtraGroupedClasses {

    private final String activityQualifiedClassName;
    private List<ExtraAnnotatedClass> extraAnnotatedClasses = new ArrayList<>();

    public ExtraGroupedClasses(String packageName) {
        this.activityQualifiedClassName = packageName;
    }

    public void add(ExtraAnnotatedClass extraAnnotatedClass) {
        extraAnnotatedClasses.add(extraAnnotatedClass);
    }
}
