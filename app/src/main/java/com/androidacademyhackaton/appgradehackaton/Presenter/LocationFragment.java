package com.androidacademyhackaton.appgradehackaton.Presenter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidacademyhackaton.appgradehackaton.Database.AppGradeDatabase;
import com.androidacademyhackaton.appgradehackaton.FragmentsCallbacks;
import com.androidacademyhackaton.appgradehackaton.Model.GeoArea;
import com.androidacademyhackaton.appgradehackaton.R;
import com.androidacademyhackaton.appgradehackaton.View.AddCourseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationFragment extends Fragment {

    private FragmentsCallbacks mOnParentNeedToChange;

    public LocationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment LocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationFragment newInstance() {
        LocationFragment fragment = new LocationFragment();
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

        ArrayList<GeoArea> locations = new ArrayList<GeoArea>();
        View view = inflater.inflate(R.layout.fragment_location , container , false);

        final AutoCompleteTextView location = view.findViewById(R.id.locationEditText);

        AppGradeDatabase database = new AppGradeDatabase(getActivity());
        database.getGeoAreas(new AppGradeDatabase.OnResultCallback() {
            @Override
            public void callback(Object data) {
                ArrayList<GeoArea> locationsFromDb = (ArrayList<GeoArea>)data;
                String[] areas = new String[locationsFromDb.size()];

                for(int i=0 ; i < locationsFromDb.size() ; i++){
                    areas[i] =locationsFromDb.get(i).getTitle();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (getActivity(),android.R.layout.select_dialog_item, areas);
                location.setThreshold(0);
                location.setAdapter(adapter);

            }
        });

        return view;
    }

}
