package com.justin.drudgereader.article;

import com.justin.drudgereader.newssource.BaseSource;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Justin on 5/3/2015.
 */
public class Article extends BaseSource {

    private String description;

    public Article() {}

    public Article(String title, String url) {
        super(title, url);
    }

    public Article(JSONObject object) throws JSONException {
        super(object.getString("title"), object.getString("link"));
        description = object.getString("description");
    }

    // Getters
    public String getTitle() {
        return getStrOne();
    }

    public String getUrl() {
        return getStrTwo();
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setTitle(String title) {
        setStrOne(title);
    }

    public void setUrl(String url) {
        setStrTwo(url);
    }
}
