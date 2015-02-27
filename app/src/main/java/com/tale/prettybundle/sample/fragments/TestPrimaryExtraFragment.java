package com.tale.prettybundle.sample.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tale.prettybundle.Extra;
import com.tale.prettybundle.PrettyBundle;
import com.tale.prettybundle.sample.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by giang on 2/26/15.
 */
public class TestPrimaryExtraFragment extends Fragment {

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
        PrettyBundle.inject(this);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_test_primary_type_display, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);

        tvInt.setText(String.valueOf(intVal));
        tvLong.setText(String.valueOf(longVal));
        tvFloat.setText(String.valueOf(floatVal));
        tvDouble.setText(String.valueOf(doubleVal));
        tvBoolean.setText(String.valueOf(booleanVal));
        tvString.setText(stringVal);
        tvCharSequence.setText(charSequenceVal);
    }
}
