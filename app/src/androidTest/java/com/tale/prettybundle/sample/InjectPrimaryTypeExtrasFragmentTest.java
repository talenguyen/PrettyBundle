package com.tale.prettybundle.sample;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by tale on 2/18/15.
 */
public class InjectPrimaryTypeExtrasFragmentTest extends ActivityInstrumentationTestCase2<TestExtraOnFragmentActivity> {

    public InjectPrimaryTypeExtrasFragmentTest() {
        super(TestExtraOnFragmentActivity.class);
    }

    @Override public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testStartPrimaryTypeDisplayWithExtras() throws Exception {
        final String integerExtra = "1";
        final String longExtra = "2";
        final String floatExtra = "3.4";
        final String doubleExtra = "5.6";
        final String stringExtra = "String value";
        final String charSequenceExtra = "CharSequence value";

        Espresso.onView(ViewMatchers.withText(integerExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(longExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(floatExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(doubleExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText("true")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(stringExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(charSequenceExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
