package com.tale.prettybundle.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.tale.prettybundle.Extra;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by giang on 2/25/15.
 */
public class TestArrayExtrasActivity extends Activity {

    @Extra int[] ints;
    @Extra long[] longs;
    @Extra float[] floats;
    @Extra double[] doubles;
    @Extra boolean[] booleans;
    @Extra String[] strings;

    @InjectView(R.id.tvInts) TextView tvInts;
    @InjectView(R.id.tvLongs) TextView tvLongs;
    @InjectView(R.id.tvFloats) TextView tvFloats;
    @InjectView(R.id.tvDoubles) TextView tvDoubles;
    @InjectView(R.id.tvBooleans) TextView tvBooleans;
    @InjectView(R.id.tvStrings) TextView tvStrings;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_array_extras);
        ButterKnife.inject(this);
    }
}
