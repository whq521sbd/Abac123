package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.MarketNewsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 牛XX on 2016/9/18.
 */

public class MarketNewsAdapter extends BaseAdapter {
    private List<MarketNewsEntity> list = new ArrayList<MarketNewsEntity>();
    private LayoutInflater inflater;

    public MarketNewsAdapter(Context context, List<MarketNewsEntity> list) {
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
        if (v == null) {
            v = inflater.inflate(R.layout.marker_news_item, null);
            vh = new ViewHolder();
            vh.title = (TextView) v.findViewById(R.id.title);
            vh.type = (TextView) v.findViewById(R.id.type);
            vh.time = (TextView) v.findViewById(R.id.time);
            vh.context = (TextView) v.findViewById(R.id.context);
            vh.person = (TextView) v.findViewById(R.id.person);
            v.setTag(vh);
        } else {
            vh = (ViewHolder) v.getTag();
        }
        MarketNewsEntity mne = list.get(position);
        vh.title.setText(mne.getTitle());
        vh.type.setText(mne.getType());
        vh.time.setText("发布时间：" + mne.getAddTime());
        vh.context.setText(mne.getContent());
        vh.person.setText("发布人：" + mne.getAddPerson());
        return v;
    }

    private class ViewHolder {
        TextView title;
        TextView type;
        TextView time;
        TextView context;
        TextView person;
    }
}
