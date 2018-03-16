package com.androidacademyhackaton.appgradehackaton.Presenter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidacademyhackaton.appgradehackaton.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SemesterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SemesterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SemesterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SemesterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SemesterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SemesterFragment newInstance(String param1, String param2) {
        SemesterFragment fragment = new SemesterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        final TextView txtYear = view.findViewById(R.id.fragmentSemesterYearEditText);
        final TextView txtSemester = view.findViewById(R.id.fragmentSemesterSemesterEditText);

        rightCircleYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightCircleYear.setSelected(true);
                leftCircleYear.setSelected(false);
                txtYear.setText("2018");
            }
        });

        leftCircleYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftCircleYear.setSelected(true);
                rightCircleYear.setSelected(false);
                txtYear.setText("2019");
            }
        });

        rightCircleSemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightCircleSemester.setSelected(true);
                centerCircleSemester.setSelected(false);
                leftCircleSemester.setSelected(false);
                txtSemester.setText("א");
            }
        });

        centerCircleSemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightCircleSemester.setSelected(false);
                centerCircleSemester.setSelected(true);
                leftCircleSemester.setSelected(false);
                txtSemester.setText("ב");
            }
        });

        leftCircleSemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightCircleSemester.setSelected(false);
                centerCircleSemester.setSelected(false);
                leftCircleSemester.setSelected(true);
                txtSemester.setText("ג");
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
