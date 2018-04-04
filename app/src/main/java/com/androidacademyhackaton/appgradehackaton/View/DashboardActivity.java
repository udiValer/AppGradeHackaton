package com.androidacademyhackaton.appgradehackaton.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidacademyhackaton.appgradehackaton.Model.Student;
import com.androidacademyhackaton.appgradehackaton.R;

public class DashboardActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_layout);

        Intent splashIntent = getIntent();
        Student student = (Student)splashIntent.getSerializableExtra("Student");
        student.getEmail();

    }
}
