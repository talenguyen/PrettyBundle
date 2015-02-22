package com.tale.prettybundle.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.tale.prettybundle.Extra;

/**
 * Created by tale on 2/1/15.
 */
public class TestStringExtra1Activity extends ActionBarActivity {

    @Extra String stringExtra1;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_extra);

//        if (TextUtils.isEmpty(stringExtra)) {
//            tvStringExtra.setText(stringExtra);
//        }
    }
}
