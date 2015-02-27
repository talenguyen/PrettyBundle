package com.tale.prettybundle.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tale.prettybundle.Activities;
import com.tale.prettybundle.sample.data.Person;

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

    @OnClick(R.id.btTestParcelableExtras) public void testTestParcelableExtras() {
        startActivity(Activities.createTestParcelableActivityIntent(this, new Person("Giang", 26)));
    }

    @OnClick(R.id.btTestArrayExtras) public void testTestArrayExtras() {
        final int[] ints = {1, 2, 3};
        final long[] longs = {4, 5, 6};
        final float[] floats = {4.1f, 5.1f, 6.1f};
        final double[] doubles = {7.2d, 8.2d, 9.2d};
        final boolean[] booleans = {true, false, false, true};
        final String[] strings = {"One", "Two", "Three"};
        final Person[] persons = {new Person("p1", 18), new Person("p2", 19)};
        startActivity(Activities.createTestArrayExtrasActivityIntent(this, ints, longs, floats, doubles, booleans, strings, persons));
    }

    @OnClick(R.id.btTestFragmentInject) public void testFragmentInject() {
        startActivity(new Intent(this, TestExtraOnFragmentActivity.class));
    }
}
