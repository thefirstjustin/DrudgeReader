package com.justin.drudgereader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.justin.drudgereader.R;
import com.justin.drudgereader.newssource.Source;
import com.justin.drudgereader.viewholder.SourceViewer;

import java.util.ArrayList;

/**
 * Created by Justin on 3/25/2015.
 */
public class FeedAdapter extends BaseAdapter {

    protected Context mContext;
    ArrayList<String> feed;
    protected ArrayList<Source> sources;

    public FeedAdapter(Context mContext, ArrayList<String> feed) {
        this.mContext = mContext;
        this.feed = feed;
    }

    public FeedAdapter(ArrayList<Source> sources) {

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

        Source source = (Source) getItem(position);

        SourceViewer holder = new SourceViewer();

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.source_view, parent, false);
        }
        return convertView;
    }
}
