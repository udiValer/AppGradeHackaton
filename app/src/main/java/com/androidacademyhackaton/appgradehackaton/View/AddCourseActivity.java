package com.androidacademyhackaton.appgradehackaton.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.androidacademyhackaton.appgradehackaton.Database.AppGradeDatabase;
import com.androidacademyhackaton.appgradehackaton.FragmentsCallbacks;
import com.androidacademyhackaton.appgradehackaton.Model.Student;
import com.androidacademyhackaton.appgradehackaton.Presenter.CourseFragment;
import com.androidacademyhackaton.appgradehackaton.Presenter.LocationFragment;
import com.androidacademyhackaton.appgradehackaton.Presenter.SemesterFragment;
import com.androidacademyhackaton.appgradehackaton.R;

/**
 * Created by Udi on 3/15/2018.
 */

public class AddCourseActivity extends FragmentActivity implements FragmentsCallbacks {

    public String year;
    public String semester;
    public String curriculum;
    public String course;
    public String location;
    private ViewPager viewPager;
    private TextView btnNext;
    private TextView btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse_layout);

        btnNext = findViewById(R.id.addCourseNextBtn);
        btnBack = findViewById(R.id.addCourseBackBtn);
        viewPager = findViewById(R.id.addCourseViewPager);
        final AddCourseViewPagerAdapter adapter = new AddCourseViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(2);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (viewPager.getCurrentItem()) {
                    case 2:
                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                        btnBack.setEnabled(true);
                        btnNext.setEnabled(false);
                        break;
                    case 1:
                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                        btnNext.setText("הוסף");
                        btnNext.setEnabled(false);
                        break;
                    case 0:
                        AppGradeDatabase database = new AppGradeDatabase(AddCourseActivity.this);
                        database.addCourseForCurrentUser(year, semester, course, new AppGradeDatabase.OnResultCallback() {
                            @Override
                            public void callback(Object data) {
                                // TODO: Go to dashboard screen
                            }
                        });
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (viewPager.getCurrentItem()) {
                    case 2:
                        break;
                    case 1:
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        btnBack.setEnabled(false);
                        btnNext.setEnabled(true);
                        break;
                    case 0:
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        btnNext.setText("המשך");
                        btnNext.setEnabled(true);
                        break;
                }
            }
        });
    }

    @Override
    public void onBtnNextNeedToEnable() {
        this.btnNext.setEnabled(true);
    }

    @Override
    public void getEditTextData(String editText, String value) {
        switch (editText){
            case "YEAR":
                year = value;
                break;
            case "SEMESTER":
                semester = value;
                break;
            case "CURRICULUM":
                curriculum = value;
                break;
            case "COURSE":
                course = value;
                break;
            case "LOCATION":
                location = value;
                break;

        }
    }

    private class AddCourseViewPagerAdapter extends FragmentPagerAdapter{

        public AddCourseViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return LocationFragment.newInstance();
                case 1:
                    return CourseFragment.newInstance();
                default:
                    return SemesterFragment.newInstance();

            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
