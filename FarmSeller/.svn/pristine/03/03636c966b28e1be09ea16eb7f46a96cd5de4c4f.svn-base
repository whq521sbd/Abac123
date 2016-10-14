package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.KeyValueData;

import java.util.ArrayList;
import java.util.List;

public class SelDateListAdapter extends BaseAdapter {
    private List<KeyValueData> list = new ArrayList<KeyValueData>();
    private LayoutInflater inflater;

    public SelDateListAdapter(Context context, List<KeyValueData> list) {
        super();
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int arg0, View v, ViewGroup arg2) {
        // TODO Auto-generated method stub
        ViewHolder vh = new ViewHolder();
        if (v == null) {
            v = inflater.inflate(R.layout.seldate_list_item, null);
            vh.txt = (TextView) v.findViewById(R.id.txt);
            v.setTag(vh);
        } else {
            vh = (ViewHolder) v.getTag();
        }
        vh.txt.setText(list.get(arg0).getKey());
        return v;
    }

    private class ViewHolder {
        TextView txt;
    }
}
