package com.aotuo.vegetable.base;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.util.Log;

import net.tsz.afinal.FinalActivity;

/**
 * TitleActivity
 * @author Zephyr
 *
 */
public class TitleActivity extends FinalActivity{
	private static final String TAG = "TitleActivity";
	private Context mContext;
	
	public ImageView mBack;
	public TextView mTitleText;
    public Button mRightBtn;
	private FrameLayout mContent;
	private TextView mLeftText;
	
	protected Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			handleCallBack(msg);
		}
	};

	public void handleCallBack(Message msg){
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "----->TitleActivity[onCreate] is invoked");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.activity_title);
		mContext = this;
		initView();
	}
	
	private void initView() {
		mContent = (FrameLayout)findViewById(R.id.activity_content);
        mBack = (ImageView) findViewById(R.id.back);
        mTitleText = (TextView) findViewById(R.id.title_text);
        mRightBtn = (Button) findViewById(R.id.rightBtn);
        mLeftText = (TextView)findViewById(R.id.back_text);
        mLeftText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackClick();				
			}
		});
        mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackClick();
			}
		});
        
        mRightBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onRightBtnClick();
			}
		});
	}

	/**
	 * 设置标题栏题目
	 * @param content
	 * @param show
	 */
	public void setTitleText(String content , boolean show){
		if(show){
			mTitleText.setText(content);
		}
	}
	
	public void setTitleText(int contentId , boolean show){
		if(show){
			mTitleText.setText(contentId);
		}
	}
	
	/**
	 * 设置标题栏的颜色
	 */
	public void setTitleColor(int colorId){
		mTitleText.setTextColor(colorId);
	}
	
	/**
	 * 设置是否显示左侧返回键
	 * @param show
	 */
	public void isShowLeftBtn(boolean show){
		if(show){
			mBack.setVisibility(View.VISIBLE);
		}else {
			mBack.setVisibility(View.INVISIBLE);
		}
	}
	
	public void isShowLeftText(boolean show){
		if(show){
			mLeftText.setVisibility(View.VISIBLE);
		}else {
			mLeftText.setVisibility(View.GONE);
		}
	}
	
	/**
	 * 设置右侧按钮
	 * @param content
	 * @param show
	 */
	public void setRightBtn(String content , boolean show){
		if(show){
			mRightBtn.setText(content);
		}else {
			mRightBtn.setVisibility(View.GONE);
		}
	}
	
	public void setRightBtn(int contentId , boolean show){
		if(show){
			mRightBtn.setText(contentId);
		}else {
			mRightBtn.setVisibility(View.GONE);
		}
	}
	
	public void setRightBtn(int contentId, int contentColor ,boolean show) {
		if(show){
			mRightBtn.setText(contentId);
			mRightBtn.setTextColor(contentColor);
		} else {
			mRightBtn.setVisibility(View.GONE);
		}
	}
	
	public void setRightBtnDisplay(int contentId , boolean show){
		if(show){
			mRightBtn.setText("");
			mRightBtn.setBackgroundResource(contentId);
		}else {
			mRightBtn.setVisibility(View.GONE);
		}
		
	}
	
	@Override
	public void setContentView(int layoutResID) {
		mContent.removeAllViews();
		View.inflate(this, layoutResID, mContent);
		onContentChanged();
	}
	
	@Override
	public void setContentView(View view) {
		mContent.removeAllViews();
		mContent.addView(view);
		onContentChanged();
	}
	
	@Override
	public void setContentView(View view, LayoutParams params) {
		mContent.removeAllViews();
		mContent.addView(view, params);
		onContentChanged();
	}
	
	public void onBackClick(){
		
	}
	
	public void onRightBtnClick(){
		
	}
}
