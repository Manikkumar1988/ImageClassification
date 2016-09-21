package com.example.mani.classifyimg;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mani.classifyimg.adapter.ImagesAdapter;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.addGlobalAssertion;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;



@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<ClassifyingActivity> mActivityRule = new ActivityTestRule<>(
            ClassifyingActivity.class);

    @Before
    public void initValidString() {
        //mStringToBetyped = "list 7 untagged images";
        //mStringToBetyped = "select 1st and images";
        mStringToBetyped = "select 1st, 2nd and 3rd images";
    }

    @Test
    public void changeText() {
        // Type text and then press the button.
        onView(withId(R.id.input_edit_text))
                .perform(typeText(mStringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.send_button)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.input_edit_text))
                .check(matches(withText(mStringToBetyped)));

    }


    @Test
    public void loadRecycler()
    {
        type(mActivityRule.getActivity().getString(R.string.list_all_images));

        onView(ViewMatchers.withId(R.id.recycler_view)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }


    @Test
    public void loadXImages()
    {
        type("List 15 untagged images");

        onView(ViewMatchers.withId(R.id.recycler_view)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recycler_view)).check(new RecyclerViewItemCountAssertion(15));
    }

    @Test
    public void selectXImages()
    {
        type("List 15 untagged images");
        type("select 1st 2nd 3rd images");

        onView(ViewMatchers.withId(R.id.recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(3, click()));

        onView(ViewMatchers.withId(R.id.recycler_view)).check(new RecyclerViewSelectedItemCountAssertion(3));
    }


    private void type(String input) {
        onView(withId(R.id.input_edit_text))
                .perform(typeText(input), closeSoftKeyboard());
        onView(withId(R.id.send_button)).perform(click());
    }

    @Test
    public void classifyXImages()
    {

        type("List 13 untagged images");
        type("select 1st, 2nd and 3rd images");
        onView(ViewMatchers.withId(R.id.recycler_view)).check(matches(isDisplayed()));

        onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, click()));


        onView(ViewMatchers.withId(R.id.recycler_view)).check(new RecyclerViewSelectedItemCountAssertion(3));

        type("classify as nature");
        onView(ViewMatchers.withId(R.id.recycler_view)).check(new RecyclerViewSelectedItemCountAssertion(0));
        onView(ViewMatchers.withId(R.id.recycler_view)).check(new RecyclerViewItemCountAssertion(10));


    }

    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            ImagesAdapter adapter = (ImagesAdapter) recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), Matchers.is(expectedCount));
        }
    }

    public class RecyclerViewSelectedItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewSelectedItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            ImagesAdapter adapter = (ImagesAdapter) recyclerView.getAdapter();
            assertThat(adapter.getSelectedItemCount(), Matchers.is(expectedCount));
        }
    }
}
