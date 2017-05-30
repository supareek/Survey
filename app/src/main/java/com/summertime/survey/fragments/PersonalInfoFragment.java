package com.summertime.survey.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.summertime.survey.MainActivity;
import com.summertime.survey.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVWriter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInfoFragment extends Fragment {


    public static final String FILE_NAME = "fileName";

    private View mView;

    @BindView(R.id.name)
    EditText nameEdit;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.mobile)
    EditText mobile;

    @BindView(R.id.gender)
    Spinner gender;

    @BindView(R.id.age)
    Spinner age;

    @BindView(R.id.education)
    Spinner education;

    @BindView(R.id.profession)
    Spinner profession;

    @BindView(R.id.location)
    Spinner location;

    private FileWriter mFileWriter;


    String newline = System.getProperty("line.separator");

    public PersonalInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_personal_info, container, false);

        ButterKnife.bind(this, mView);

        return mView;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public boolean validate(){

        if(TextUtils.isEmpty(nameEdit.getText().toString())){
            nameEdit.setError(getString(R.string.required_field));
            return false;
        }

        if(!isValidEmail(email.getText().toString())){
            email.setError(getString(R.string.required_email));
            return false;
        }

        if(gender.getSelectedItemPosition() == 0){
            ((TextView) gender.getSelectedView()).setError("Field Required");
            return false;
        }

        if(age.getSelectedItemPosition() == 0){
            ((TextView) age.getSelectedView()).setError("Field Required");
            return false;
        }

        if(education.getSelectedItemPosition() == 0){
            ((TextView) education.getSelectedView()).setError("Field Required");
            return false;
        }

        if(profession.getSelectedItemPosition() == 0){
            ((TextView) profession.getSelectedView()).setError("Field Required");
            return false;
        }

        if(location.getSelectedItemPosition() == 0){
            ((TextView) location.getSelectedView()).setError("Field Required");
            return false;
        }

        return true;
    }

    public void writeToCSV() throws IOException {

        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "summertime.csv";
        saveFileName(fileName);
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

        String[] data = {nameEdit.getText().toString(),
                email.getText().toString(),
                mobile.getText().toString(),
                gender.getSelectedItem().toString(),
                age.getSelectedItem().toString(),
                education.getSelectedItem().toString(),
                profession.getSelectedItem().toString(),
                location.getSelectedItem().toString()};

        writer.writeNext(data);

        writer.close();

    }

    private void saveFileName(String file) {
        SharedPreferences settings = ((MainActivity)getActivity()).getSHaredPrefs();
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(FILE_NAME, file);
        editor.commit();
    }


}
