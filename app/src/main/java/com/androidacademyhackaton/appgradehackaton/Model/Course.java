package com.androidacademyhackaton.appgradehackaton.Model;

public class Course {

    public enum Semester{
        SEMESTER_1,
        SEMESTER_2,
        SEMESTER_3
    }

    String title;
    int year;
    Semester semester;

    public Course(){}

    public Course(String title, int year, Semester semester){
        this.title = title;
        this.year = year;
        this.semester = semester;
    }

}
