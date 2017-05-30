package com.summertime.survey;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.summertime.survey.fragments.FavActivitiesFragment;
import com.summertime.survey.fragments.InstalledApplicationFragment;
import com.summertime.survey.fragments.OpportunityFragment;
import com.summertime.survey.fragments.PersonalInfoFragment;
import com.summertime.survey.fragments.ThankyouFragment;
import com.summertime.survey.fragments.WelcomeFragment;
import com.summertime.survey.fragments.YesNoInfoFragment;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;


    private MyPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (shouldAskPermissions()) {
            askPermissions();
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame,new WelcomeFragment(),"Main");
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void initPager() {


        getSupportFragmentManager().beginTransaction().
                remove(getSupportFragmentManager().findFragmentById(R.id.frame)).commit();
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setEnabled(false);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Fragment fragment = (Fragment) adapterViewPager.instantiateItem(mViewPager, position);
                switch (position){
                    case 0:
                        boolean b= ((PersonalInfoFragment) fragment).validate();
                        if(!b)
                            mViewPager.setCurrentItem(position);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        Fragment fragment = (Fragment) adapterViewPager.instantiateItem(mViewPager, 0);
                        try {
                            ((PersonalInfoFragment) fragment).writeToCSV();
                        } catch (IOException e) {
                            Log.e("TAG", e.getMessage(), e);
                        }
                        break;
                    case 2:
                        fragment = (Fragment) adapterViewPager.instantiateItem(mViewPager, 1);
                        try {
                            ((OpportunityFragment) fragment).writeToFile();
                        } catch (IOException e) {
                            Log.e("TAG", e.getMessage(), e);
                        }
                        break;
                    case 3:
                        fragment = (Fragment) adapterViewPager.instantiateItem(mViewPager, 2);
                        try {
                            ((InstalledApplicationFragment) fragment).writeToFile();
                        } catch (IOException e) {
                            Log.e("TAG", e.getMessage(), e);
                        }
                        break;
                    case 4:
                        fragment = (Fragment) adapterViewPager.instantiateItem(mViewPager, 3);
                        try {
                            ((FavActivitiesFragment) fragment).writeToFile();
                        } catch (IOException e) {
                            Log.e("TAG", e.getMessage(), e);
                        }
                        break;
                    case 5:
                        fragment = (Fragment) adapterViewPager.instantiateItem(mViewPager, 4);
                        try {
                            ((YesNoInfoFragment) fragment).writeToFile();
                        } catch (IOException e) {
                            Log.e("TAG", e.getMessage(), e);
                        }
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    public SharedPreferences getSHaredPrefs(){

        SharedPreferences prefs = getSharedPreferences(
                getPackageName(), Context.MODE_PRIVATE);
        return prefs;

    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 6;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new PersonalInfoFragment();
                case 1:
                    return new OpportunityFragment();
                case 2:
                    return new InstalledApplicationFragment();
                case 3:
                    return new FavActivitiesFragment();
                case 4:
                    return new YesNoInfoFragment();
                case 5:
                    return  new ThankyouFragment();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }
}
