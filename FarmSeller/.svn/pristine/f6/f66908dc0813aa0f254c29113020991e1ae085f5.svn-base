package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aotuo.vegetable.entity.NewsListData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 牛XX on 2016/9/1.
 */
public class NewsAdapter extends BaseAdapter {
    private List<NewsListData> list = new ArrayList<NewsListData>();
    private LayoutInflater inflater;

    public NewsAdapter(Context context, List<NewsListData> classes) {
        this.list = classes;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position < list.size()?list.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position < list.size()? position:0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        v = inflater.inflate(android.R.layout.two_line_list_item, null);
        TextView tv = (TextView) v.findViewById(android.R.id.text1);
        TextView tv2 = (TextView) v.findViewById(android.R.id.text2);
        tv.setText(list.get(position).getTitle());
        tv2.setText(list.get(position).getTime());
        return v;
    }
}
