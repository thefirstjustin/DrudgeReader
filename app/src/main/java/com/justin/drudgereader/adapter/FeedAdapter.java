package com.justin.drudgereader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.justin.drudgereader.R;
import com.justin.drudgereader.newssource.NewsSource;
import com.justin.drudgereader.viewholder.SourceViewer;

import java.util.ArrayList;

/**
 * Created by Justin on 3/25/2015.
 */
public class FeedAdapter extends BaseAdapter {

    protected Context mContext;
    ArrayList<String> feed;
    protected ArrayList<NewsSource> sources;

//    public FeedAdapter(Context mContext, ArrayList<String> feed) {
//        this.mContext = mContext;
//        this.feed = feed;
//    }

    public FeedAdapter(Context mContext, ArrayList<NewsSource> sources) {
        this.mContext = mContext;
        this.sources = sources;
    }

    @Override
    public int getCount() {
        return sources.size();
    }

    @Override
    public Object getItem(int position) {
        return sources.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NewsSource source = (NewsSource) getItem(position);

        SourceViewer holder = new SourceViewer();

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.source_view, parent, false);

            holder.tvTitle = (TextView) convertView.findViewById(R.id.title);
//            holder.tvSubtitle = (TextView) convertView.findViewById(R.id.subtitle);

            convertView.setTag(holder);
        } else {
            holder = (SourceViewer) convertView.getTag();
        }

        holder.tvTitle.setText(source.getTitle());
//        holder.tvSubtitle.setText(source.getStrUrl());
        return convertView;
    }
}
