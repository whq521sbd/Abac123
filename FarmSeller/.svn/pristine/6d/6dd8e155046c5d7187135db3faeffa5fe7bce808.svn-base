package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.ClassifyMessage;

import java.util.List;

public class ClassicListAdapter extends BaseAdapter {
	private List<ClassifyMessage> list;
	private LayoutInflater inflater;

	public ClassicListAdapter(Context context, List<ClassifyMessage> list) {
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
			v = inflater.inflate(R.layout.classic_list_item, null);
			vh.img = (ImageView) v.findViewById(R.id.img);
			vh.txt = (TextView) v.findViewById(R.id.txt);
			v.setTag(vh);
		} else {
			vh = (ViewHolder) v.getTag();
		}
		ClassifyMessage ce = list.get(arg0);
		vh.txt.setText(ce.getGc_name());
		//vh.img.setImageResource(ce.getType_img());
		return v;
	}

	private class ViewHolder {
		ImageView img;
		TextView txt;
	}
}
