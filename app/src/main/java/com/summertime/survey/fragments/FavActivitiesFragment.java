package com.summertime.survey.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.summertime.survey.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavActivitiesFragment extends Fragment {


    public FavActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_activities, container, false);
    }

}
