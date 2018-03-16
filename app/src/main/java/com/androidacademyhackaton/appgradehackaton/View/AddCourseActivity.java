package com.androidacademyhackaton.appgradehackaton.View;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.androidacademyhackaton.appgradehackaton.Model.Course;
import com.androidacademyhackaton.appgradehackaton.Presenter.CourseFragment;
import com.androidacademyhackaton.appgradehackaton.Presenter.LocationFragment;
import com.androidacademyhackaton.appgradehackaton.Presenter.NonSwipeableViewPager;
import com.androidacademyhackaton.appgradehackaton.Presenter.SemesterFragment;
import com.androidacademyhackaton.appgradehackaton.R;

/**
 * Created by Udi on 3/15/2018.
 */

public class AddCourseActivity extends FragmentActivity implements CourseFragment.OnFragmentInteractionListener, SemesterFragment.OnFragmentInteractionListener, LocationFragment.OnFragmentInteractionListener{

    private ViewPager viewPager;
    private TextView btnNext;
    private TextView btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse_layout);

        btnNext = findViewById(R.id.addCourseNextBtn);
        btnBack = findViewById(R.id.addCourseBackBtn);
        viewPager = (ViewPager) findViewById(R.id.addCourseViewPager);
        final AddCourseViewPagerAdapter adapter = new AddCourseViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(2);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (viewPager.getCurrentItem()) {
                    case 2:
                        btnBack.setEnabled(true);
                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                        break;
                    case 1:
                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                        btnNext.setText("מצא שותף");
                        btnNext.setEnabled(false);
                        break;
                    case 0:
                        //TODO : set new course to student
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (viewPager.getCurrentItem()) {
                    case 2:
                        btnBack.setEnabled(false);
                        break;
                    case 1:
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        break;
                    case 0:
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        break;
                }
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class AddCourseViewPagerAdapter extends FragmentPagerAdapter{

        public AddCourseViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return SemesterFragment.newInstance("SemesterFragment" , "Instance 1");
                case 1:
                    return CourseFragment.newInstance("CourseFragment" , "Instance 2");
                default:
                    return LocationFragment.newInstance("LocationFragment" , "Instance 1");

            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
