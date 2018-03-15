package com.androidacademyhackaton.appgradehackaton.Model;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Course {

    public enum Semester{
        SEMESTER_1,
        SEMESTER_2,
        SEMESTER_3
    }

    public String title;
    public int year;
    public Semester semester;

    public Course(){}

    public Course(String title, int year, Semester semester){
        this.title = title;
        this.year = year;
        this.semester = semester;
    }

}
