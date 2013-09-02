package com.cp.london.gplus;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Landing extends ListActivity implements LoaderManager.LoaderCallbacks<List<PlusActivity>> {

    private ListView plusActivities;

    private PlusAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plusActivities = (ListView) findViewById(android.R.id.list);
        adapter = new PlusAdapter(this, Collections.<PlusActivity>emptyList());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Loader<List<PlusActivity>> onCreateLoader(int i, Bundle bundle) {
        return new ActivityLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<PlusActivity>> listLoader, List<PlusActivity> activities) {
        adapter = new PlusAdapter(getApplicationContext(), activities);
        plusActivities.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<PlusActivity>> listLoader) {
        plusActivities.setAdapter(null);
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
