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
    public String email;
    public Gender gender;
    public int age;
    public List<String> geoAreaIds;
    public List<String> curriculumIds;
    public String about;
    public String photoUrl;

    public Student(){}

    public Student(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = UNDEFINED;
        this.age = 0;
        this.geoAreaIds = new ArrayList<>();
        this.curriculumIds = new ArrayList<>();
        this.about = "";
        this.photoUrl = "";
    }
}
