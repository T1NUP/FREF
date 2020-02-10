package com.example.fref;

public class song {

    String url,nam;

    song(){

    }

    public song(String name, String url)
    {
        this.nam= name;
        this.url= url;
    }

    public String getName() {
        return nam;
    }

    public void setName(String name) {
        this.nam = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
