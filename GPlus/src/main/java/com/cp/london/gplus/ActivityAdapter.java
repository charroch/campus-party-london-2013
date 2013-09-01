package com.cp.london.gplus;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class ActivityAdapter extends ArrayAdapter<String> {

    public ActivityAdapter(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
    }
}
