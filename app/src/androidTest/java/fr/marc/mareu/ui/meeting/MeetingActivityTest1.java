package fr.marc.mareu.ui.meeting;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
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

import androidx.test.espresso.DataInteraction;
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
public class MeetingActivityTest1 {

    @Rule
    public ActivityTestRule<MeetingActivity> mActivityTestRule = new ActivityTestRule<>(MeetingActivity.class);

    @Test
    public void meetingActivityTest1() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.floatingActionButton2), withContentDescription("fab_booking"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.datePicker), childAtPosition(childAtPosition(withId(R.id.cardview), 0), 0)));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatButton = onView(allOf(withId(android.R.id.button1), withText("OK"),
                childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3)));

        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.end_hour), withText("14:40"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardview),
                                        0),
                                4)));
        appCompatEditText2.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatMultiAutoCompleteTextView = onView(
                allOf(withId(R.id.mail_autocomplete),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardview2),
                                        0),
                                0)));
        appCompatMultiAutoCompleteTextView.perform(scrollTo(), replaceText("a"), closeSoftKeyboard());

        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatTextView.perform(click());

        ViewInteraction appCompatMultiAutoCompleteTextView2 = onView(
                allOf(withId(R.id.mail_autocomplete), withText("alexandre@mareu.com, "),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardview2),
                                        0),
                                0)));
        appCompatMultiAutoCompleteTextView2.perform(scrollTo(), replaceText("alexandre@mareu.com, d"));

        ViewInteraction appCompatMultiAutoCompleteTextView3 = onView(
                allOf(withId(R.id.mail_autocomplete), withText("alexandre@mareu.com, d"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardview2),
                                        0),
                                0),
                        isDisplayed()));
        appCompatMultiAutoCompleteTextView3.perform(closeSoftKeyboard());

        DataInteraction appCompatTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatTextView2.perform(click());

        ViewInteraction appCompatMultiAutoCompleteTextView4 = onView(
                allOf(withId(R.id.mail_autocomplete), withText("alexandre@mareu.com, damien@mareu.com, "),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardview2),
                                        0),
                                0)));
        appCompatMultiAutoCompleteTextView4.perform(scrollTo(), replaceText("alexandre@mareu.com, damien@mareu.com, c"));

        ViewInteraction appCompatMultiAutoCompleteTextView5 = onView(
                allOf(withId(R.id.mail_autocomplete), withText("alexandre@mareu.com, damien@mareu.com, c"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardview2),
                                        0),
                                0),
                        isDisplayed()));
        appCompatMultiAutoCompleteTextView5.perform(closeSoftKeyboard());

        DataInteraction appCompatTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatTextView3.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.subject),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardview2),
                                        0),
                                2)));
        appCompatEditText3.perform(scrollTo(), replaceText("Test"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.subject), withText("Test"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardview2),
                                        0),
                                2)));
        appCompatEditText4.perform(pressImeActionButton());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.book), withText("Book"),
                        childAtPosition(
                                allOf(withId(R.id.constraintlayout),
                                        childAtPosition(
                                                withId(R.id.scrollview),
                                                0)),
                                7)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.room_spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardview2),
                                        0),
                                4)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.book), withText("Book"),
                        childAtPosition(
                                allOf(withId(R.id.constraintlayout),
                                        childAtPosition(
                                                withId(R.id.scrollview),
                                                0)),
                                7)));
        appCompatButton4.perform(scrollTo(), click());
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
