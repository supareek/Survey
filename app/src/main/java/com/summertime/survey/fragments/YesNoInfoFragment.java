package com.summertime.survey.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.summertime.survey.MainActivity;
import com.summertime.survey.R;

import java.io.FileWriter;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private View mView;

    public YesNoInfoFragment() {
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
        mView =  inflater.inflate(R.layout.fragment_yes_no_info, container, false);
        ButterKnife.bind(this,mView);

        return mView;
    }

    public void writeToFile() {

        int selectId = radio1.getCheckedRadioButtonId();

        String text = null;

        if(selectId != -1)
            text = ((RadioButton)mView.findViewById(selectId)).getText().toString();

        selectId = radio2.getCheckedRadioButtonId();

        String text2 = null;
        if(selectId != -1)
            text2 = ((RadioButton)mView.findViewById(selectId)).getText().toString();

        selectId = radio3.getCheckedRadioButtonId();

        String text3 = null;
        if(selectId != -1)
            text3 = ((RadioButton)mView.findViewById(selectId)).getText().toString();

        selectId = radio4.getCheckedRadioButtonId();

        String text4 = null;
        if(selectId != -1)
            text4 = ((RadioButton)mView.findViewById(selectId)).getText().toString();

        String[] data = {
                text,
                text2,
                text3,
                text4
        };

        mActivity.setYesNoList(data);
    }



}
