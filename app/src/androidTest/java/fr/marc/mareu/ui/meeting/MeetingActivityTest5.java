package fr.marc.mareu.ui.meeting;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.marc.mareu.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MeetingActivityTest5 {

    @Rule
    public ActivityTestRule<MeetingActivity> mActivityTestRule = new ActivityTestRule<>(MeetingActivity.class);

    @Test
    public void meetingActivityTest5() {
        onView(allOf(withId(R.id.menu_filter), withContentDescription("Item")                )).perform(click());

        onView(allOf(withId(R.id.title), withText("Chose a date"),
                childAtPosition(childAtPosition(withId(R.id.content), 0), 0), isDisplayed())).perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        onView(allOf(withId(R.id.menu_filter), withContentDescription("Item"),
                childAtPosition(childAtPosition(withId(R.id.toolbar2), 1), 0), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.title), withText("Chose meeting rooms"),
                        childAtPosition(childAtPosition(withId(R.id.content), 0), 0), isDisplayed())).perform(click());

        onData(anything()).inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(withId(R.id.contentPanel), 0))).atPosition(0).perform(click());

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.menu_filter), withContentDescription("Item"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar2),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.title), withText("Clean filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView3.perform(click());
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
