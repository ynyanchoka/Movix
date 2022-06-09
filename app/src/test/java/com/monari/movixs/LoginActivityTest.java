package com.monari.movixs;

import static org.junit.Assert.assertTrue;

import com.monari.movixs.ui.LoginActivity;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest  {

    private LoginActivity activity;
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(LoginActivity.class)
                .create()
                .resume()
                .get();
    }

//    @Test
//    public void moviesActivityStarted(){
//        activity.findViewById(R.id.idLoginButton).performClick();
//        Intent expectedIntent = new Intent(activity, MoviesActivity.class);
//        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
//        Intent actualIntent = shadowActivity.getNextStartedActivity();
//        assertTrue(actualIntent.filterEquals(expectedIntent));
//    }
}