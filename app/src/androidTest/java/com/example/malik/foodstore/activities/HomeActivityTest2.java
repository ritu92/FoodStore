package com.example.malik.foodstore.activities;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.malik.foodstore.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest2 {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void homeActivityTest2() {

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.tabs),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        1),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction view = onView(
                allOf(withId(android.R.id.statusBarBackground),
                        childAtPosition(
                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                1),
                        isDisplayed()));
        view.check(matches(isDisplayed()));


        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), isDisplayed()));
        textView.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.view_expandable_headerlayout), isDisplayed()));
        relativeLayout.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button = onView(
                allOf(withId(R.id.bangaloreButton), withText("Bangalore"), isDisplayed()));
        button.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.title), isDisplayed()));
        textView3.perform(click());

        ViewInteraction viewPager47 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager47.perform(swipeRight());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.bt_trackOrder), withText("Track orders"), isDisplayed()));
        button2.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(android.R.id.title), isDisplayed()));
        textView4.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.bt_location), withText("Get current location"), isDisplayed()));
        button3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button4 = onView(
                allOf(withId(R.id.buttonBack), withText("Set current location as delivery address"), isDisplayed()));
        button4.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3555);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
