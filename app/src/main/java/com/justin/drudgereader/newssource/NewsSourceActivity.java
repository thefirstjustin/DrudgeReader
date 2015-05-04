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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.justin.drudgereader.R;
import com.justin.drudgereader.SingleActivity;
import com.justin.drudgereader.article.Article;
import com.justin.drudgereader.utils.GetRSSFeed;

import java.util.ArrayList;
import java.util.List;

public class NewsSourceActivity extends SingleActivity {
    @Override
    public Fragment createFragment() {
        ActionBar bar = getSupportActionBar();
//        bar.setTitle(getIntent().getStringExtra("title"));
        NewsSource ns = (NewsSource) getIntent().getSerializableExtra("site");
        bar.setTitle(ns.getTitle());
        return new NewsSourceFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_source, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

//            List<String> feed = getActivity().getIntent().getStringArrayListExtra("feed");
//            if (feed != null) {
//                Toast.makeText(getActivity(), "You\'ve got it!", Toast.LENGTH_SHORT).show();
//            }
            try {
                new GetRSSFeed(getActivity(), url) {

                    @Override
                    protected void onPostExecute(List<String> rssFeed) {
                        super.onPostExecute(rssFeed);

                        final ArrayList<String> feed = new ArrayList<String>(rssFeed);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_list_item_1,
                                android.R.id.text1,
                                feed);

//                        ListView lv = (ListView) getActivity().findViewById(android.R.id.list);
//                        lv.setAdapter(adapter);
//                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                String url = feed.get(position);
//                                Log.d("Url", url);
//                            }
//                        });

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
                    }
                }.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rootView;
        }
    }
}
