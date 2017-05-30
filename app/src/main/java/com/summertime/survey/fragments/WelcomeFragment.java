package com.summertime.survey.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.summertime.survey.MainActivity;
import com.summertime.survey.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeFragment extends Fragment {


    @BindView(R.id.point1)
    TextView point;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_welcome, container, false);

        ButterKnife.bind(this,view);


        point.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.flash));

        return view;
    }

    @OnClick(R.id.takeSurvey)
    public void takeSurvey(){
        ((MainActivity)getActivity()).initPager();
    }

}
