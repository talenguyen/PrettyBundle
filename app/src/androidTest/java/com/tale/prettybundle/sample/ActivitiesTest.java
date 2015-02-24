package com.tale.prettybundle.sample;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.widget.Button;
import android.widget.EditText;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tale on 2/22/15.
 */
public class ActivitiesTest extends BaseActivityUnitTestCase<MainActivity> {

    private Intent launchIntent;

    public ActivitiesTest() {
        super(MainActivity.class);
    }

    @Override public void setUp() throws Exception {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        super.setUp();
        launchIntent = new Intent(getInstrumentation()
                .getTargetContext(), MainActivity.class);
    }

    @Override public void tearDown() throws Exception {
        super.tearDown();
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public void testCreateTestStringExtra2Activity_withEnteredExtra() throws Exception {
        startActivity(launchIntent, null, null);

        EditText etExtra1 = (EditText) getActivity().findViewById(R.id.etExtra1);
        EditText etExtra2 = (EditText) getActivity().findViewById(R.id.etExtra2);

        final String extra1 = "extra1";
        final String extra2 = "extra2";

        etExtra1.setText(extra1);
        etExtra2.setText(extra2);

        ((Button) getActivity().findViewById(R.id.btSubmit)).performClick();

        final Intent startedActivityIntent = getStartedActivityIntent();

        final String stringExtra1 = startedActivityIntent.getStringExtra("stringExtra1");
        assertThat(stringExtra1).isEqualTo(extra1);

        final String stringExtra2 = startedActivityIntent.getStringExtra("stringExtra2");
        assertThat(stringExtra2).isEqualTo(extra2);
    }
}
