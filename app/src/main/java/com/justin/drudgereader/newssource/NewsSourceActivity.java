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
import android.widget.Toast;

import com.justin.drudgereader.R;
import com.justin.drudgereader.SingleActivity;
import com.justin.drudgereader.utils.GetRSSFeed;

import java.net.MalformedURLException;
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
            View rootView = inflater.inflate(R.layout.fragment_news_source, container, false);

            NewsSource ns = (NewsSource) getActivity().getIntent().getSerializableExtra("site");
            final String url = ns.getStrUrl();

//            List<String> feed = getActivity().getIntent().getStringArrayListExtra("feed");
//            if (feed != null) {
//                Toast.makeText(getActivity(), "You\'ve got it!", Toast.LENGTH_SHORT).show();
//            }
            try {
                new GetRSSFeed(getActivity(), url) {

                    @Override
                    protected void onPostExecute(List<String> rssFeed) {
                        super.onPostExecute(rssFeed);

                        ArrayList<String> feed = new ArrayList<String>(rssFeed);
                    }
                }.execute();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return rootView;
        }
    }
}
