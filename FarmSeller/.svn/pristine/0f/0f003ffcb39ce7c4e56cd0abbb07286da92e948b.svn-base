package com.aotuo.vegetable.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aotuo.vegetable.entity.CitySubMessage;
import com.aotuo.vegetable.R;

public class SubCityAdapter extends BaseAdapter {
	private List<CitySubMessage> list;
	private LayoutInflater mInflater;
	private int clickTem = 0;
	private Context context;

	public void setSeclection(Context context, int clickTemp) {
		this.context = context;
		clickTem = clickTemp;
	}

	public SubCityAdapter(Context context, List<CitySubMessage> list) {
		this.list = list;
		this.mInflater = LayoutInflater.from(context);
	}

	public void updata(List<CitySubMessage> list){
		this.list=list;
		notifyDataSetChanged();
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
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.filter_car_adapter, null);
			findView(holder, convertView);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		showDoubleNum(holder, position);
		return convertView;
	}

	private void findView(ViewHolder holder, View convertView) {
		holder.txtFilter = (TextView) convertView
				.findViewById(R.id.txt_filter_name);
		convertView.setTag(holder);
	}

	private void showDoubleNum(ViewHolder holder, int position) {
		holder.txtFilter.setText(list.get(position).getName());
		// if (clickTem == position) {
		// holder.txtFilter.setTextColor(context.getResources().getColor(
		// R.color.yellow));
		// } else {
		// holder.txtFilter.setTextColor(context.getResources().getColor(
		// R.color.black));
		// }
	}

	public class ViewHolder {
		private TextView txtFilter;
	}
}
