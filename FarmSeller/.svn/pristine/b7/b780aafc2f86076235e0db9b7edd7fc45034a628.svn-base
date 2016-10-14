package com.aotuo.vegetable.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.aotuo.vegetable.R;
/**
 * 
 * @author sgy,带有分割线的gridview
 *
 */
public class DrawLineGridView extends GridView {

	public DrawLineGridView(Context context) {
		super(context);
	}

	public DrawLineGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DrawLineGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		View localView1 = getChildAt(0);
		int column = getWidth() / localView1.getWidth();// 一行的数量
		int childCount = getChildCount();// 总数量
		Paint localPaint;
		localPaint = new Paint();
		localPaint.setStyle(Paint.Style.STROKE);
		localPaint.setColor(getContext().getResources().getColor(R.color.line));// 分割线颜色
		for (int i = 0; i < childCount; i++) {
			View cellView = getChildAt(i);
			if ((i + 1) % column == 0) {//每一行的最后一个
				canvas.drawLine(cellView.getLeft(), cellView.getBottom(),
						cellView.getRight() - 30, cellView.getBottom(),
						localPaint);
			} else if ((i + 1) > (childCount - (childCount % column))) {//最后一个item
				canvas.drawLine(cellView.getRight(), cellView.getTop() + 50,
						cellView.getRight(), cellView.getBottom() - 50,
						localPaint);
			} else {
				canvas.drawLine(cellView.getRight(), cellView.getTop() + 50,
						cellView.getRight(), cellView.getBottom() - 50,
						localPaint);// 画竖线
				if (i % column == 0) {// 第一个
					canvas.drawLine(cellView.getLeft() + 30,
							cellView.getBottom(), cellView.getRight(),
							cellView.getBottom(), localPaint);// 画横线
				} else if (i % column == column - 1) {// 一行最后一个
					canvas.drawLine(cellView.getLeft(), cellView.getBottom(),
							cellView.getRight() - 30, cellView.getBottom(),
							localPaint);// 画横线
				} else {//中间的
					canvas.drawLine(cellView.getLeft(), cellView.getBottom(),
							cellView.getRight(), cellView.getBottom(),
							localPaint);// 画横线
				}

			}
		}
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
