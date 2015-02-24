package com.tale.prettybundle.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by giang on 2/24/15.
 */
public class MenuActivity extends Activity {

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btTestStringExtras) public void testStringExtras() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @OnClick(R.id.btTestPrimaryTypeExtras) public void testPrimaryTypeExtras() {
        startActivity(new Intent(this, TestPrimaryTypeSetterActivity.class));
    }
}
