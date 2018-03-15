package com.androidacademyhackaton.appgradehackaton.Model;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

import static com.androidacademyhackaton.appgradehackaton.Model.Student.Gender.UNDEFINED;

@IgnoreExtraProperties
public class Student {

    public enum Gender{
        UNDEFINED,
        MAN,
        WOMAN
    }

    public String firstName;
    public String lastName;
    public Gender gender;
    public int age;
    public List<String> locationIds;
    public List<String> curriculumIds;
    public String about;
    public String photoUrl;
    public String userId;

    public Student(){}

    public Student(String firstName, String lastName, String userId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = UNDEFINED;
        this.age = 0;
        this.locationIds = new ArrayList<>();
        this.curriculumIds = new ArrayList<>();
        this.about = "";
        this.photoUrl = "";
        this.userId = userId;
    }
}
