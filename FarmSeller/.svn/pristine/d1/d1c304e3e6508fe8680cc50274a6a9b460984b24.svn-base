package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.InformDataMessage;
import com.aotuo.vegetable.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NewsCenterAdapter extends BaseAdapter {
	private List<InformDataMessage> list = new ArrayList<InformDataMessage>();
	private LayoutInflater inflater;
	/**
	 * CheckBox 是否选择的存储集合,key 是 position , value 是该position是否选中
	 */
	int isflag = 0;
	private Map<Integer, Boolean> selectedMembers = new HashMap<Integer, Boolean>();

	public NewsCenterAdapter(List<InformDataMessage> list, Context context) {
		super();
		this.list = list;
		this.inflater = inflater.from(context);
		// 初始化,默认都没有选中
		configCheckMap(false);
	}

	/**
	 * 首先,默认情况下,所有item都是没有选中的.这里进行初始化
	 */
	public void configCheckMap(boolean bool) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				selectedMembers.put(i, bool);
			}
		}
	}

	public void changeeddata(List<InformDataMessage> clist) {
		this.list = clist;
		if(clist != null){
			for(InformDataMessage idm: clist){
				String body = idm.getMessage_body();
				if(body != null && !"".equals(body)){
					String[] barr = body.split("<a href");
					idm.setMessage_body(barr[0]);
					if(barr.length > 1){
						String[] bbarr = barr[1].split("&");
						for(String sarr: bbarr){
							String[] a = sarr.split("=");
							if(a.length > 1){
								if("order_id".equals(a[0])){
									String[] d = a[1].split("\"");
									idm.setOrder_id(d[0]);
									Log.i("AAAAAAAA", ""+idm.getOrder_id());
									break;
								}
							}
						}
					}
				}
			}
		}
		notifyDataSetChanged();
		configCheckMap(false);
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
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
		ViewHolder holder = new ViewHolder();
		view = inflater.inflate(R.layout.infrom_item, null);
		holder.time = (TextView) view.findViewById(R.id.time);
		holder.title = (TextView) view.findViewById(R.id.title);
		holder.content = (TextView) view.findViewById(R.id.content);
		initdata(holder, position);
		return view;
	}

	private void initdata(ViewHolder holder, final int position) {
		InformDataMessage idm = list.get(position);
		holder.title.setText(idm.getMessage_title());
		holder.content.setText(idm.getMessage_body());
		if(idm.getMessage_time() != null &&!"".equals(idm.getMessage_time()))
			holder.time.setText(getTime(idm.getMessage_time()));
		else
			holder.time.setText("");
	}

	private String getTime(String sec){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String s = sdf.format(new Date(Long.parseLong(sec) * 1000));
		return s;
	}
	
	// 是否显示checkbox
	public void isshowcheckbox(int flag) {
		isflag = flag;
		notifyDataSetChanged();
	}

	static class ViewHolder {
		TextView time;
		TextView title;
		TextView content;
	}

	public Map<Integer, Boolean> getCheckMap() {
		return this.selectedMembers;
	}

	public List<InformDataMessage> getList() {
		return list;
	}

	public void setList(List<InformDataMessage> list) {
		this.list = list;
	}

	// 移除一个item的时候
	public void remove(int position) {
		this.list.remove(position);
	}

	public List<InformDataMessage> getSelectItems() {
		List<InformDataMessage> itemList = new ArrayList<InformDataMessage>();
		for (Iterator<Map.Entry<Integer, Boolean>> it = selectedMembers
				.entrySet().iterator(); it.hasNext();) {
			Map.Entry<Integer, Boolean> entry = it.next();
			if (entry.getValue()) {
				itemList.add(list.get(entry.getKey()));
			}
		}
		return itemList;
	}
}
