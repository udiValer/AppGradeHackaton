package com.androidacademyhackaton.appgradehackaton.Presenter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.androidacademyhackaton.appgradehackaton.Database.AppGradeDatabase;
import com.androidacademyhackaton.appgradehackaton.FragmentsCallbacks;
import com.androidacademyhackaton.appgradehackaton.Model.Course;
import com.androidacademyhackaton.appgradehackaton.Model.Curriculum;
import com.androidacademyhackaton.appgradehackaton.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {

    private FragmentsCallbacks mOnParentNeedToChange;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance() {
        CourseFragment fragment = new CourseFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnParentNeedToChange = (FragmentsCallbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentsCallbacks");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course , container , false);

        final Spinner spinnerForCurriculum = view.findViewById(R.id.courseFragmentCurriculumSpinner);
        final Spinner spinnerForCourses = view.findViewById(R.id.courseFragmentCourseSpinner);
        final AppGradeDatabase database = new AppGradeDatabase(getActivity());

        database.getCurriculums(new AppGradeDatabase.OnResultCallback() {
            @Override
            public void callback(Object data) {
                ArrayList<Curriculum> curriculms = (ArrayList<Curriculum>)data;

                ArrayAdapter<Curriculum> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item , curriculms);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinnerForCurriculum.setAdapter(adapter);
            }
        });

        spinnerForCurriculum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, View view, int i, long l) {
                database.getRelevantCourses(new AppGradeDatabase.OnResultCallback() {
                    @Override
                    public void callback(Object data) {
                        List<Course> relevantCourses = (ArrayList<Course>)data;

                        ArrayAdapter<Course> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item , relevantCourses);
                        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        spinnerForCourses.setAdapter(adapter);
                        mOnParentNeedToChange.getEditTextData("CURRICULUM" , ((Curriculum)adapterView.getSelectedItem()).getTitle());
                        mOnParentNeedToChange.getEditTextData("COURSE" , spinnerForCourses.getSelectedItem().toString());
                        mOnParentNeedToChange.onBtnNextNeedToEnable();
                    }
                } , (Curriculum)adapterView.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

}
