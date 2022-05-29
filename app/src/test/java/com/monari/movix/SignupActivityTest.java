package com.monari.movix;


import static org.junit.Assert.assertTrue;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;

@RunWith(RobolectricTestRunner.class)
public class SignupActivityTest {

    private SignupActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(SignupActivity.class)
                .create()
                .resume()
                .get();
    }

//    @Test
//    public void profileActivityStarted(){
//        activity.findViewById(R.id.idSignUpButton).performClick();
//        Intent expectedIntent = new Intent(activity, ProfileActivity.class);
//        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
//        Intent actualIntent = shadowActivity.getNextStartedActivity();
//        assertTrue(actualIntent.filterEquals(expectedIntent));
//    }
}
