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
public class OpportunityFragment extends Fragment {

    @BindView(R.id.layout1)
    RadioGroup radioGroup1;

    @BindView(R.id.layout2)
    RadioGroup radioGroup2;

    private FileWriter mFileWriter;
    String newline = System.getProperty("line.separator");
    private View mView;

    public OpportunityFragment() {
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
        mView =  inflater.inflate(R.layout.fragment_yes_no_question, container, false);

        ButterKnife.bind(this, mView);

        return mView;
    }


    public void writeToFile() {

        int selectId = radioGroup1.getCheckedRadioButtonId();

        String text = null;
        if(selectId != -1 ) {
          text = ((RadioButton) mView.findViewById(selectId)).getText().toString();
        }

        selectId = radioGroup2.getCheckedRadioButtonId();

        String text2 = null;
        if(selectId != -1 ) {
            text2 = ((RadioButton) mView.findViewById(selectId)).getText().toString();
        }

        String[] data = {
                text,
                text2
        };

       mActivity.setOpportunityList(data);
    }

}
