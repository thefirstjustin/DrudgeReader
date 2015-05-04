package com.justin.drudgereader.article;

import com.justin.drudgereader.newssource.BaseSource;

/**
 * Created by Justin on 5/3/2015.
 */
public class Article extends BaseSource {

    public Article() {}

    public Article(String title, String url) {
        super(title, url);
    }

    // Getters
    public String getTitle() {
        return getStrOne();
    }

    public String getUrl() {
        return getStrTwo();
    }

    // Setters
    public void setTitle(String title) {
        setStrOne(title);
    }

    public void setUrl(String url) {
        setStrTwo(url);
    }
}
