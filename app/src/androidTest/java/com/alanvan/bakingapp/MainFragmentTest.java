package com.alanvan.bakingapp;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.alanvan.bakingapp.ui.main.MainActivity;

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
import static com.alanvan.bakingapp.Constants.RECIPE_NAME;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class MainFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void setIdlingResource() {
        idlingResource = activityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void clickOnRecipeStartRecipeDetailsActivity() {
        onView(withId(R.id.mainRecyclerView)).perform(android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.fragment_recipe_detail)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnRecipeHasRecipeDetailsActivityIntent() {
        onView(withId(R.id.mainRecyclerView)).perform(android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(
                hasExtra(RECIPE_NAME, "Nutella Pie")
        );
    }

    @Test
    public void scrollToTheLastItem() {
        onView(withId(R.id.mainRecyclerView)).perform(android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition(3));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }
}
