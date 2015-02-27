package com.tale.prettybundle.sample;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.CheckBox;

import com.tale.prettybundle.sample.espresso.ExtViewActions;

/**
 * Created by tale on 2/18/15.
 */
public class InjectPrimaryTypeExtrasTest extends ActivityInstrumentationTestCase2<TestPrimaryTypeSetterActivity> {

    private TestPrimaryTypeSetterActivity activity;

    public InjectPrimaryTypeExtrasTest() {
        super(TestPrimaryTypeSetterActivity.class);
    }

    @Override public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    public void testStartPrimaryTypeDisplayWithExtras() throws Exception {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override public void run() {
                ((CheckBox) activity.findViewById(R.id.cbBoolean)).setChecked(true);
            }
        });
        final String integerExtra = "1";
        Espresso.onView(ViewMatchers.withHint(R.string.hint_int)).perform(ViewActions.typeText(integerExtra), ViewActions.pressImeActionButton());
        final String longExtra = "2";
        Espresso.onView(ViewMatchers.withHint(R.string.hint_long)).perform(ViewActions.typeText(longExtra), ViewActions.pressImeActionButton());
        final String floatExtra = "3.4";
        Espresso.onView(ViewMatchers.withHint(R.string.hint_float)).perform(ViewActions.typeText(floatExtra), ViewActions.pressImeActionButton());
        final String doubleExtra = "5.6";
        Espresso.onView(ViewMatchers.withHint(R.string.hint_double)).perform(ViewActions.typeText(doubleExtra), ViewActions.pressImeActionButton());
        final String stringExtra = "String value";
        Espresso.onView(ViewMatchers.withHint(R.string.hint_string)).perform(ViewActions.typeText(stringExtra), ViewActions.pressImeActionButton());
        final String charSequenceExtra = "CharSequence value";
        Espresso.onView(ViewMatchers.withHint(R.string.hint_char_sequence)).perform(ViewActions.typeText(charSequenceExtra), ViewActions.pressImeActionButton());

        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withText(R.string.submit)).perform(ExtViewActions.waitForSoftKeyboard(), ViewActions.scrollTo(), ViewActions.click());

        Espresso.onView(ViewMatchers.withText(integerExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(longExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(floatExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(doubleExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText("true")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(stringExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(charSequenceExtra)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
