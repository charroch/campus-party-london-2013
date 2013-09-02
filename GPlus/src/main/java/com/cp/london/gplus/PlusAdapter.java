package com.cp.london.gplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlusAdapter extends BaseAdapter {

    private final Context context;
    private final List<PlusActivity> activities;

    public PlusAdapter(Context context, List<PlusActivity> activities) {
        this.context = context;
        this.activities = activities;
    }

    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public Object getItem(int i) {
        return activities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup root) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_item, null);
            holder = new ViewHolder();
            holder.avatar = (ImageView) view.findViewById(R.id.avatar);
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.content = (TextView) view.findViewById(R.id.content);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        PlusActivity activity = activities.get(i);
        holder.avatar.setImageResource(R.drawable.ic_launcher);
        holder.title.setText(activity.title);
        holder.content.setText(activity.content);
        return view;
    }


    private static class ViewHolder {
        ImageView avatar;
        TextView title, content;
    }
}
