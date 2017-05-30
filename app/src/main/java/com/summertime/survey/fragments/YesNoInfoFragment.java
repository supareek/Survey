package com.summertime.survey.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
public class YesNoInfoFragment extends Fragment {


    private FileWriter mFileWriter;
    String newline = System.getProperty("line.separator");

    @BindView(R.id.radio1)
    RadioGroup radio1;

    @BindView(R.id.radio2)
    RadioGroup radio2;

    @BindView(R.id.radio3)
    RadioGroup radio3;

    @BindView(R.id.radio4)
    RadioGroup radio4;

    public YesNoInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_yes_no_info, container, false);
        ButterKnife.bind(this,view);

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

        int selectId = radio1.getCheckedRadioButtonId();

        String text = ((RadioButton)getActivity().findViewById(selectId)).getText().toString();

        selectId = radio2.getCheckedRadioButtonId();

        String text2 = ((RadioButton)getActivity().findViewById(selectId)).getText().toString();

        selectId = radio3.getCheckedRadioButtonId();

        String text3 = ((RadioButton)getActivity().findViewById(selectId)).getText().toString();

        selectId = radio4.getCheckedRadioButtonId();

        String text4 = ((RadioButton)getActivity().findViewById(selectId)).getText().toString();

        String[] data = {getString(R.string.selfieQuestion) + newline,
                text + newline,
                getString(R.string.travelQuestion) + newline,
                text2 + newline,
                getString(R.string.sportsQuestion) + newline,
                text3 + newline,
                getString(R.string.natureQuestion) + newline,
                text4 + newline
        };

        writer.writeNext(data);


        writer.close();
    }



}
