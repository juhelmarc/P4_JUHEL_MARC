package fr.marc.mareu.ui.meeting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static fr.marc.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.marc.mareu.R;
import fr.marc.mareu.utils.DeleteViewAction;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTestMeeting {

    private MeetingActivity mActivity;
    private static final int ITEMS_COUNT = 7;

    @Before
    public void setUp() {
        ActivityScenario<MeetingActivity> activityScenario = ActivityScenario.launch(MeetingActivity.class);
        activityScenario.onActivity(activity -> mActivity = activity);

    }
    @After
    public void tearDown() {
        mActivity= null;
    }

    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        onView( withId(R.id.recyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(5, new DeleteViewAction()));
        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT - 1));

    }

    @Test
    public void createMeeting() {

        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT - 1));

        onView(withId(R.id.floatingActionButton2)).perform(click());

        //Try booked with out edits texts completed
        onView(withId(R.id.book) ).perform(scrollTo(),click());

        //EDIT DATE MEETING
        onView(allOf(withId(R.id.datePicker),
                childAtPosition(childAtPosition(withId(R.id.cardview), 0), 0))).perform(scrollTo(), click());
        onView(allOf(withId(android.R.id.button1), withText("OK"),
                childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3))).perform(scrollTo(), click());

        //TRY TO BOOK AGAIN
        onView(withId(R.id.book) ).perform(scrollTo(),click());

        //EDIT MULTI AUTO COMPLETE
        onView(allOf(withId(R.id.mail_autocomplete),
                childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 0)))
                .perform(scrollTo(), click());

        onView(allOf(withId(R.id.mail_autocomplete),
                childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 0)))
                .perform(scrollTo(), replaceText("alexandre@mareu.com, dorine@mareu.com,"));

        onView(allOf(withId(R.id.mail_autocomplete), withText("alexandre@mareu.com, dorine@mareu.com,"),
                childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 0), isDisplayed()))
                .perform(closeSoftKeyboard());
        //EDIT SUBJECT
        onView(allOf(withId(R.id.subject),
                childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 2)))
                .perform(scrollTo(), replaceText("test"), closeSoftKeyboard());

        onView(allOf(withId(R.id.subject), withText("test"),
                childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 2)))
                .perform(pressImeActionButton());
        //EDIT ROOM
        onView(allOf(withId(R.id.room_spinner),
                childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 4)))
                .perform(scrollTo(), click());

        onData(anything()).inAdapterView(childAtPosition(withClassName(is("android.widget.PopupWindow$PopupBackgroundView")), 0)).atPosition(1)
                .perform(click());
        //PRESS BUTTON BOOK
        ViewInteraction appCompatButton4 = onView(allOf(withId(R.id.book), withText("Book"),
                        childAtPosition(allOf(withId(R.id.constraintlayout), childAtPosition(withId(R.id.scrollview), 0)), 7)));
        appCompatButton4.perform(scrollTo(), click());

        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT ));

    }

    @Test
    public void filterMenuWithDateAndClear() {
        // DATE FILTER
        onView(allOf(withId(R.id.menu_filter), withContentDescription("Item"))).perform(click());

        onView(allOf(withId(R.id.title), withText(R.string.date_filter), childAtPosition(
                childAtPosition(withId(R.id.content), 0), 0), isDisplayed())).perform(click());

        onView(allOf(withId(android.R.id.button1), withText("OK"),
                childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3))).perform(scrollTo(), click());
        onView(withId(R.id.recyclerview)).check(withItemCount(6));

        // CLEAR FILTER
        onView(allOf(withId(R.id.menu_filter), withContentDescription("Item"))).perform(click());

        onView(allOf(withId(R.id.title), withText("Clear filter"),
                childAtPosition(childAtPosition(withId(R.id.content), 0), 0), isDisplayed())).perform(click());
        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT ));

    }
    @Test
    public void filterMenuWithRoomAndClear() {

        // ROOM FILTER
        onView(allOf(withId(R.id.menu_filter), withContentDescription("Item"))).perform(click());

        onView(allOf(withId(R.id.title), withText("Chose meeting room"),
                childAtPosition(childAtPosition(withId(R.id.content), 0), 0), isDisplayed())).perform(click());

        onData(anything()).inAdapterView(allOf(withId(R.id.select_dialog_listview),
                childAtPosition(withId(R.id.contentPanel), 0))).atPosition(0).perform(click());
        onView(withId(R.id.recyclerview)).check(withItemCount(1));

        // CLEAR FILTER
        onView(allOf(withId(R.id.menu_filter), withContentDescription("Item"))).perform(click());

        onView(allOf(withId(R.id.title), withText("Clear filter"),
                childAtPosition(childAtPosition(withId(R.id.content), 0), 0), isDisplayed())).perform(click());
        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT ));

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