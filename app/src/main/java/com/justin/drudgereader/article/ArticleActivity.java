package com.justin.drudgereader.article;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.justin.drudgereader.R;
import com.justin.drudgereader.SingleActivity;

import java.util.ArrayList;

public class ArticleActivity extends SingleActivity {

    private ArticleFragment articleFragment;

    @Override
    public Fragment createFragment() {
        articleFragment = new ArticleFragment();
        return articleFragment;
    }

    @Override
    public void onBackPressed() {
        if (articleFragment.getLinkCounter() != 0) {
            articleFragment.decreaseLinkCounter();
            articleFragment.reloadLastUrl();
        } else if (articleFragment.getLinkCounter() < 1){
            super.onBackPressed();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ArticleFragment extends Fragment {

        protected int linkCounter = 1;
        protected WebView webView;
        protected WebViewClient client;
        protected ArrayList<String> urls;

        public ArticleFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_article, container, false);

            String url = getActivity().getIntent().getStringExtra("url");
            Log.d("Url", url);

            urls = new ArrayList<>();

            client = new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    super.shouldOverrideUrlLoading(view, url);

                    view.loadUrl(url);
                    urls.add(url);
                    linkCounter++;

                    return true;
                }
            };

            webView = (WebView) rootView.findViewById(R.id.web_view);
            webView.loadUrl(url);
            webView.setWebViewClient(client);

            return rootView;
        }

        public int getLinkCounter() {
            return linkCounter;
        }

        public void decreaseLinkCounter() {
            linkCounter--;
        }

        public void reloadLastUrl() {
            Log.d("Urls size", String.valueOf(urls.size()));
            urls.remove(urls.size() - 1);
            Log.d("Urls size", String.valueOf(urls.size()));

            String link = urls.get((urls.size()) - 1);

            client.shouldOverrideUrlLoading(webView, link);
        }
    }
}
