package com.tale.prettybundle.sample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.tale.prettybundle.Extra;
import com.tale.prettybundle.PrettyBundle;

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
    @Extra Parcelable[] people;

    @InjectView(R.id.tvInts) TextView tvInts;
    @InjectView(R.id.tvLongs) TextView tvLongs;
    @InjectView(R.id.tvFloats) TextView tvFloats;
    @InjectView(R.id.tvDoubles) TextView tvDoubles;
    @InjectView(R.id.tvBooleans) TextView tvBooleans;
    @InjectView(R.id.tvStrings) TextView tvStrings;
    @InjectView(R.id.tvPeople) TextView tvPeople;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_array_extras);
        ButterKnife.inject(this);
        PrettyBundle.inject(this);

        StringBuilder sb = new StringBuilder("");
        if (ints != null && ints.length > 0) {
            sb.append("{");
            for (int i = 0; i < ints.length; i++) {
                sb.append(ints[i]);
                if (i < ints.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
        }
        tvInts.setText(sb.toString());
        sb.setLength(0);

        if (longs != null && longs.length > 0) {
            sb.append("{");
            for (int i = 0; i < longs.length; i++) {
                sb.append(longs[i]);
                if (i < longs.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
        }
        tvLongs.setText(sb.toString());
        sb.setLength(0);

        if (floats != null && floats.length > 0) {
            sb.append("{");
            for (int i = 0; i < floats.length; i++) {
                sb.append(floats[i]);
                if (i < floats.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
        }
        tvFloats.setText(sb.toString());
        sb.setLength(0);

        if (doubles != null && doubles.length > 0) {
            sb.append("{");
            for (int i = 0; i < doubles.length; i++) {
                sb.append(doubles[i]);
                if (i < doubles.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
        }
        tvDoubles.setText(sb.toString());
        sb.setLength(0);

        if (booleans != null && booleans.length > 0) {
            sb.append("{");
            for (int i = 0; i < booleans.length; i++) {
                sb.append(booleans[i]);
                if (i < booleans.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
        }
        tvBooleans.setText(sb.toString());
        sb.setLength(0);

        if (strings != null && strings.length > 0) {
            sb.append("{");
            for (int i = 0; i < strings.length; i++) {
                sb.append(strings[i]);
                if (i < strings.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
        }
        tvStrings.setText(sb.toString());
        sb.setLength(0);

        if (people != null && people.length > 0) {
            sb.append("{");
            for (int i = 0; i < people.length; i++) {
                sb.append(people[i].toString());
                if (i < people.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
        }
        tvPeople.setText(sb.toString());
        sb.setLength(0);
    }
}
