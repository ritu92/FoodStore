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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void homeActivityTest() {
        ViewInteraction viewPager = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction viewPager2 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager2.perform(swipeLeft());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerViewHome), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction viewPager3 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager3.perform(swipeLeft());

        ViewInteraction viewPager4 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager4.perform(swipeLeft());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recyclerViewHome), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction viewPager5 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager5.perform(swipeRight());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), isDisplayed()));
       // textView.perform(click());

        ViewInteraction viewPager6 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager6.perform(swipeLeft());

        ViewInteraction viewPager7 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager7.perform(swipeLeft());

        ViewInteraction viewPager8 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager8.perform(swipeRight());

        ViewInteraction viewPager9 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager9.perform(swipeRight());

        ViewInteraction button = onView(
                allOf(withId(R.id.bt_trackOrder),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.realtabcontent),
                                        0),
                                1),
                        isDisplayed()));
//        button.check(matches(isDisplayed()));

        ViewInteraction viewPager10 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager10.perform(swipeLeft());

        ViewInteraction viewPager11 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager11.perform(swipeLeft());

        ViewInteraction viewPager12 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager12.perform(swipeRight());

        ViewInteraction viewPager13 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager13.perform(swipeRight());

        ViewInteraction viewPager14 = onView(
                allOf(withId(R.id.pagerHome), isDisplayed()));
        viewPager14.perform(swipeLeft());

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
