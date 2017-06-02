package com.summertime.survey.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.summertime.survey.GridAdapter;
import com.summertime.survey.MainActivity;
import com.summertime.survey.R;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstalledApplicationFragment extends Fragment {


    @BindView(R.id.gridview)
    GridView mGridview;

    GridAdapter adapter;
    String newline = System.getProperty("line.separator");

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
    private FileWriter mFileWriter;

    public InstalledApplicationFragment() {
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
        mView =  inflater.inflate(R.layout.fragment_installed_application, container, false);

        ButterKnife.bind(this,mView);

        List<String> list = Arrays.asList(strings);
        adapter = new GridAdapter(getContext(), list);
        mGridview.setAdapter(adapter);

        return mView;
    }

    public void writeToFile() {

        Map<String , Boolean> map = adapter.getSelectedList();
        if(map == null && map.isEmpty()){
            return;
        }

        String str = "";
        for(Map.Entry<String,Boolean> entry: map.entrySet()){
            if(entry.getValue()){
                str += entry.getKey()+", ";
            }
        }
        mActivity.setInstalledApp(new String[]{str});
    }


    public boolean validate() {
        Map<String , Boolean> map = adapter.getSelectedList();
        if(map == null && map.isEmpty()){
            return false;
        }

        for(Map.Entry<String,Boolean> entry: map.entrySet()){
            if(entry.getValue()){
               return true;
            }
        }

        return false;
    }
}
