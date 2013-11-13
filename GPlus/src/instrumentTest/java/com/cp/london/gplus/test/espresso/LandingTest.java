package com.cp.london.gplus.test.espresso;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import com.cp.london.gplus.Landing;
import com.cp.london.gplus.PlusActivity;
import com.cp.london.gplus.PlusAdapter;
import com.cp.london.gplus.R;

import java.util.ArrayList;
import java.util.List;

import static com.cp.london.gplus.PlusActivity.from;
import static com.cp.london.gplus.test.matchers.DisabledViewMatcher.isDisabled;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class LandingTest extends ActivityInstrumentationTestCase2<Landing> {

//    public static final int EMPTY_LIST = 0;
//    public static final int COUNT_OF_FIVE = 5;

    public LandingTest() {
        super(Landing.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void test_LandingActivity_is_not_null() {
        assertNotNull(getActivity());
    }

    public void test_should_display_empty_view_when_no_data() {
        onView(withId(android.R.id.empty)).check(matches(isDisplayed()));
        onView(withId(android.R.id.list)).check(matches(withEffectiveVisibility(Visibility.GONE)));
    }

    @UiThreadTest
    public void ignore_because_it_doesnt_finish___test_should_have_some_views_when_mocked() throws InterruptedException {
        PlusAdapter adapter = new PlusAdapter(getActivity(), mockedActivities());

        getActivity().getListFragment().setListAdapter(adapter); // does not work!!?!

        onData(is(instanceOf(PlusActivity.class))).atPosition(0).check(matches(isDisplayed()));
    }

    private List<PlusActivity> mockedActivities() {
        List<PlusActivity> list = new ArrayList<PlusActivity>();
        list.add(from("1", "title_1", "content_1", ""));
        list.add(from("2", "title_2", "content_2", ""));
        list.add(from("3", "title_3", "content_3", ""));
        list.add(from("4", "title_4", "content_4", ""));
        list.add(from("5", "title_5", "content_5", ""));
        return list;
    }

    /**
     * Preconditions:<br />
     * - Needs a Google account on the device.<br />
     * - Permissions for the app need to be already accepted.
     * <p/>
     * Ignored because it does not work.
     */
    public void ignore_need_to_figure_out_how_to_make_it_work___test_should_login_user_successfully() {
//        onView(withId(R.id.login)).perform(click());
//
//        onData(is(instanceOf(String.class))).atPosition(0).perform(click()); // DOES NOT WORK, because the dialog is not part of our app (different process??)
//        onView(withText(android.R.string.ok)).perform(click()); // DOES NOT WORK, because the dialog is not part of our app (different process??)

        givenSomeGoogleAccount_tellActivityToLogin_becauseUsingNormalFlowWouldntWork();

        onData(is(instanceOf(PlusActivity.class))).atPosition(0).check(matches(isDisplayed())); // DOES NOT WORK. Is it because it thinks it doesn't need to wait?
        onView(withId(R.id.login)).check(matches(isDisplayed())).check(matches(isDisabled()));
        onView(withId(R.id.logout)).check(matches(isDisplayed())).check(matches(isEnabled()));
    }

    private void givenSomeGoogleAccount_tellActivityToLogin_becauseUsingNormalFlowWouldntWork() {
        Account[] accounts = AccountManager.get(getActivity()).getAccountsByType("com.google");
        getActivity().onActivityResult(Landing.CHOOSE_ACCOUNT_RESULT, Activity.RESULT_OK, new Intent().putExtra(AccountManager.KEY_ACCOUNT_NAME, accounts[0]));
    }
}
