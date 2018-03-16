package com.androidacademyhackaton.appgradehackaton.Model;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.androidacademyhackaton.appgradehackaton.Model.Student.Gender.UNDEFINED;

@IgnoreExtraProperties
public class Student implements Serializable{

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getGeoAreaIds() {
        return geoAreaIds;
    }

    public void setGeoAreaIds(List<String> geoAreaIds) {
        this.geoAreaIds = geoAreaIds;
    }

    public List<String> getCurriculumIds() {
        return curriculumIds;
    }

    public void setCurriculumIds(List<String> curriculumIds) {
        this.curriculumIds = curriculumIds;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
