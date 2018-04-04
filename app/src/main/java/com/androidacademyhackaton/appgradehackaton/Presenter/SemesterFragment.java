package com.androidacademyhackaton.appgradehackaton.Presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidacademyhackaton.appgradehackaton.FragmentsCallbacks;
import com.androidacademyhackaton.appgradehackaton.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SemesterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SemesterFragment extends Fragment {

    public TextView txtYear;
    public TextView txtSemester;
    private FragmentsCallbacks mOnParentNeedToChange;
    boolean yearSelected = false;
    boolean semesterSelected = false;

    public SemesterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment SemesterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SemesterFragment newInstance() {
        SemesterFragment fragment = new SemesterFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnParentNeedToChange = (FragmentsCallbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnParentNeedToChange");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_semester , container , false);

        InitializeButtonsActions(view);

        return view;
    }

    private void InitializeButtonsActions(View view) {

        final ImageView rightCircleYear = view.findViewById(R.id.fragmentSemesterRightCircleYear);
        final ImageView leftCircleYear = view.findViewById(R.id.fragmentSemesterLeftCircleYear);

        final ImageView rightCircleSemester = view.findViewById(R.id.fragmentSemesterRightCircleSemester);
        final ImageView centerCircleSemester = view.findViewById(R.id.fragmentSemesterCenterCircleSemester);
        final ImageView leftCircleSemester = view.findViewById(R.id.fragmentSemesterLeftCircleSemester);

        txtYear = view.findViewById(R.id.fragmentSemesterYearEditText);
        txtSemester = view.findViewById(R.id.fragmentSemesterSemesterEditText);

        rightCircleYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightCircleYear.setSelected(true);
                leftCircleYear.setSelected(false);
                txtYear.setText("2018");
                CheckIfToEnableNextOption("Year");
            }
        });

        leftCircleYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftCircleYear.setSelected(true);
                rightCircleYear.setSelected(false);
                txtYear.setText("2019");
                CheckIfToEnableNextOption("Year");
            }
        });

        rightCircleSemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightCircleSemester.setSelected(true);
                centerCircleSemester.setSelected(false);
                leftCircleSemester.setSelected(false);
                txtSemester.setText("א");
                CheckIfToEnableNextOption("Semester");
            }
        });

        centerCircleSemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightCircleSemester.setSelected(false);
                centerCircleSemester.setSelected(true);
                leftCircleSemester.setSelected(false);
                txtSemester.setText("ב");
                CheckIfToEnableNextOption("Semester");
            }
        });

        leftCircleSemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightCircleSemester.setSelected(false);
                centerCircleSemester.setSelected(false);
                leftCircleSemester.setSelected(true);
                txtSemester.setText("ג");
                CheckIfToEnableNextOption("Semester");
            }
        });
    }

    private void CheckIfToEnableNextOption(String section){
        if(section == "Year"){
            yearSelected = true;
        }
        else{
            semesterSelected = true;
        }

        if(semesterSelected && yearSelected){
            mOnParentNeedToChange.getEditTextData("YEAR" , txtYear.getText().toString());
            mOnParentNeedToChange.getEditTextData("SEMESTER" , txtSemester.getText().toString());
            mOnParentNeedToChange.onBtnNextNeedToEnable();
        }
    }

}
