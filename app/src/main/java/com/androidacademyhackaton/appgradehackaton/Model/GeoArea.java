package com.androidacademyhackaton.appgradehackaton.Model;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class GeoArea {

    public String title;

    public GeoArea(){}

    public GeoArea(String title){
        this.title = title;
    }

}
