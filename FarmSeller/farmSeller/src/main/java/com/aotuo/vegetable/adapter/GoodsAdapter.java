package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.GoodsA;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.FinalContent;

import net.tsz.afinal.FinalBitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 牛XX on 2016/8/30.
 */
public class GoodsAdapter extends BaseAdapter {
    private List<GoodsA> list = new ArrayList<GoodsA>();
    private LayoutInflater inflater;

    public GoodsAdapter(Context context, List<GoodsA> list) {
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
        ViewHolder vh = new ViewHolder();
        if (v == null) {
            v = inflater.inflate(R.layout.goods_list_item, null);
            vh.img = (ImageView) v.findViewById(R.id.img);
            vh.name = (TextView) v.findViewById(R.id.name);
            vh.weight = (TextView) v.findViewById(R.id.weight);
            vh.price = (TextView) v.findViewById(R.id.price);
            vh.desp = (TextView) v.findViewById(R.id.desp);
            vh.area = (TextView) v.findViewById(R.id.area);
            vh.zt = (TextView) v.findViewById(R.id.zt);
            v.setTag(vh);
        } else {
            vh = (ViewHolder) v.getTag();
        }
        GoodsA ga = list.get(position);
        FinalBitmap.create(inflater.getContext()).display(vh.img, FinalContent.FinalUrl + ga.getPic().split(",")[0]);
        vh.name.setText(ga.getGoodsName());
        vh.weight.setText(ga.getQuantity());
        vh.price.setText(ga.getPrice());
        vh.desp.setText(ga.getIntroduction());
        vh.area.setText(CommonTools.getAddress(inflater.getContext(), ga.getAreaNum()));
        if(ga.getZT() == 1){
            vh.zt.setText("已上架");
            vh.zt.setTextColor(inflater.getContext().getResources().getColor(R.color.cff9900));
        } else {
            vh.zt.setText("已下架");
            vh.zt.setTextColor(inflater.getContext().getResources().getColor(R.color.c333333));
        }
        return v;
    }

    private class ViewHolder {
        ImageView img;
        TextView zt;
        TextView name;
        TextView weight;
        TextView price;
        TextView area;
        TextView desp;
    }
}
