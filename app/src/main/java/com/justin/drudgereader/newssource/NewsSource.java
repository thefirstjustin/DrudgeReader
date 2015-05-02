package com.justin.drudgereader.newssource;

import java.io.Serializable;

/**
 * Created by Justin on 5/1/2015.
 */
public class NewsSource implements Serializable {
    private String title;
    private String strUrl;

    public NewsSource() {
        title = "Google";
        strUrl = "http://www.google.com";
    }

    public NewsSource(String title, String strUrl) {
        this.title = title;
        this.strUrl = strUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getStrUrl() {
        return strUrl;
    }
}
