package com.cp.london.gplus;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

public class MyPlusActivitiesFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<PlusActivity>> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public Loader<List<PlusActivity>> onCreateLoader(int i, Bundle bundle) {
        return new ActivityLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<PlusActivity>> listLoader, List<PlusActivity> activities) {
        PlusAdapter adapter = new PlusAdapter(getActivity(), activities);
        setListAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<PlusActivity>> listLoader) {
        setListAdapter(null);
    }

    class ActivityLoader extends AsyncTaskLoader<List<PlusActivity>> {

        public ActivityLoader(Context context) {
            super(context);
        }

        @Override
        public List<PlusActivity> loadInBackground() {
            return Collections.<PlusActivity>emptyList();
        }
    }
}
