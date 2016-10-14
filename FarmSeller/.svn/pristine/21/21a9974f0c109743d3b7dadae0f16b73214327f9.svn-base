package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aotuo.vegetable.entity.BoothEntity;
import com.aotuo.vegetable.util.CommonTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 牛XX on 2016/9/24.
 */

public class BoothAdapter extends BaseAdapter {
    private List<BoothEntity> list = new ArrayList<BoothEntity>();
    private LayoutInflater inflater;
    public BoothAdapter(Context context, List<BoothEntity> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
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
        ViewHolder vh = null;
        if(v == null){
            v = inflater.inflate(android.R.layout.simple_list_item_2, null);
            vh = new ViewHolder();
            vh.text1 = (TextView) v.findViewById(android.R.id.text1);
            vh.text2 = (TextView) v.findViewById(android.R.id.text2);
            v.setTag(vh);
        } else {
            vh = (ViewHolder) v.getTag();
        }
        BoothEntity me = list.get(position);
        if(CommonTools.getUserSn().equals(me.getUserNum()))
            vh.text1.setText("摊位：" + me.getBooth() + "(自己的摊位)");
        else
            vh.text1.setText("摊位：" + me.getBooth() );
        vh.text2.setText("摊主：" + me.getFullName());
        return v;
    }
    private class ViewHolder{
        private TextView text1;
        private TextView text2;
    }

}
