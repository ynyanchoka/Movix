package com.monari.movix;



import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MoviesActivityInstrumentationTest {

    @Rule
    public ActivityScenarioRule<MoviesActivity> activityRule =
            new ActivityScenarioRule<>(MoviesActivity.class);




}
