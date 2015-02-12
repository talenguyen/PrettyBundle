package com.tale.prettybundle.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.tale.prettybundle.Extra;

/**
 * Created by tale on 2/1/15.
 */
public class TestStringExtra2Activity extends ActionBarActivity {

    @Extra String stringExtra1;
    @Extra String stringExtra2;

    private TextView tvStringExtra;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_extra);

        tvStringExtra = ((TextView) findViewById(R.id.tvStringExtra));
//        if (TextUtils.isEmpty(stringExtra)) {
//            tvStringExtra.setText(stringExtra);
//        }
    }
}
