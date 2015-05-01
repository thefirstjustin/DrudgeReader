package com.justin.drudgereader.feed;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.justin.drudgereader.R;
import com.justin.drudgereader.SingleActivity;
import com.justin.drudgereader.adapter.FeedAdapter;
import com.justin.drudgereader.newssource.NewsSourceActivity;
import com.justin.drudgereader.utils.GetRSSFeed;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


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

            final ArrayList<String> sources = new ArrayList<>();
            sources.add("CNN");
            sources.add("Drudge Report");
            sources.add("NBC");
            sources.add("Google News");

            ListView lv = (ListView) rootView.findViewById(android.R.id.list);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, sources);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    String url = "";
                    switch (position) {
                        case 0:
                            url = "http://rss.cnn.com/rss/edition.rss";
                            break;
                        case 1:
                            url = "http://www.drudgereportfeed.com/";
                            break;
                        case 2:
                            url = "http://feeds.nbcnews.com/feeds/topstories";
                            break;
                        case 3:
                            url = "https://news.google.com/?output=rss";
                            break;
                    }
                    GetRSSFeed feed;
                    Toast.makeText(getActivity(), url, Toast.LENGTH_SHORT).show();
                    try {
                        feed = (GetRSSFeed) new GetRSSFeed(getActivity(), url) {

                            @Override
                            protected void onPostExecute(List<String> rssFeed) {
                                super.onPostExecute(rssFeed);

                                if (rssFeed != null) {
                                    Intent intent = new Intent(getActivity(), NewsSourceActivity.class);
                                    ArrayList<String> feed = new ArrayList<String>(rssFeed);
                                    intent.putExtra("title", sources.get(position));
                                    intent.putExtra("feed", feed);
                                    startActivity(intent);
                                }
                            }
                        }.execute();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            });

//            TextView drudgeChoice = (TextView) rootView.findViewById(R.id.drudge_choice);
//            drudgeChoice.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Toast.makeText(getActivity(), "This works", Toast.LENGTH_SHORT).show();
//                    GetRSSFeed feed;
//                    try {
//                        feed = (GetRSSFeed) new GetRSSFeed(getActivity(), "http://www.drudgereportfeed.com/") {
//
//                            @Override
//                            protected void onPostExecute(List<String> rssFeed) {
//                                super.onPostExecute(rssFeed);
//
//                                if (rssFeed != null) {
//                                    Intent intent = new Intent(getActivity(), NewsSourceActivity.class);
//                                    ArrayList<String> feed = new ArrayList<String>(rssFeed);
//                                    intent.putExtra("feed", feed);
//                                    startActivity(intent);
//                                }
//                            }
//                        }.execute();
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
            return rootView;
        }
    }
}
