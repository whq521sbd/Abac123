package com.aotuo.vegetable.view;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;

public class TitleView {
	private TextView title;
	private View titleGap;
	private Activity activity;
	private RelativeLayout back;
	private Button rightBtn;
	public void initView(Activity act, String strTitle){
		activity = act;
		titleGap = act.findViewById(R.id.titleGap);
		title = (TextView) act.findViewById(R.id.title_text);
		back = (RelativeLayout) act.findViewById(R.id.back);
		title.setText(strTitle);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(leftClick == null)
					activity.finish();
				else {
					leftClick.onLeftClick();
				}
			}
		});
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			titleGap.setVisibility(View.VISIBLE);
		} else {
			titleGap.setVisibility(View.GONE);
		}
	}

	public void initView(Activity act, String strTitle, String click){
		activity = act;
		titleGap = act.findViewById(R.id.barTitle);
		title = (TextView) act.findViewById(R.id.title_text);
		back = (RelativeLayout) act.findViewById(R.id.back);
		rightBtn = (Button) act.findViewById(R.id.rightBtn);
		title.setText(strTitle);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(leftClick == null)
					activity.finish();
				else {
					leftClick.onLeftClick();
				}
			}
		});
		rightBtn.setText(click);
		rightBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(ititleBar != null){
					ititleBar.onRightClick();
				}
			}
		});
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			titleGap.setVisibility(View.VISIBLE);
		} else {
			titleGap.setVisibility(View.GONE);
		}
	}

	public void showRightBtn(){
		rightBtn.setVisibility(View.VISIBLE);
	}

	public void hideRightBtn(){
		rightBtn.setVisibility(View.GONE);
	}

	private ITitleBar ititleBar;
	public void setItitleBar(ITitleBar titleBar){
		ititleBar = titleBar;
	}
	public interface ITitleBar{
		void onRightClick();
	}

	private ITitleBarLeft leftClick;

	public void setLeftClick(ITitleBarLeft leftClick) {
		this.leftClick = leftClick;
	}

	public interface ITitleBarLeft{
		void onLeftClick();
	}
}
