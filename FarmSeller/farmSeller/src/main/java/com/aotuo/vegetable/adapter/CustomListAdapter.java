package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.CustomEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 牛XX on 2016/9/10.
 */

public class CustomListAdapter extends BaseAdapter {
    private List<CustomEntity> list = new ArrayList<CustomEntity>();
    private LayoutInflater inflater;

    public CustomListAdapter(Context context, List<CustomEntity> list) {

        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        v = inflater.inflate(android.R.layout.simple_list_item_1, null);
        TextView tv = (TextView) v.findViewById(android.R.id.text1);
        tv.setText(list.get(position).getFullName());
        v.setBackgroundColor(inflater.getContext().getResources().getColor(R.color.white));
        return v;
    }
}
