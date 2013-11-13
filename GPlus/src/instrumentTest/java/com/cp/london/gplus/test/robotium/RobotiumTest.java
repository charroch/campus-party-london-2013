package com.cp.london.gplus.test.robotium;

import android.test.ActivityInstrumentationTestCase2;

import com.cp.london.gplus.Landing;
import com.jayway.android.robotium.solo.Solo;

import junit.framework.Assert;

public class RobotiumTest extends ActivityInstrumentationTestCase2<Landing> {

    private Solo solo;

    public RobotiumTest() {
        super(Landing.class);
    }


    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void test_should_show_menu_and_populate() throws Exception {
        solo.sendKey(Solo.MENU);
        Assert.assertTrue(solo.searchText("Populate"));
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
