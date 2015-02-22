package com.tale.prettybundle.sample;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by tale on 2/18/15.
 */
public class InjectActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public InjectActivityTest() {
        super(MainActivity.class);
    }

    @Override public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        super.setUp();
    }

    @Override protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testStartActivityTestStringExtra2() throws Exception {
        final String extra1 = "Giang";
        final String extra2 = "Nguyen";
        solo.enterText(0, extra1);
        solo.enterText(1, extra2);

        solo.clickOnButton(getActivity().getString(R.string.submit));

        assertTrue(solo.searchText(extra1) && solo.searchText(extra2));
    }
}
