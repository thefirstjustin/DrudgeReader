package com.justin.drudgereader.feed;

import android.content.Intent;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.justin.drudgereader.R;
import com.justin.drudgereader.SingleActivity;
import com.justin.drudgereader.adapter.FeedAdapter;
import com.justin.drudgereader.newssource.NewsSourceActivity;
import com.justin.drudgereader.newssource.NewsSource;
import com.justin.drudgereader.utils.GetRSSFeed;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends SingleActivity {

    @Override
    public android.app.Fragment createFragment() {
        return new FeedFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public static class FeedFragment extends Fragment {

        protected String rssResult = "";
        protected boolean item = false;

        public FeedFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_feed, container, false);

            final ArrayList<NewsSource> sites = new ArrayList<NewsSource>();
            sites.add(new NewsSource("CNN", "http://rss.cnn.com/rss/edition.rss"));
            sites.add(new NewsSource("Drudge Report", "http://www.drudgereportfeed.com/"));
            sites.add(new NewsSource("NBC", "http://feeds.nbcnews.com/feeds/topstories"));
            sites.add(new NewsSource("Google News", "https://news.google.com/?output=rss"));

            ListView lv = (ListView) rootView.findViewById(android.R.id.list);
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, sources);
            FeedAdapter adapter = new FeedAdapter(getActivity(), sites);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                    String url = "";
//                    switch (position) {
//                        case 0:
//                            url = "http://rss.cnn.com/rss/edition.rss";
//                            break;
//                        case 1:
//                            url = "http://www.drudgereportfeed.com/";
//                            break;
//                        case 2:
//                            url = "http://feeds.nbcnews.com/feeds/topstories";
//                            break;
//                        case 3:
//                            url = "https://news.google.com/?output=rss";
//                            break;
//                    }

                    NewsSource ns = sites.get(position);
                    final String url = ns.getStrUrl();
                    GetRSSFeed feed;
                    Toast.makeText(getActivity(), url, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), NewsSourceActivity.class);
                    intent.putExtra("site", ns);
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }
}
