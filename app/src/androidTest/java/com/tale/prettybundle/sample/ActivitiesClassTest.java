package com.tale.prettybundle.sample;

import android.content.Context;
import android.test.AndroidTestCase;

/**
 * Created by tale on 2/1/15.
 */
public class ActivitiesClassTest extends AndroidTestCase {

    public static final String ACTIVITIES_CLASS = "com.tale.prettybundle.Activities";

    @Override public void setUp() throws Exception {
        super.setUp();

    }

    public void testActivitiesClassExist() throws Exception {
        // Verify class exist.
        final Class<?> classActivities = Class.forName(ACTIVITIES_CLASS);

        // Verify create method for TestExtraActivity exist..
        classActivities.getMethod("createTestStringExtra1ActivityIntent", Context.class, String.class);

        // Verify create method for Test1ExtraActivity exist..
        classActivities.getMethod("createTestStringExtra2ActivityIntent", Context.class, String.class, String.class);
    }
}
