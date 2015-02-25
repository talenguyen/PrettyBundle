package com.tale.prettybundle.sample;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;

import com.tale.prettybundle.Activities;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by giang on 2/24/15.
 */
public class TestPrimaryTypeSetterActivity extends Activity {

    @InjectView(R.id.etInt) EditText etInt;
    @InjectView(R.id.etLong) EditText etLong;
    @InjectView(R.id.etFloat) EditText etFloat;
    @InjectView(R.id.etDouble) EditText etDouble;
    @InjectView(R.id.cbBoolean) CheckBox cbBoolean;
    @InjectView(R.id.etString) EditText etString;
    @InjectView(R.id.etCharSequence) EditText etCharSequence;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_primary_type_setter);
        ButterKnife.inject(this);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btSubmit) public void submit() {

        int intVal;
        if (TextUtils.isEmpty(etInt.getText().toString())) {
            intVal = 0;
        } else {
            intVal = Integer.parseInt(etInt.getText().toString());
        }

        long longVal;
        if (TextUtils.isEmpty(etLong.getText().toString())) {
            longVal = 0;
        } else {
            longVal = Long.parseLong(etLong.getText().toString());
        }

        float floatVal;
        if (TextUtils.isEmpty(etFloat.getText().toString())) {
            floatVal = 0;
        } else {
            floatVal = Float.parseFloat(etFloat.getText().toString());
        }

        double doubleVal;
        if (TextUtils.isEmpty(etDouble.getText().toString())) {
            doubleVal = 0;
        } else {
            doubleVal = Double.parseDouble(etDouble.getText().toString());
        }

        String stringVal;
        if (TextUtils.isEmpty(etString.getText().toString())) {
            stringVal = null;
        } else {
            stringVal = etString.getText().toString();
        }

        CharSequence charSequenceVal;
        if (TextUtils.isEmpty(etCharSequence.getText())) {
            charSequenceVal = null;
        } else {
            charSequenceVal = etCharSequence.getText();
        }

        boolean booleanVal = cbBoolean.isChecked();

        startActivity(Activities.createTestPrimaryTypeDisplayActivityIntent(this, intVal, longVal, floatVal, doubleVal, booleanVal, stringVal, charSequenceVal));

    }
}
