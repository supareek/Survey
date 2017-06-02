package com.summertime.survey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sumit on 5/14/17.
 */

public class GridAdapter extends BaseAdapter{

    private final Context mContext;
    private final List<String> mList;
    private final LayoutInflater mInflater;
    private Map<String, Boolean> mMap;

    public GridAdapter(Context context, List<String> list){
        mContext = context;
        mList = list;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMap = new HashMap<>();
        for(String str : mList){
            mMap.put(str,false);
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = mInflater.inflate(R.layout.grid_view_item, null);
        }


        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);


        checkBox.setText(mList.get(i));
        checkBox.setTag(mList.get(i));

        checkBox.setChecked(mMap.get(mList.get(i)));

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tag = (String) view.getTag();
                mMap.put(tag, !mMap.get(tag));
            }
        });

        return view;
    }

    public Map<String,Boolean> getSelectedList(){
        return mMap;
    }
}