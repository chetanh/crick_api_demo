package com.android.demo.framework.models;

import org.jetbrains.annotations.NotNull;

public class Provider {
    private String source;
    private String pubDate;
    private String url;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NotNull
    @Override
    public String toString() {
        return "Class [source = " + source + ", pubDate = " + pubDate + ", url = " + url + "]";
    }
}