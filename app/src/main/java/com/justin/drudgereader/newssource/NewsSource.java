package com.justin.drudgereader.newssource;

import java.io.Serializable;

/**
 * Created by Justin on 5/1/2015.
 */
public class NewsSource implements Serializable {
    private String title;
    private String strUrl;

    public NewsSource(String title, String strUrlFragment, String apiKey) {
        this.title = title;

        strUrl = "http://api.usatoday.com/open/" + strUrlFragment + apiKey + "&encoding=json";
    }

    public String getTitle() {
        return title;
    }

    public String getStrUrl() {
        return strUrl;
    }
}