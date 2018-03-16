package com.androidacademyhackaton.appgradehackaton.View;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.androidacademyhackaton.appgradehackaton.Presenter.LocationFragment;
import com.androidacademyhackaton.appgradehackaton.R;

/**
 * Created by Udi on 3/15/2018.
 */

public class AddCourseActivity extends FragmentActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse_layout);

        viewPager = findViewById(R.id.addCourseViewPager);
        viewPager.setAdapter(new AddCourseViewPagerAdapter(getSupportFragmentManager()));

    }

    private class AddCourseViewPagerAdapter extends FragmentPagerAdapter{

        public AddCourseViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return LocationFragment.newInstance("FirstFragment" , "Instance 1");
                case 1:
                    return LocationFragment.newInstance("FirstFragment" , "Instance 1");
                default:
                    return LocationFragment.newInstance("FirstFragment" , "Instance 1");
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
