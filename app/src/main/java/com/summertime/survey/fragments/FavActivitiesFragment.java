package com.summertime.survey.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.summertime.survey.MainActivity;
import com.summertime.survey.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVWriter;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.summertime.survey.fragments.PersonalInfoFragment.FILE_NAME;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fav_activities, container, false);
        ButterKnife.bind(this, view);

        return view;
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

        String[] data = {getString(R.string.favactivitiesQues) + newline,
                getString(R.string.moviewQues) + newline,
                movies.getSelectedItem().toString() + newline,
                getString(R.string.eatingout) + newline,
                eating.getSelectedItem().toString() + newline,
                getString(R.string.partyQuestion) + newline,
                party.getSelectedItem().toString() + newline,
                getString(R.string.vacation) + newline,
                vacation.getSelectedItem().toString() + newline};

        writer.writeNext(data);

        writer.close();
    }


}
