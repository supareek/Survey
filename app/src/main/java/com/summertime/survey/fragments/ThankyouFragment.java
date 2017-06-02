package com.summertime.survey.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.summertime.survey.MainActivity;
import com.summertime.survey.R;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThankyouFragment extends Fragment {


    private View mView;

    public ThankyouFragment() {
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
        mView = inflater.inflate(R.layout.fragment_thankyou, container, false);

        ButterKnife.bind(this, mView);

        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && mView != null){
            try {
                mActivity.writeToFile();
            } catch (IOException e) {
                Log.e("TAG", e.getMessage(),e);
            }
        }
    }

    @OnClick(R.id.finishSurvey)
    public void finish(){
        Intent intent = new Intent(mActivity.getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
