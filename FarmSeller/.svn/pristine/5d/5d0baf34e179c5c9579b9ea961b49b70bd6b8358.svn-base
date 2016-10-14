package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.RecordEntity;
import com.aotuo.vegetable.util.FinalContent;

import net.tsz.afinal.FinalBitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 牛XX on 2016/10/11.
 */

public class PayResultAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<RecordEntity> list = new ArrayList<RecordEntity>();

    public PayResultAdapter(Context context, List<RecordEntity> list) {
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
            vh = new ViewHolder();
            v = inflater.inflate(R.layout.pay_result_item, null);
            vh.txtDate = (TextView) v.findViewById(R.id.txtDate);
            vh.name = (TextView) v.findViewById(R.id.name);
            vh.price = (TextView) v.findViewById(R.id.price);
            vh.weight = (TextView) v.findViewById(R.id.weight);
            vh.total = (TextView) v.findViewById(R.id.total);
            vh.log = (TextView) v.findViewById(R.id.log);
            vh.booth = (TextView) v.findViewById(R.id.booth);
            vh.address = (TextView) v.findViewById(R.id.address);
            vh.state = (TextView) v.findViewById(R.id.state);
            vh.img = (ImageView) v.findViewById(R.id.img);
            v.setTag(vh);
        } else {
            vh = (ViewHolder) v.getTag();
        }

        RecordEntity re = list.get(position);
        vh.txtDate.setText(re.getTime());
        vh.name.setText(re.getGoodsTitle());
        vh.price.setText(re.getPrice() + "元/公斤");
        vh.weight.setText(re.getWeight() + "公斤");
        vh.total.setText("合计：  " + re.getSum() + "元");
        vh.log.setText("运费：  " + re.getLogMoney() + "元");
        vh.booth.setText(re.getBooth());
        vh.address.setText(re.getMarket());
        vh.state.setText(re.getState());
        FinalBitmap.create(inflater.getContext()).display(vh.img, FinalContent.FinalUrl + re.getPicPath());

        return v;
    }

    private class ViewHolder {
        TextView txtDate;
        TextView name;
        TextView price;
        TextView weight;
        TextView total;
        TextView log;
        TextView booth;
        TextView address;
        TextView state;
        ImageView img;
    }
}
