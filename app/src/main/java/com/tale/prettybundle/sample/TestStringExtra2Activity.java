package com.tale.prettybundle.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.tale.prettybundle.Extra;
import com.tale.prettybundle.PrettyBundle;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by tale on 2/1/15.
 */
public class TestStringExtra2Activity extends ActionBarActivity {

    @Extra String stringExtra1;
    @Extra String stringExtra2;
    @InjectView(R.id.tvStringExtra1) TextView tvStringExtra1;
    @InjectView(R.id.tvStringExtra2) TextView tvStringExtra2;


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_extra);
        ButterKnife.inject(this);
        PrettyBundle.inject(this);

        if (!TextUtils.isEmpty(stringExtra1)) {
            tvStringExtra1.setText(stringExtra1);
        }

        if (!TextUtils.isEmpty(stringExtra2)) {
            tvStringExtra2.setText(stringExtra2);
        }
    }
}
