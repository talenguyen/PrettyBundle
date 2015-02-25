package com.tale.prettybundle.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.tale.prettybundle.Extra;
import com.tale.prettybundle.PrettyBundle;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by giang on 2/24/15.
 */
public class TestPrimaryTypeDisplayActivity extends Activity {

    @Extra int intVal = 0;
    @Extra long longVal = 0;
    @Extra float floatVal = 0;
    @Extra double doubleVal = 0;
    @Extra boolean booleanVal = false;
    @Extra String stringVal = "Default";
    @Extra CharSequence charSequenceVal = "Default";

    @InjectView(R.id.tvInt) TextView tvInt;
    @InjectView(R.id.tvLong) TextView tvLong;
    @InjectView(R.id.tvFloat) TextView tvFloat;
    @InjectView(R.id.tvDouble) TextView tvDouble;
    @InjectView(R.id.tvBoolean) TextView tvBoolean;
    @InjectView(R.id.tvString) TextView tvString;
    @InjectView(R.id.tvCharSequence) TextView tvCharSequence;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_primary_type_display);
        ButterKnife.inject(this);
        ButterKnife.inject(this);

        ButterKnife.inject(this);
        PrettyBundle.inject(this);

        tvInt.setText(String.valueOf(intVal));
        tvLong.setText(String.valueOf(longVal));
        tvFloat.setText(String.valueOf(floatVal));
        tvDouble.setText(String.valueOf(doubleVal));
        tvBoolean.setText(String.valueOf(booleanVal));
        tvString.setText(stringVal);
        tvCharSequence.setText(charSequenceVal);
    }
}
