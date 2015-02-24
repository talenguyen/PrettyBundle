package com.tale.prettybundle.sample;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by tale on 2/18/15.
 */
public class InjectParcelableExtrasTest extends ActivityInstrumentationTestCase2<MenuActivity> {

    public InjectParcelableExtrasTest() {
        super(MenuActivity.class);
    }

    @Override public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testStartActivityTestStringExtra2WithExtras() throws Exception {
        Espresso.onView(ViewMatchers.withText(R.string.test_parcelable_extras)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withText("Giang")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText("26")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
