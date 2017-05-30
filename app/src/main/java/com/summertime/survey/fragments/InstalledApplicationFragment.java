package com.summertime.survey.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.summertime.survey.GridAdapter;
import com.summertime.survey.MainActivity;
import com.summertime.survey.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.summertime.survey.fragments.PersonalInfoFragment.FILE_NAME;

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

    public void writeToFile() throws IOException {

        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = ((MainActivity) getActivity()).getSHaredPrefs().getString(FILE_NAME, "");

        String filePath = baseDir + File.separator + fileName;
        File f = new File(filePath );
        CSVWriter writer;
        // File exist
        if(f.exists() && !f.isDirectory()){
            mFileWriter = new FileWriter(filePath , true);
            writer = new CSVWriter(mFileWriter);
        }
        else {
            writer = new CSVWriter(new FileWriter(filePath));
        }

        String[] data = {getString(R.string.applicationQues) + newline};

        writer.writeNext(data);

        writer.writeNext(adapter.getSelectedList().toArray(new String[0]));

        writer.close();
    }


}
