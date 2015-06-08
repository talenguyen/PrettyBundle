package com.tale.prettybundle.sample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.tale.prettybundle.Fragments;

/**
 * Created by giang on 2/26/15.
 */
public class TestExtraOnFragment4Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_page);
        if (savedInstanceState == null) {
            Fragment fragment = Fragments.createTestPrimaryExtraFragment(1, 2, 3.4f, 5.6d, true, "String value", "CharSequence value"); // Create fragment.
            getFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
