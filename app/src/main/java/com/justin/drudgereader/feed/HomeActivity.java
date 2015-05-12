package com.justin.drudgereader.feed;

import android.content.Intent;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.justin.drudgereader.R;
import com.justin.drudgereader.SingleActivity;
import com.justin.drudgereader.adapter.FeedAdapter;
import com.justin.drudgereader.newssource.NewsSourceActivity;
import com.justin.drudgereader.newssource.NewsSource;

import java.util.ArrayList;


public class HomeActivity extends SingleActivity {

    @Override
    public android.app.Fragment createFragment() {
        return new FeedFragment();
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
            sites.add(new NewsSource("Articles", getActivity().getString(R.string.articles), getActivity().getString(R.string.key_articles)));
            sites.add(new NewsSource("Breaking News", getString(R.string.breaking_news), getString(R.string.key_breaking_news)));
            sites.add(new NewsSource("Best Selling Books", getString(R.string.best_selling_books), getString(R.string.key_best_selling_books)));
            sites.add(new NewsSource("Book Reviews", getString(R.string.book_reviews), getString(R.string.key_book_reviews)));
            sites.add(new NewsSource("Census", getString(R.string.census), getString(R.string.key_census)));
            sites.add(new NewsSource("Movie Reviews", getString(R.string.movie_reviews), getString(R.string.key_movie_reviews)));
            sites.add(new NewsSource("Music Reviews", getString(R.string.music_reviews), getString(R.string.key_music_reviews)));

            ListView lv = (ListView) rootView.findViewById(android.R.id.list);
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, sources);
            FeedAdapter adapter = new FeedAdapter(getActivity(), sites);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    NewsSource ns = sites.get(position);
                    final String url = ns.getStrUrl();
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
