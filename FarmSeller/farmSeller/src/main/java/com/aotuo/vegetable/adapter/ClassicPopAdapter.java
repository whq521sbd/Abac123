package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.ClassifyMessage;

import java.util.List;

public class ClassicPopAdapter extends BaseAdapter {
	private List<ClassifyMessage> list;
	private LayoutInflater inflater;


	public ClassicPopAdapter(Context context,
			List<ClassifyMessage> topClassicList) {
		// TODO Auto-generated constructor stub
		
		this.list = topClassicList;
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
		if(v == null){
			v = inflater.inflate(R.layout.classic_pop_item, null);
			vh.txt = (TextView) v.findViewById(R.id.txt);
			v.setTag(vh);
		} else {
			vh = (ViewHolder) v.getTag();
		}
		TextView txt = vh.txt;
		txt.setText(list.get(arg0).getGc_name());
		
		return v;
	}
	private class ViewHolder{
		TextView txt;
	}
}
