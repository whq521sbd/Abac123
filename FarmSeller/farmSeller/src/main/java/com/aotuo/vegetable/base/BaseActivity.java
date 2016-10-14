package com.aotuo.vegetable.base;

import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.aotuo.vegetable.util.ReLoginReceiver;
import com.aotuo.vegetable.util.SystemStatusManager;

import net.tsz.afinal.FinalActivity;

public abstract class BaseActivity extends FinalActivity {
	public FarmApp app;
	private ReLoginReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTranslucentStatus();
		app = (FarmApp) getApplication();
		app.addActivity(this);
		initReceiver();
	}

	public void back(View v){
		finish();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(receiver != null){
			unregisterReceiver(receiver);
			receiver = null;
		}
		if(app != null){
			app.finishActivity(this);
		}
	}

	private void initReceiver(){
		if(receiver == null){
			IntentFilter intent = new IntentFilter();
			intent.addAction(ReLoginReceiver.RE_LOGIN);
			receiver = new ReLoginReceiver();
			registerReceiver(receiver, intent);
		}
		receiver.setActivity(this);
	}

	/**
	 * 设置状态栏背景状态
	 */
	private void setTranslucentStatus()
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		{
			Window win = getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			winParams.flags |= bits;
			win.setAttributes(winParams);
		}
		SystemStatusManager tintManager = new SystemStatusManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(0);//状态栏无背景
	}
}
