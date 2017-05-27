package com.summertime.survey.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.summertime.survey.GridAdapter;
import com.summertime.survey.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstalledApplicationFragment extends Fragment {


    @BindView(R.id.gridview)
    GridView mGridview;

    public String[] strings = new String[]{
            "WhatsApp",
            "Snapchat",
            "Facebook",
            "Instagram",
            "Zomato",
            "Swiggy",
            "FoodPanda",
            "DineOut",
            "Twitter",
            "Trivago",
            "TripAdvisor",
            "Airbnb",
            "MakeMyTrip",
            "Goibibo",
            "Agoda",
            "OYORooms",
            "Uber",
            "Ola",
            "BlaBlaCar",
            "ZoomCar"
    };

    private View mView;

    public InstalledApplicationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_installed_application, container, false);

        ButterKnife.bind(this,mView);

        List<String> list = Arrays.asList(strings);
        GridAdapter adapter = new GridAdapter(getContext(), list);
        mGridview.setAdapter(adapter);

        return mView;
    }

}
