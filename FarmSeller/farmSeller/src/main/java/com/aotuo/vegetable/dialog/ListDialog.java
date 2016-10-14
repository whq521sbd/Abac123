package com.aotuo.vegetable.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aotuo.vegetable.R;

import java.util.ArrayList;
import java.util.List;

public class ListDialog extends Dialog{
	Context context;
	private DialogListener listener;
	private int intParam;
	private String strParam;
	private ListView listView;
	private List<String> list = new ArrayList<String>();

	public String getStrParam() {
		return strParam;
	}

	public void setStrParam(String strParam) {
		this.strParam = strParam;
	}

	public int getIntParam() {

		return intParam;
	}

	public void setIntParam(int intParam) {
		this.intParam = intParam;
	}

	public interface DialogListener {
		public void onClick(int index);
	}

	public ListDialog(Context context, List<String> data, DialogListener listener) {
		super(context);
		this.context = context;
		this.listener = listener;
		this.list.clear();
		this.list.addAll(data);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	public ListDialog(Context context, int theme, List<String> data, DialogListener listener) {
		super(context, theme);
		this.context = context;
		this.listener = listener;
		this.list.clear();
		this.list.addAll(data);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	public List<String> getList() {
		return list;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.list_dialog);
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(new ListDailogAdapter(context));
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(listener != null){
					listener.onClick(position);
				}
			}
		});
	}


	private class ListDailogAdapter extends BaseAdapter{
		private LayoutInflater inflater;
		public ListDailogAdapter(Context context) {
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
			v = inflater.inflate(R.layout.list_dialog_item, null);
			TextView textView = (TextView) v.findViewById(R.id.text1);
			textView.setText(list.get(position));
			return v;
		}
	}
}
