package com.justin.drudgereader.newssource;

import android.app.Fragment;
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

import com.justin.drudgereader.R;
import com.justin.drudgereader.SingleActivity;

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

            WebViewClient client = new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    super.shouldOverrideUrlLoading(view, url);

                    return true;
                }
            };

            WebView webView = (WebView) rootView.findViewById(R.id.web_view);
            webView.loadUrl(url);
            webView.setWebViewClient(client);

            return rootView;
        }
    }
}
