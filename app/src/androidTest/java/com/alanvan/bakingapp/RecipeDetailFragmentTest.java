package com.alanvan.bakingapp;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.alanvan.bakingapp.ui.main.MainActivity;
import com.alanvan.bakingapp.ui.recipe_detail.RecipeDetailActivity;
import com.squareup.haha.perflib.Main;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.alanvan.bakingapp.Constants.RECIPE_ID;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class RecipeDetailFragmentTest {
    @Rule
    public ActivityTestRule<RecipeDetailActivity> activityTestRule = new ActivityTestRule<>(RecipeDetailActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void registerIdlingResource() {
        idlingResource = activityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void scrollToPosition() {
        onView(withId(R.id.recyclerView)).perform(android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition(3));
    }

    @Test
    public void clickOnItemAtPosition0() {
        onView(withId(R.id.recyclerView)).perform(android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void clickOnItemAtPosition1() {
        onView(withId(R.id.recyclerView)).perform(android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }
}
