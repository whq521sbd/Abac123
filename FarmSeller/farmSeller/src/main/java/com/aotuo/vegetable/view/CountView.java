package com.aotuo.vegetable.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CountView extends View{

	private Paint mPaint;
	private Rect mBounds;
	private int mCounts = 0;
	private int totals = 0;
	public CountView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBounds = new Rect();
	}
	
	
	public void setTotals(int totals){
		this.totals = totals;
	}
	
	public void setmCounts(int mCounts){
		this.mCounts = mCounts;
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		mPaint.setAlpha(0x00);
		canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(30);
		String text = mCounts+"/"+totals;
		mPaint.getTextBounds(text, 0, text.length(), mBounds);
		float textWidth = mBounds.width();
		float textHeight = mBounds.height();
		canvas.drawText(text, getWidth()/2-textWidth/2, getHeight()/2+textHeight/3, mPaint);
	}

}
