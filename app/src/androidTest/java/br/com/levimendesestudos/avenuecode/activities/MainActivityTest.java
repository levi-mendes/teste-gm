package br.com.levimendesestudos.avenuecode.activities;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.com.levimendesestudos.avenuecode.R;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testShouldShowTvNoResults() {
        onView(ViewMatchers.withId(R.id.etSearch)).perform(typeText("0000099384958984598459"));
        onView(withId(R.id.ibSearch)).perform(click());
        onView(withId(R.id.tvNoResults)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowItemSave() {
        onView(withId(R.id.etSearch)).perform(typeText("Springfield"));
        onView(withId(R.id.ibSearch)).perform(click());
        onView(withId(R.id.rvAddresses)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.itemSave)).check(matches(isDisplayed()));
    }

}
