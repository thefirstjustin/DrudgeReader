package com.justin.drudgereader.utils;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.justin.drudgereader.R;

import org.apache.http.HttpConnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Justin on 5/7/2015.
 */
public class GetNewsStories extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        String strUrl = params[0];
        URL url;
        HttpURLConnection conn = null;
        try {
            url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return null;
    }
}
