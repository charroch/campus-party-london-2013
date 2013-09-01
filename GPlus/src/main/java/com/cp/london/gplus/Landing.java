package com.cp.london.gplus;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Landing extends Activity implements LoaderManager.LoaderCallbacks<List<String>> {

    private ListView plusActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plusActivities = (ListView) findViewById(R.id.listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.landing, menu);
        return true;
    }

    @Override
    public Loader<List<String>> onCreateLoader(int i, Bundle bundle) {
        return new ActivityLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<String>> listLoader, List<String> strings) {
        plusActivities.setAdapter(new ActivityAdapter(
                getApplicationContext(), R.layout.activity_item, strings
        ));
    }

    @Override
    public void onLoaderReset(Loader<List<String>> listLoader) {
        plusActivities.setAdapter(null);
    }

    class ActivityLoader extends AsyncTaskLoader<List<String>> {

        public ActivityLoader(Context context) {
            super(context);
        }

        @Override
        public List<String> loadInBackground() {
            return Collections.singletonList("two");
        }
    }

}
