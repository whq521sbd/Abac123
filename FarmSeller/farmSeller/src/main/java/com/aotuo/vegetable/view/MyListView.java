package com.aotuo.vegetable.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class MyListView extends ListView {
	/** 头部view高度. */
	private int mHeaderHeight;
	/** 头部view显示高度. */
	private int mHeaderVisibleHeight;
	private int step = 0;
	/** 头部view. */
	private View mHeader;

	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
//		init(context, attrs);
	}

//	private void init(Context context, AttributeSet attrs) {
//		// set scroll mode
//		setOverScrollMode(OVER_SCROLL_NEVER);
//
//		if (null != attrs) {
//			TypedArray ta = context.obtainStyledAttributes(attrs,
//					R.styleable.PullScrollView);
//
//			if (ta != null) {
//				mHeaderHeight = (int) ta.getDimension(
//						R.styleable.PullScrollView_headerHeight, -1);
//				mHeaderVisibleHeight = (int) ta.getDimension(
//						R.styleable.PullScrollView_headerVisibleHeight, -1);
//				ta.recycle();
//				step = mHeaderHeight / 2;
//			}
//		}
//	}
//
//	private PointF mStartPoint = new PointF();
//
//	private enum State {
//		/** 正常 */
//		NORMAL,
//		/** 顶部 */
//		UP,
//		/** 底部 */
//		DOWN
//	}
//
//	/** 状态. */
//	private State mState = State.NORMAL;
//	static float keep = 0f;
//	private boolean isHidHeader = false;
//
//	@Override
//	public boolean onTouchEvent(MotionEvent ev) {
//		// TODO Auto-generated method stub
//		int action = ev.getAction();
//		switch (action) {
//		case MotionEvent.ACTION_DOWN:
//			Log.i("AAAAAAA", "ACTION_DOWN");
//			mStartPoint.set(ev.getX(), ev.getY());
//
//			break;
//		case MotionEvent.ACTION_MOVE:
//			float deltaY = Math.abs(ev.getY() - mStartPoint.y);
//			if (deltaY > 10 && deltaY > Math.abs(ev.getX() - mStartPoint.x)) {
//				doActionMove(ev);
//			}
//			Log.i("AAAAAAA", "ACTION_MOVE");
//			break;
//		case MotionEvent.ACTION_UP:
//			if (getScrollY() == 0) {
//				mHeader.setVisibility(View.VISIBLE);
//			}
//			mState = State.NORMAL;
//			Log.i("AAAAAAA", "ACTION_UP");
//			break;
//		default:
//			break;
//
//		}
//
//		return super.onTouchEvent(ev);
//	}
//
//	/**
//	 * 执行移动动画
//	 * 
//	 * @param event
//	 */
//	private void doActionMove(MotionEvent event) {
//
//		float deltaY = event.getY() - mStartPoint.y;
//
//		if (mState == State.UP) { // up
//			if (keep > deltaY)
//				keep = deltaY;
//			else if (deltaY - keep > step / 5)
//				mStartPoint.y = event.getY() - step / 5;
//		} else if (mState == State.DOWN) { // down
//			if (keep < deltaY)
//				keep = deltaY;
//			else if (keep - deltaY > step / 5)
//				mStartPoint.y = event.getY() + step / 5;
//		}
//
//		// 对于首次Touch操作要判断方位：UP OR DOWN
//		if (deltaY < 0 && Math.abs(deltaY) > step) {
//			if (mState != State.UP) { // up
//				mState = State.UP;
//				mStartPoint.y = event.getY();
//				if (!isHidHeader) {
//					AlphaAnimation aa = new AlphaAnimation(1.0f, 0.0f);
//					// 渐变时间
//					aa.setDuration(1000);
//					aa.setFillAfter(true);
//					aa.setAnimationListener(new AnimationListener() {
//
//						@Override
//						public void onAnimationStart(Animation animation) {
//							// TODO Auto-generated method stub
//
//						}
//
//						@Override
//						public void onAnimationRepeat(Animation animation) {
//							// TODO Auto-generated method stub
//
//						}
//
//						@Override
//						public void onAnimationEnd(Animation animation) {
//							// TODO Auto-generated method stub
//							mHeader.setVisibility(View.GONE);
//						}
//					});
//					mHeader.startAnimation(aa);
//					isHidHeader = true;
//				}
//			}
//		} else if (deltaY > 0 && Math.abs(deltaY) > step) {
//			if (mState != State.DOWN) { // down
//				mState = State.DOWN;
//				mStartPoint.y = event.getY();
//				if (isHidHeader) {
//					AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
//					// 渐变时间
//					aa.setDuration(1000);
//					aa.setFillAfter(true);
//					mHeader.setVisibility(View.VISIBLE);
//					mHeader.startAnimation(aa);
//					isHidHeader = false;
//				}
//			}
//		}
//	}
//
//	/**
//	 * 设置Header
//	 * 
//	 * @param view
//	 */
//	public void setHeader(View view) {
//		mHeader = view;
//	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}