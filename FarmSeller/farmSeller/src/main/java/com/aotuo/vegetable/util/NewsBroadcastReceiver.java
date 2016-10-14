package com.aotuo.vegetable.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NewsBroadcastReceiver extends BroadcastReceiver {
	private final static String TAG = "NewsBroadcastReceiver";
	public final static String NEWS_ACTION = "servers.alla.news";
	
	public NewsBroadcastReceiver() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (NEWS_ACTION.equals(intent.getAction())) {
			Log.i("TAG", "has news");
			if (iNews != null) {
				if(intent != null){
					iNews.update(intent.getStringExtra("msg"));
				} else {
					iNews.update();
				}
			}
		}
	}
	private INews iNews;
	public void setINews(INews in){
		iNews = in;
	}
	public interface INews {
		void update();
		void update(String msg);
	}
}
