package com.summertime.survey.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.summertime.survey.MainActivity;
import com.summertime.survey.R;

import java.io.FileWriter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavActivitiesFragment extends Fragment {

    @BindView(R.id.vacation)
    Spinner vacation;

    @BindView(R.id.movies)
    Spinner movies;

    @BindView(R.id.eating)
    Spinner eating;

    @BindView(R.id.party)
    Spinner party;

    private FileWriter mFileWriter;

    String newline = System.getProperty("line.separator");

    public FavActivitiesFragment() {
        // Required empty public constructor
    }

    private MainActivity mActivity;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fav_activities, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    public void writeToFile() {

        String[] data = {
                movies.getSelectedItem().toString(),
                eating.getSelectedItem().toString(),
                party.getSelectedItem().toString(),
                vacation.getSelectedItem().toString()};

        mActivity.setFavactivities(data);

    }


}
