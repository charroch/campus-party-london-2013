package com.cp.london.gplus;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.ActivityFeed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyPlusActivitiesFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<PlusActivity>> {

    private static final int LOADER_ID = 312;
    private static final String KEY_TOKEN = "token";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        GetToken act = (GetToken) activity;
        if (act.hasToken()) {
            loadFeed(act.getToken());
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        PlusActivity activity = (PlusActivity) l.getAdapter().getItem(position);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(activity.itemUrl));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public Loader<List<PlusActivity>> onCreateLoader(int i, Bundle bundle) {
        return new GetPlusFeed(getActivity(), bundle.getString(KEY_TOKEN));
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

    public void loadFeed(String token) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TOKEN, token);
        getLoaderManager().initLoader(LOADER_ID, bundle, this);
        getLoaderManager().getLoader(LOADER_ID).forceLoad();
    }

    /**
     * Get feed asynchronously
     */
    public static class GetPlusFeed extends AsyncTaskLoader<List<PlusActivity>> {

        private final String token;

        public GetPlusFeed(Context context, String token) {
            super(context);
            this.token = token;
        }

        @Override
        public List<PlusActivity> loadInBackground() {
            try {
                return getPlusActivities(token);
            } catch (IOException e) {
                Log.e("LOGTAG", "", e);
                return Collections.emptyList();
            }
        }

        public List<PlusActivity> getPlusActivities(String token) throws IOException {
            Plus.Activities.List listActivities = plus(token).activities().list("me", "public");
            listActivities.setMaxResults(50L);
            ActivityFeed activityFeed = listActivities.execute();
            final List<Activity> activities = activityFeed.getItems();
            List<PlusActivity> returned = new ArrayList<PlusActivity>(activities.size());
            for (Activity activity : activities) {
                returned.add(
                        PlusActivity.from("", activity.getActor().getDisplayName(), activity.getTitle(), activity.getUrl()));
            }
            return returned;
        }

        public Plus plus(String token) {
            HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
            GoogleCredential c = new GoogleCredential();
            c.setAccessToken(token);
            Plus plus = new Plus.Builder(httpTransport, new AndroidJsonFactory(), c)
                    .setApplicationName("Google-Campus-Party/0.1").build();
            return plus;
        }
    }
}
