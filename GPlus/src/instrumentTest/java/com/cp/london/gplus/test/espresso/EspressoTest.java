package com.cp.london.gplus.test.espresso;

import android.test.ActivityInstrumentationTestCase2;

import com.cp.london.gplus.Landing;
import com.cp.london.gplus.R;

import static com.cp.london.gplus.test.matchers.DisabledViewMatcher.isDisabled;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.*;

public class EspressoTest extends ActivityInstrumentationTestCase2<Landing> {

    public EspressoTest() {
        super(Landing.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void test_should_show_populate_menu_item() {
        openActionBarOverflowOrOptionsMenu(getActivity());

        onView(withText("Populate")).check(matches(isDisplayed())).check(matches(isEnabled()));
    }

    public void test_should_be_able_to_login() {
        onView(withId(R.id.login)).check(matches(isDisplayed())).check(matches(isEnabled()));
//        onView(withText("Login?")).check(matches(isDisplayed())).check(matches(isEnabled())); // Also works
//        onView(withText(R.string.login)).check(matches(isDisplayed())).check(matches(isEnabled())); // Also works
    }

    public void test_should_not_be_able_to_logout() {
        onView(withId(R.id.logout)).check(matches(isDisplayed())).check(matches(isDisabled()));
    }
}
