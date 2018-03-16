package com.androidacademyhackaton.appgradehackaton.Model;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class GeoArea {

    public String title;

    public GeoArea(){}

    public GeoArea(String title){
        this.title = title;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if(!(object instanceof GeoArea)){
            return false;
        }
        GeoArea geoArea = (GeoArea) object;
        return title.equals(geoArea.title);
    }

    public String getTitle() {
        return title;
    }

}
