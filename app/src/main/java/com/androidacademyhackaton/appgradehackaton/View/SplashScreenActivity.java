package com.androidacademyhackaton.appgradehackaton.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.androidacademyhackaton.appgradehackaton.Model.MySharedPref;
import com.androidacademyhackaton.appgradehackaton.R;

/**
 * Created by Udi on 3/15/2018.
 */

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen_layout);

        MySharedPref mySharedPref = new MySharedPref((getApplicationContext()));
        if(!(mySharedPref.getUserId().equals(""))){
            startActivity(new Intent(SplashScreenActivity.this , SignInActivity.class));
        }
        else {
            startActivity(new Intent(SplashScreenActivity.this , SignInActivity.class));
        }
    }
}
