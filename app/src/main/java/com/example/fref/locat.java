package com.example.fref;

import android.net.Uri;

import java.net.URL;

public class locat {

    Double lat,lon;
    String url;
    locat()
    {

    }

    public locat(Double lat,Double lon,String url) {
        this.lat = lat;
        this.lon= lon;
        this.url= url;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon(){
        return lon;
    }

    public void setLon(Double lon){
        this.lon= lon;
    }

    public String getUrl() {
        return url;
    }
}
