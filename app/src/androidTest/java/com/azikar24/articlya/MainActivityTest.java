package com.azikar24.articlya;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    public MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }


    @Test
    public void testLaunch() {
        System.out.println("Testing successful Launch");
        ViewPager view = mainActivity.findViewById(R.id.viewPager);
        assertNotNull(view);
    }


    @Test
    public void init() {
        System.out.println("Testing init()");

        ViewPager view = mainActivity.findViewById(R.id.viewPager);
        int tabLayoutLength = view.getAdapter().getCount();
        TabLayout tblayout = mainActivity.findViewById(R.id.period_tab);
        String[] tabNames = {"Today", "Week", "Month"};
        boolean flag = tabLayoutLength == 3;

        for (int i = 0; i < tblayout.getTabCount() && !flag; i++) {
            String tabText = tblayout.getTabAt(0).getText().toString();
            if (!tabNames[i].equals(tabText)) {
                break;
            }
        }

        assertTrue(flag);
    }

    @Test
    public void tab() {
        System.out.println("Testing tabLayoutSelectedListener");
        ViewInteraction tabView = onView(
                allOf(withContentDescription("Week"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.period_tab),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());
        ViewPager view = mainActivity.findViewById(R.id.viewPager);
        assertEquals(view.getCurrentItem(), 1);
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