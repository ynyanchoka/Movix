package com.monari.movixs;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.monari.movixs.ui.SignupActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest //testing integration of all application components
public class SignupActivityInstrumentationTest {

    @Rule
    public ActivityScenarioRule<SignupActivity> activityRule =
            new ActivityScenarioRule<>(SignupActivity.class);

    @Test
    public void validateEditText() {
        onView(withId(R.id.name)).perform(typeText("Ymelda"))
                .check(matches(withText("Ymelda")));//
        //matches() is a ViewAssertion method that validates the specific properties of the given view
    }

    @Test
    public void detailsIsSentToProfileActivity(){
        String name = "Ymelda";
        onView(withId(R.id.name)).perform(typeText(name)).perform(closeSoftKeyboard());
        try {                             // the sleep method requires to be checked and handled so we use try block
            Thread.sleep(250);
        } catch (InterruptedException e){
            System.out.println("got interrupted!");
        }
        onView(withId(R.id.idSignUpButton)).perform(click());
        onView(withId(R.id.idSignUpButton)).check(matches
                (withText("Name: " + name)));
    }
}
