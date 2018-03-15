package com.androidacademyhackaton.appgradehackaton.Model;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String email;
    public String studentId;

    public User() {}

    public User(String email, String studentId) {
        this.email = email;
        this.studentId = studentId;
    }

}

