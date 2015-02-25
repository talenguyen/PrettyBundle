package com.tale.prettybundle.sample;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import com.tale.prettybundle.sample.espresso.ExtViewActions;

/**
 * Created by tale on 2/18/15.
 */
public class InjectPrimaryTypeExtrasTest extends ActivityInstrumentationTestCase2<TestPrimaryTypeSetterActivity> {

    public InjectPrimaryTypeExtrasTest() {
        super(TestPrimaryTypeSetterActivity.class);
    }

    @Override public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testStartPrimaryTypeDisplayWithExtras() throws Exception {
        final String integerExtra = "1";
        Espresso.onView(ViewMatchers.withHint(R.string.hint_int)).perform(ViewActions.typeText(integerExtra));
        final String longExtra = "2";
        Espresso.onView(ViewMatchers.withHint(R.string.hint_long)).perform(ViewActions.typeText(longExtra));
        final String floatExtra = "3.4";
        Espresso.onView(ViewMatchers.withHint(R.string.hint_float)).perform(ViewActions.typeText(floatExtra));
        final String doubleExtra = "5.6";
        Espresso.onView(ViewMatchers.withHint(R.string.hint_double)).perform(ViewActions.typeText(doubleExtra));
        Espresso.onView(ViewMatchers.withHint(R.string.hint_true_or_false)).perform(ViewActions.click());
        final String stringExtra = "String value";
        Espresso.onView(ViewMatchers.withHint(R.string.hint_string)).perform(ViewActions.typeText(stringExtra));
        final String charSequenceExtra = "CharSequence value";
        Espresso.onView(ViewMatchers.withHint(R.string.hint_char_sequence)).perform(ViewActions.typeText(charSequenceExtra));

        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withText(R.string.submit)).perform(ExtViewActions.waitForSoftKeyboard(), ViewActions.click());

        Espresso.onView(ViewMatchers.withText(integerExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(longExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(floatExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(doubleExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText("true")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(stringExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(charSequenceExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
