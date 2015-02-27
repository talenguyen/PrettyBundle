package com.tale.prettybundle.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.tale.prettybundle.Activities;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends FragmentActivity {

    @InjectView(R.id.etExtra1) EditText etExtra1;
    @InjectView(R.id.etExtra2) EditText etExtra2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this, this);
    }


    @OnClick(R.id.btSubmit) public void submit() {
        String extra1 = etExtra1.getText().toString();
        final String extra2 = etExtra2.getText().toString();
        if (TextUtils.isEmpty(extra1)) {
            extra1 = null;
        }
        final Intent intent = Activities.createTestStringExtra2ActivityIntent(this, extra1, extra2);
        startActivity(intent);
    }
}
