package com.sky.journeys.skyjourneys.pages.profile;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.sky.journeys.skyjourneys.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class ProfileActivityTest {

    @Rule
    public IntentsTestRule<ProfileActivity> mActivityTestRule =
            new IntentsTestRule<>(ProfileActivity.class);

    @Test
    public void profileButtonsAreDisplayed() {
        ViewInteraction profileImage = onView(withId(R.id.profile_image));
        profileImage.check(matches(isDisplayed()));

        ViewInteraction myJourneysButton = onView(withId(R.id.my_journeys_button));
        myJourneysButton.check(matches(isDisplayed()));

        ViewInteraction pastJourneysButton = onView(withId(R.id.past_journeys_button));
        pastJourneysButton.check(matches(isDisplayed()));

        ViewInteraction wishlistButton = onView(withId(R.id.wishlist_button));
        wishlistButton.check(matches(isDisplayed()));

        ViewInteraction achievementsButton = onView(withId(R.id.achievements_button));
        achievementsButton.check(matches(isDisplayed()));
    }

    //TODO when past bookings page is done
//    @Test
//    public void pastJourneysButtonRedirectsPastBookingsActivity() {
//        onView(withId(R.id.my_journeys_button)).perform(click());
//        intended(hasComponent(PastBookings.class.getName()));
//    }

    //TODO when wishlist page is done
//    @Test
//    public void wishlistButtonRedirectsWishlistActivity() {
//        onView(withId(R.id.my_journeys_button)).perform(click());
//        intended(hasComponent(Wishlist.class.getName()));
//    }

    //TODO when achievements page is done
//    @Test
//    public void achievementsButtonRedirectsAchievementsActivity() {
//        onView(withId(R.id.my_journeys_button)).perform(click());
//        intended(hasComponent(Achievements.class.getName()));
//    }

}
