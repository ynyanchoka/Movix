package com.monari.movix;

import static org.junit.Assert.assertTrue;

import android.content.Intent;

import com.monari.movix.ui.LoginActivity;
import com.monari.movix.ui.SignupActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    //configure our test class so it knows which Activity we will use to write (and assert) our tests:
        private MainActivity activity;
        @Before
        public void setUp() throws Exception {
            activity = Robolectric.buildActivity(MainActivity.class)
                    .create()
                    .resume()
                    .get();
        }

    @Test
    public void secondActivityStarted(){
        activity.findViewById(R.id.loginButton).performClick();
        Intent expectedIntent = new Intent(activity, LoginActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

    @Test
    public void anotherActivityStarted(){
        activity.findViewById(R.id.signUpButton).performClick();
        Intent expectedIntent = new Intent(activity, SignupActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }


}
