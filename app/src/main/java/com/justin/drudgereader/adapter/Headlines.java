package com.justin.drudgereader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.justin.drudgereader.R;
import com.justin.drudgereader.article.Article;
import com.justin.drudgereader.customviews.ArticleView;

import java.util.ArrayList;

/**
 * Created by Justin on 5/13/2015.
 */
public class Headlines extends BaseAdapter {

    private Context mContext;
    private ArrayList<Article> articles;

    public Headlines(Context context, ArrayList<Article> articles) {
        mContext = context;
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(String.valueOf(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Article article = (Article) getItem(position);

        ArticleView holder = new ArticleView();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.article_view, parent, false);

            holder.tvTitle = (TextView) convertView.findViewById(R.id.title);
            holder.tvDescription = (TextView) convertView.findViewById(R.id.description);

            convertView.setTag(holder);
        } else {
            holder = (ArticleView) convertView.getTag();
        }

        holder.tvTitle.setText(article.getTitle());
        holder.tvDescription.setText(article.getDescription());

        return convertView;
    }
}
