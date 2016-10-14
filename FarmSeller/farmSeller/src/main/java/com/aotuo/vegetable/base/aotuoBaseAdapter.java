package com.aotuo.vegetable.base;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class aotuoBaseAdapter extends BaseAdapter{
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	protected Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			handleCallBack(msg);
		}

	};

	public abstract void handleCallBack(Message msg);
}
