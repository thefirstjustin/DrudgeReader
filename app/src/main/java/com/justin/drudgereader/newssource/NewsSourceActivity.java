package com.justin.drudgereader.newssource;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;

import com.justin.drudgereader.R;
import com.justin.drudgereader.SingleActivity;
import com.justin.drudgereader.adapter.Headlines;
import com.justin.drudgereader.article.Article;
import com.justin.drudgereader.article.ArticleActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsSourceActivity extends SingleActivity {
    @Override
    public Fragment createFragment() {
        ActionBar bar = getSupportActionBar();
//        bar.setTitle(getIntent().getStringExtra("title"));
        NewsSource ns = (NewsSource) getIntent().getSerializableExtra("site");
        bar.setTitle(ns.getTitle());
        return new NewsSourceFragment();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class NewsSourceFragment extends Fragment {

        public NewsSourceFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_news_source, container, false);

            NewsSource ns = (NewsSource) getActivity().getIntent().getSerializableExtra("site");
            final String url = ns.getStrUrl();
            Log.d("Url", url);

            Networking.getNewsFeed(url, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);

                    try {
                        JSONArray arr = response.getJSONArray("stories");
                        Log.d("Stories", arr.toString());

                        final ArrayList<Article> articles = new ArrayList<Article>();

                        for (int i =0; i < arr.length(); i++) {
                            JSONObject obj = arr.getJSONObject(i);
                            Log.d("Object", obj.toString());

                            Article article = new Article(obj);
                            articles.add(article);

                            String link = obj.getString("link");
                            String title = obj.getString("title");
                            String description = obj.getString("description");
                            Log.d("Obj Stuff", "Link: " + link + ", Title: " + title + ", Description: " + description);
                        }

                        Headlines adapter = new Headlines(getActivity(), articles);
                        ListView lv = (ListView) getActivity().findViewById(android.R.id.list);
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Article article = (Article) articles.get(position);

                                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                                intent.putExtra("url", article.getUrl());
                                startActivity(intent);
                            }
                        });
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

//            WebViewClient client = new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    super.shouldOverrideUrlLoading(view, url);
//
//                    return true;
//                }
//            };
//
//            WebView webView = (WebView) rootView.findViewById(R.id.web_view);
//            webView.loadUrl(url);
//            webView.setWebViewClient(client);

            return rootView;
        }
    }
}
