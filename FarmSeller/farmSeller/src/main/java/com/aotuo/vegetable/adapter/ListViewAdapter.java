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


public class ListViewAdapter extends BaseAdapter {

    private List<KeyValueData> dataList = new ArrayList<KeyValueData>();
    private Context ctx;
    public ListViewAdapter(Context ctx, List<KeyValueData> dataList) {
        this.ctx = ctx;
        this.dataList = dataList;
    }

    // 必须得到真实的数据源项数，否则不能�?�配数据
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 对于ListView中的数据，显示每�行数据都要调用一次该方法 convertView和ViewHolder同完成缓存机制，以优化性能
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.chat_gridviewitem,null);

            holder = new ViewHolder();
            // 每项的视图是一样的
            holder.chat_textitem = (TextView) view.findViewById(R.id.chat_textitem);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        // 每项的数据是不一样的
//
//        imageLoader = AbImageLoader.getInstance(ctx);
//        imageLoader.display(holder.img,dataList.get(position).getDayPictureUrl());

        holder.chat_textitem.setText(dataList.get(position).getValue());

        return view;
    }

    // 用来实现缓存机制
    static class ViewHolder {

        public TextView chat_textitem;

    }
}
