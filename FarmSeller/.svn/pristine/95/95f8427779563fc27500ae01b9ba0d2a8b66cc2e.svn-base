package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.AreaMessage;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends BaseAdapter{
	List<AreaMessage> list=new ArrayList<AreaMessage>();
	LayoutInflater inflater;
	
	public CityAdapter(Context context, List<AreaMessage> list) {
		super();
		this.list = list;
		this.inflater = inflater.from(context);
	}

	@Override
	public int getCount() {
		return list==null?0:list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		ViewHolder holder=new ViewHolder();
		view=inflater.inflate(R.layout.city_provice_item, null);
		holder.cityname=(TextView) view.findViewById(R.id.cityname);
		
		holder.cityname.setText(list.get(position).getGc_name());
		return view;
	}
	public class ViewHolder{
		TextView cityname;
	}

}
