package com.tale.prettybundle.sample;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by tale on 2/18/15.
 */
public class InjectActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public InjectActivityTest() {
        super(MainActivity.class);
    }

    @Override public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testStartActivityTestStringExtra2() throws Exception {
        final String extra1 = "Giang";
        final String extra2 = "Nguyen";
        Espresso.onView(ViewMatchers.withHint(R.string.string_extra_1)).perform(ViewActions.typeText(extra1));
        Espresso.onView(ViewMatchers.withHint(R.string.string_extra_2)).perform(ViewActions.typeText(extra2));

        Espresso.onView(ViewMatchers.withText(R.string.submit)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withText(extra1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(extra2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
