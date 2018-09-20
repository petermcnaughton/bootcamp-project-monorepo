package com.sky.journeys.skyjourneys.pages.profile;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.pages.myjourneys.CurrentBooking;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MyJourneysSummaryPageTest {

    @Rule
    public IntentsTestRule<ProfileActivity> mActivityTestRule =
            new IntentsTestRule<>(ProfileActivity.class);

    @Test
    public void myJourneysButtonLoadsJourneysSummaryPage() {
        onView(withId(R.id.my_journeys_button)).perform(click());
        intended(hasComponent(CurrentBooking.class.getName()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.to), withText("Valencia"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.card),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.dates), withText("22/08/2018 - 29/08/2018"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.card),
                                        0),
                                2),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
