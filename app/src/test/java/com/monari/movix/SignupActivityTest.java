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

}
