package com.androidacademyhackaton.appgradehackaton.Database;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.androidacademyhackaton.appgradehackaton.Utils.Gender;

import java.util.Set;

/**
 * Created by Udi on 3/15/2018.
 */

public final class MySharedPref {

    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;

    private static final String USER_ID = "userId";
    private static final String PREF_NAME = "AppGradePref";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String GENDER = "gender";
    private static final String AGE = "age";
    private static final String ABOUT = "about";
    private static final String PHOTO = "photo";
    private static final String CURRICULUMS = "curriculums";
    private static final String GEO_AREAS = "geoAreas";

    public MySharedPref(Context context) {
        sharedPref = context.getSharedPreferences(PREF_NAME, 0);
        editor = sharedPref.edit();
    }

    public void setUserId(String id) {
        editor.putString(USER_ID, id);
        editor.apply();
    }

    public String getUserId() {
        if (sharedPref.contains(USER_ID)) {
            return sharedPref.getString(USER_ID, "");
        }
        return "";
    }

    public void setFirstName(String firstName) {
        editor.putString(FIRST_NAME, firstName);
        editor.apply();
    }

    public String getFirstName() {
        if (sharedPref.contains(FIRST_NAME)) {
            return sharedPref.getString(FIRST_NAME, "");
        }
        return "";
    }

    public void setLastName(String lastName) {
        editor.putString(LAST_NAME, lastName);
        editor.apply();
    }

    public String getLastName() {
        if (sharedPref.contains(LAST_NAME)) {
            return sharedPref.getString(LAST_NAME, "");
        }
        return "";
    }

    public void setGender(String gender) {
        editor.putString(GENDER, gender);
        editor.apply();
    }

    public String getGender(){
        if(sharedPref.contains(GENDER)){
            return sharedPref.getString(GENDER , "");
        }
        return "";
    }

    public void setAge(int age){
        editor.putInt(AGE , age);
        editor.apply();
    }

    public int getAge(){
        if(sharedPref.contains((AGE))){
            return sharedPref.getInt(AGE , 0);
        }
        return 0;
    }

    public void setAbput(String about){
        editor.putString(ABOUT , about);
        editor.apply();
    }

    public String getAbout(){
        if(sharedPref.contains(ABOUT)){
            return sharedPref.getString(ABOUT , "");
        }
        return "";
    }

    public void setPhoto(String photoUrl){
        editor.putString(PHOTO , photoUrl);
        editor.apply();
    }

    public String getPhoto(){
        if(sharedPref.contains(PHOTO)){
            return sharedPref.getString(PHOTO , "");
        }
        return "";
    }

    public void setCurriculums(String curriculum) {
        String curriculums = getCurriculums();
        if(curriculum.equals("")){
            editor.putString(curriculums , "");
        }
        else{
            editor.putString(curriculums + " , " + curriculum , "");
        }

        editor.apply();
    }

    public String getCurriculums(){
        if(sharedPref.contains(CURRICULUMS)) {
            return sharedPref.getString(CURRICULUMS, "");
        }
        return "";
    }

    public void setGeoAreas(String geaArea){
        String geoAreas = getGeoAreas();
        if(geaArea.equals("")){
            editor.putString(geaArea , "");
        }
        else{
            editor.putString(geoAreas + " , " + geaArea , "");
        }
        editor.apply();
    }

    public String getGeoAreas(){
        if(sharedPref.contains(GEO_AREAS)) {
            return sharedPref.getString(CURRICULUMS, "");
        }
        return "";
    }



}
