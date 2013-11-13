package com.cp.london.gplus.test.matchers;

import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class DisabledViewMatcher extends TypeSafeMatcher<View> {

    public static Matcher<View> isDisabled() {
        return new DisabledViewMatcher();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is disabled");
    }

    @Override
    public boolean matchesSafely(View view) {
        return !view.isEnabled();
    }
}
