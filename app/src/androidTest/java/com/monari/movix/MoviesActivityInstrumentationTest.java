package com.monari.movix;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;

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
    public ActivityScenarioRule<MoviesActivity> activityTestRule =
            new ActivityScenarioRule<>(MoviesActivity.class);
    private View activityDecorView;//what is this?


    @Before
    public void setUp() {
        activityTestRule.getScenario().onActivity(new ActivityScenario.ActivityAction<MoviesActivity>() {
            @Override
            public void perform(MoviesActivity activity) {
                activityDecorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Test
    public void listItemClickDisplaysToastWithCorrectMovie() {
        String movieName = "Red Notice"; // Espresso to check that clicking on the first item (.atPosition(0)) in our ListView results in a Toast that displays "Mi Mero Mole"
        onData(anything())//To interact with the data in an adapter we must use the onData() method rather than onView()
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());
        onView(withText(movieName)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(movieName)));
    }

}
