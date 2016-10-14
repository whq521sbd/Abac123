package com.aotuo.vegetable.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
	private OnScrollToBottomListener onScrollToBottom;

	public MyScrollView(Context context) {
		this(context, null);
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX,
			boolean clampedY) {
		super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
		// if (scrollY != 0 && null != onScrollToBottom) {
		// onScrollToBottom.onScrollBottomListener(clampedY);
		// }
		// 判断ScrollView是否滑到底部
		if (getScrollY() + getHeight() >= computeVerticalScrollRange()
				&& null != onScrollToBottom) {
			onScrollToBottom.onScrollBottomListener(clampedY);
		}
	}

	public void setOnScrollToBottomLintener(OnScrollToBottomListener listener) {
		onScrollToBottom = listener;
	}

	public interface OnScrollToBottomListener {
		public void onScrollBottomListener(boolean isBottom);
	}

}
