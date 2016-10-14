package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.CustomerEntity;

import java.util.ArrayList;
import java.util.List;

public class InterlligenceAdapter extends BaseAdapter {
	private List<CustomerEntity> list = new ArrayList<CustomerEntity>();
	private LayoutInflater inflater;

	public InterlligenceAdapter(Context context, List<CustomerEntity> reList) {
		super();
		// TODO Auto-generated constructor stub
		this.list = reList;
		inflater = LayoutInflater.from(context);
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
			v = inflater.inflate(R.layout.interlligence_list_item, null);
			vh.name = (TextView) v.findViewById(R.id.name);
			vh.num = (TextView) v.findViewById(R.id.num);
			vh.total = (TextView) v.findViewById(R.id.total);

			v.setTag(vh);
		} else {
			vh = (ViewHolder) v.getTag();
		}
		CustomerEntity ce = list.get(arg0);
		vh.name.setText(ce.getCustomName());
		vh.num.setText("交易" + ce.getTimes() + "次");
		vh.total.setText("合计：  " + ce.getSum() + "元");
		return v;
	}

	private class ViewHolder {
		TextView name;
		TextView num;
		TextView total;
	}
}
