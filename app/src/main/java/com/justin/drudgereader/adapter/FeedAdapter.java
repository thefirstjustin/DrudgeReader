package com.justin.drudgereader.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Justin on 3/25/2015.
 */
public class FeedAdapter extends BaseAdapter {

    ArrayList<String> feed;

    public FeedAdapter(ArrayList<String> feed) {
        this.feed = feed;
    }

    @Override
    public int getCount() {
        return feed.size();
    }

    @Override
    public Object getItem(int position) {
        return feed.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            //
        }
        return convertView;
    }
}
