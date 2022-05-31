package com.monari.movix;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.*;

import android.content.Intent;


@RunWith(RobolectricTestRunner.class)
public class ProfileActivityTest {
    private ProfileActivity activity;
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(ProfileActivity.class)
                .create()
                .resume()
                .get();
    }


    @Test
    public void moviesActivityStarted(){
        activity.findViewById(R.id.getStarted).performClick();
        Intent expectedIntent = new Intent(activity, MoviesActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}