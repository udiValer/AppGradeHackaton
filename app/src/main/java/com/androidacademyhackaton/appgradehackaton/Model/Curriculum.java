package com.androidacademyhackaton.appgradehackaton.Model;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Curriculum {

    public String title;

    public Curriculum(){}

    public Curriculum(String title){
        this.title = title;
    }
}
