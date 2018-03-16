package com.androidacademyhackaton.appgradehackaton.Model;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Objects;

@IgnoreExtraProperties
public class Course extends Object{

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

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if(!(object instanceof Course)){
            return false;
        }
        Course course = (Course) object;
        return title.equals(course.title);
    }

}
