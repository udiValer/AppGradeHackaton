package com.androidacademyhackaton.appgradehackaton.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.androidacademyhackaton.appgradehackaton.Database.AppGradeDatabase;
import com.androidacademyhackaton.appgradehackaton.Model.MySharedPref;
import com.androidacademyhackaton.appgradehackaton.Model.Student;
import com.androidacademyhackaton.appgradehackaton.R;
import com.google.firebase.database.DataSnapshot;

/**
 * Created by Udi on 3/15/2018.
 */

public class SplashScreenActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen_layout);

        MySharedPref mySharedPref = new MySharedPref((getApplicationContext()));
        if(!(mySharedPref.getEmail().equals(""))){
            AppGradeDatabase database = new AppGradeDatabase(SplashScreenActivity.this);
            database.signIn(mySharedPref.getEmail(), mySharedPref.getPassword() , new AppGradeDatabase.OnResultCallback() {
                @Override
                public void callback(Object data) {
                    navigateToNextScreen(data);
                }
            });
        }
        else {
            startActivity(new Intent(SplashScreenActivity.this , SignUpActivity.class));
        }
    }

    private void navigateToNextScreen(Object data) {
        Student student = (Student)data;
        //Intent intent = new Intent(SplashScreenActivity.this , DashboardActivity.class);
        Intent intent = new Intent(SplashScreenActivity.this , AddCourseActivity.class);
        intent.putExtra("Student" , student);
        startActivity(intent);
    }

}
