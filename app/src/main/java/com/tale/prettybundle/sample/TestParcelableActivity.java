package com.tale.prettybundle.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.tale.prettybundle.Extra;
import com.tale.prettybundle.PrettyBundle;
import com.tale.prettybundle.sample.data.Person;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class TestParcelableActivity extends ActionBarActivity {

    @Extra Person person;

    @InjectView(R.id.tvName) TextView tvName;
    @InjectView(R.id.tvAge) TextView tvAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_parcelable);
        ButterKnife.inject(this);
        PrettyBundle.inject(this);

        if (person != null) {
            tvAge.setText(String.valueOf(person.age));
            tvName.setText(person.name);
        }
    }

}
