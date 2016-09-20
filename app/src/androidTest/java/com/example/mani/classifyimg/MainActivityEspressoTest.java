package com.example.mani.classifyimg;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<ClassifyingActivity> mActivityRule = new ActivityTestRule<>(
            ClassifyingActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        //mStringToBetyped = "LiSt 6 unTagged images";
        mStringToBetyped = "select 1st and 3rd images";
    }

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.input_edit_text))
                .perform(typeText(mStringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.send_button)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.input_edit_text))
                .check(matches(withText(mStringToBetyped)));

    }
}
