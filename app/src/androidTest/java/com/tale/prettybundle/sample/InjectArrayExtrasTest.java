package com.tale.prettybundle.sample;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by giang on 2/25/15.
 */
public class InjectArrayExtrasTest extends ActivityInstrumentationTestCase2<MenuActivity> {

    public InjectArrayExtrasTest() {
        super(MenuActivity.class);
    }

    @Override public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testArrayExtrasDisplay() throws Exception {
        // Open TestArrayActivity
        Espresso.onView(ViewMatchers.withText(R.string.test_array_extras)).perform(ViewActions.click());

        // Verify result.
        // int arrays
        Espresso.onView(ViewMatchers.withText("{1,2,3}")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        // long arrays
        Espresso.onView(ViewMatchers.withText("{4,5,6}")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        // float arrays
        Espresso.onView(ViewMatchers.withText("{4.1,5.1,6.1}")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        // double arrays
        Espresso.onView(ViewMatchers.withText("{7.2,8.2,9.2}")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        // boolean arrays
        Espresso.onView(ViewMatchers.withText("{true,false,false,true}")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        // string arrays
        Espresso.onView(ViewMatchers.withText("{One,Two,Three}")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }
}
