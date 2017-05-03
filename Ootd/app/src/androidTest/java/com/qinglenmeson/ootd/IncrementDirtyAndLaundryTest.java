package com.qinglenmeson.ootd;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IncrementDirtyAndLaundryTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void incremenetDirtyAndLaundryTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withText("+ Clothes"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.clothingedit_EditName));
        appCompatEditText.perform(pressImeActionButton());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.clothingedit_PlusWear), withText("+")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.clothingedit_PlusWear), withText("+")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.clothingedit_PlusWear), withText("+")));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.clothingedit_PlusWear), withText("+")));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.clothingedit_PlusWear), withText("+")));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.clothingedit_PlusWear), withText("+")));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.clothingedit_PlusWear), withText("+")));
        appCompatButton8.perform(scrollTo(), click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.clothingedit_PlusWear), withText("+")));
        appCompatButton9.perform(scrollTo(), click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.clothingedit_PlusWear), withText("+")));
        appCompatButton10.perform(scrollTo(), click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.clothingedit_PlusWear), withText("+")));
        appCompatButton11.perform(scrollTo(), click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.add_clothing_add_button), withText("+ Add")));
        appCompatButton12.perform(scrollTo(), click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.clothing_goBack), withText("Go Back")));
        appCompatButton13.perform(scrollTo(), click());

        ViewInteraction rotatedButton = onView(
                allOf(withText("Archives"), isDisplayed()));
        rotatedButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.clothingedit_WearIndicator), withText("10"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        5),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("10")));

        pressBack();

        pressBack();

        ViewInteraction appCompatButton14 = onView(
                allOf(withText("Laundry"), isDisplayed()));
        appCompatButton14.perform(click());

        ViewInteraction rotatedButton2 = onView(
                allOf(withText("Archives"), isDisplayed()));
        rotatedButton2.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.clothingedit_WearIndicator), withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        5),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("0")));

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
