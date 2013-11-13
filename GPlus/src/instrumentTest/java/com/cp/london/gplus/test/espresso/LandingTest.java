package com.cp.london.gplus.test.espresso;

import android.test.ActivityInstrumentationTestCase2;

import com.cp.london.gplus.Landing;

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

//
//    public void test_should_have_empty_list() {
//        assertEquals(getActivity().getListFragment().getListView().getCount(), EMPTY_LIST);
//    }
//
//    @UiThreadTest
//    public void test_should_have_some_views_when_mocked() throws InterruptedException {
//        PlusAdapter adapter = new PlusAdapter(getActivity(), mockedActivities());
//        getActivity().getListFragment().setListAdapter(adapter);
//        assertEquals(getActivity().getListFragment().getListView().getCount(), COUNT_OF_FIVE);
//    }
//
//    private List<PlusActivity> mockedActivities() {
//        List<PlusActivity> a = new ArrayList<PlusActivity>(COUNT_OF_FIVE);
//        a.add(PlusActivity.from("1", "title_1", "content_1", ""));
//        a.add(PlusActivity.from("2", "title_2", "content_2", ""));
//        a.add(PlusActivity.from("3", "title_3", "content_3", ""));
//        a.add(PlusActivity.from("4", "title_4", "content_4", ""));
//        a.add(PlusActivity.from("5", "title_5", "content_5", ""));
//        return a;
//    }
}
