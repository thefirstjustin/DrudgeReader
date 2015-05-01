package com.justin.drudgereader.newssource;

/**
 * Created by Justin on 5/1/2015.
 */
public class Source {
    private String title;
    private String strUrl;

    public Source() {
        title = "Google";
        strUrl = "http://www.google.com";
    }

    public Source(String title, String strUrl) {
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
