package com.cp.london.gplus.test;

import android.test.ActivityInstrumentationTestCase2;

import com.cp.london.gplus.Landing;

public class LandingTest extends ActivityInstrumentationTestCase2<Landing> {

    public LandingTest() {
        super(Landing.class);
    }

    public void test_LandingActivity_is_not_null() {
        assertNotNull(getActivity());
    }
}
