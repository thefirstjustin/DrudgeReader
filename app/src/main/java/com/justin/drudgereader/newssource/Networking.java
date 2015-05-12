package com.justin.drudgereader.newssource;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by Justin on 5/12/2015.
 */
public class Networking {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getNewsFeed(String url, AsyncHttpResponseHandler handler) {
        client.get(url, handler);
    }
}
