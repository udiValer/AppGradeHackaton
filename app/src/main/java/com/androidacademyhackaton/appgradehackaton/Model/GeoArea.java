package com.androidacademyhackaton.appgradehackaton.Model;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class GeoArea {

    String title;

    public GeoArea(){}

    public GeoArea(String title){
        this.title = title;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        return result;
    }

}
