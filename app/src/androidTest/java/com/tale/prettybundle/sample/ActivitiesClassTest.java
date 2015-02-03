package com.tale.prettybundle.sample;

import android.test.AndroidTestCase;

/**
 * Created by tale on 2/1/15.
 */
public class ActivitiesClassTest extends AndroidTestCase {

    public static final String ACTIVITIES_CLASS = "com.tale.prettybundle.sample.Activities";

    @Override public void setUp() throws Exception {
        super.setUp();

    }

    public void testActivitiesClassExist() throws Exception {
        final Class<?> classActivities = Class.forName(ACTIVITIES_CLASS);
    }
}
