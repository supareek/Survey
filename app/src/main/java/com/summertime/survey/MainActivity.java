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
import android.widget.Toast;

import com.summertime.survey.fragments.FavActivitiesFragment;
import com.summertime.survey.fragments.InstalledApplicationFragment;
import com.summertime.survey.fragments.OpportunityFragment;
import com.summertime.survey.fragments.PersonalInfoFragment;
import com.summertime.survey.fragments.ThankyouFragment;
import com.summertime.survey.fragments.WelcomeFragment;
import com.summertime.survey.fragments.YesNoInfoFragment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;


    private MyPagerAdapter adapterViewPager;
    private List<String> personalInfo;
    private List<String> favactivities;
    private List<String> opportunityList;
    private List<String> installedApp;
    private List<String> yesNoList;

    private List<String> mList;
    private FileWriter mFileWriter;

    String newline = System.getProperty("line.separator");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (shouldAskPermissions()) {
            askPermissions();
        }

        initList();
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
                        b= ((InstalledApplicationFragment) fragment).validate();
                        if(!b) {
                            Toast.makeText(getApplicationContext(),"Please select one!", Toast.LENGTH_SHORT).show();
                            mViewPager.setCurrentItem(position);
                        }
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
                        ((PersonalInfoFragment) fragment).writeToCSV();
                        break;
                    case 2:
                        fragment = (Fragment) adapterViewPager.instantiateItem(mViewPager, 1);
                        ((OpportunityFragment) fragment).writeToFile();
                        break;
                    case 3:
                        fragment = (Fragment) adapterViewPager.instantiateItem(mViewPager, 2);
                        ((InstalledApplicationFragment) fragment).writeToFile();
                        break;
                    case 4:
                        fragment = (Fragment) adapterViewPager.instantiateItem(mViewPager, 3);
                        ((FavActivitiesFragment) fragment).writeToFile();
                        break;
                    case 5:
                        fragment = (Fragment) adapterViewPager.instantiateItem(mViewPager, 4);
                        ((YesNoInfoFragment) fragment).writeToFile();
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

    public void initList(){
        mList = new ArrayList<>();
    }

    public void addToList(String[] data){
        for(String str : data){
            mList.add(str);
        }
    }

    public void setPersonalInfo(String[] data) {
        this.personalInfo = new ArrayList<>();
        for(String str : data){
            this.personalInfo.add(str);
        }
    }

    public void setFavactivities(String[] data) {
        this.favactivities = new ArrayList<>();
        for(String str : data){
            this.favactivities.add(str);
        }
    }

    public void setOpportunityList(String[] data) {
        this.opportunityList = new ArrayList<>();
        for(String str : data){
            this.opportunityList.add(str);
        }
    }

    public void setInstalledApp(String[] data) {
        this.installedApp = new ArrayList<>();
        for(String str : data){
            this.installedApp.add(str);
        }
    }

    public void setYesNoList(String[] data) {
        this.yesNoList = new ArrayList<>();
        for(String str : data){
            this.yesNoList.add(str);
        }
    }

    public List<String> getList(){
        for(String str : personalInfo){
            mList.add(str);
        }
        for(String str : opportunityList){
            mList.add(str);
        }
        for(String str : installedApp){
            mList.add(str);
        }
        for(String str : favactivities){
            mList.add(str);
        }
        for(String str : yesNoList){
            mList.add(str);
        }
        return mList;
    }

    public void writeToFile() throws IOException {
        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "summertime.csv";
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

        getList();
        String[] data = new String[mList.size()];
        for(int i= 0 ; i < mList.size(); i++){
            data[i] = mList.get(i);
        }
        writer.writeNext(data);
        writer.writeNext(new String[]{newline});

        writer.close();
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
