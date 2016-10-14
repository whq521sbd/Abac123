package com.aotuo.vegetable.main.fragment;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.aotuo.vegetable.util.ImageUtil;
import com.aotuo.vegetable.R;

/**
 * 循环滚动切换图片(支持带标题,不带标题传null即可),自带适配器 支持显示本地res图片和网络图片，指定uri的图片
 * OnPagerClickCallback onPagerClickCallback 处理page被点击的回调接口,
 * 被用户手动滑动时，暂停滚动，增强用户友好性
 * 
 * @author dance
 * 
 */
public class RollViewPager extends ViewPager {
	private String TAG = "RollViewPager";
	private Context context;
	private int currentItem;
	private ArrayList<String> uriList;// 图片地址
	private ArrayList<View> dots;// 点的位置不固定，所以需要让调用者传入
	// private TextView title;// 用于显示每个图片的标题
	// private String[] titles;
	private int[] resImageIds;
	private int dot_focus_resId;// 获取焦点的图片id
	private int dot_normal_resId;// 未获取焦点的图片id
	private OnPagerClickCallback onPagerClickCallback;
	private boolean isShowResImage = false;
	MyOnTouchListener myOnTouchListener;
	ViewPagerTask viewPagerTask;
	String from;
	private boolean isManualDownload = false;
	private int delayTime = -1;

	private long start = 0;

	public class MyOnTouchListener implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				start = System.currentTimeMillis();
				handler.removeCallbacksAndMessages(null);
				break;
			case MotionEvent.ACTION_MOVE:
				handler.removeCallbacks(viewPagerTask);
				break;
			case MotionEvent.ACTION_CANCEL:
				startRoll();
				break;
			case MotionEvent.ACTION_UP:
				long duration = System.currentTimeMillis() - start;
				if (duration <= 400) {
					if (onPagerClickCallback != null)
						onPagerClickCallback.onPagerClick(currentItem);
				}
				startRoll();
				break;
			}
			return true;
		}
	}

	public void setManualDownload(boolean b){
		isManualDownload = b;
	}
	
	public class ViewPagerTask implements Runnable {
		@Override
		public void run() {
			currentItem = (currentItem + 1)
					% (isShowResImage ? resImageIds.length : uriList.size());
			handler.removeCallbacksAndMessages(null);
			handler.obtainMessage().sendToTarget();
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			RollViewPager.this.setCurrentItem(currentItem);
			startRoll();
		}
	};

	/**
	 * 循环滚动切换图片，被触摸时，暂停滚动，增强用户友好性 ，支持带标题,设置标题用setTitle，
	 * 支持显示本地res图片和网络图片，指定uri的图片
	 * 
	 * @param context
	 * @param dots
	 *            图片的点的集合，之所以不自动生成，是因为点的位置和大小不确定，所以由调用者传入
	 * @param onPagerClickCallback
	 *            页面点击回调
	 * @param dot_focus_resId
	 *            获取焦点的图片id
	 * @param dot_normal_resId
	 *            未获取焦点的图片id
	 */
	public RollViewPager(Context context,String from, ArrayList<View> dots,
			int dot_focus_resId, int dot_normal_resId,
			OnPagerClickCallback onPagerClickCallback) {
		super(context);
		this.context = context;
		this.from=from;
		this.dots = dots;
		this.dot_focus_resId = dot_focus_resId;
		this.dot_normal_resId = dot_normal_resId;
		this.onPagerClickCallback = onPagerClickCallback;
		viewPagerTask = new ViewPagerTask();
		myOnTouchListener = new MyOnTouchListener();
	}

	/**
	 * 设置网络图片的url集合，也可以是本地图片的uri
	 * 图片uriList集合，可以是网络地址，如：http://www.ssss.cn/3.jpg，也可以是本地的uri,如：
	 * assest://image/3.jpg，uriList和resImageIds只需传入一个
	 * 
	 * @param uriList
	 */
	public void setUriList(ArrayList<String> uriList) {
		isShowResImage = false;
		this.uriList = uriList;
	}

	/**
	 * 设置res图片的id 图片uriList集合，可以是网络地址，如：http://www.ssss.cn/3.jpg，也可以是本地的uri,如：
	 * assest://image/3.jpg，uriList和resImageIds只需传入一个
	 * 
	 * @param resImageIds
	 */
	public void setResImageIds(int[] resImageIds) {
		isShowResImage = true;
		this.resImageIds = resImageIds;
	}

	/**
	 * 标题相关
	 * 
	 * @param title
	 *            用于显示标题的TextView
	 * @param titles
	 *            标题数组
	 */
	// public void setTitle(TextView title, String[] titles) {
	// this.title = title;
	// this.titles = titles;
	// if (title != null && titles != null && titles.length > 0)
	// title.setText(titles[0]);// 默认显示第一张的标题
	// }

	private boolean hasSetAdapter = false;
	private final int SWITCH_DURATION = 3500;

	/**
	 * 开始滚动
	 */
	public void startRoll() {
		if (!hasSetAdapter) {
			hasSetAdapter = true;
			this.setOnPageChangeListener(new MyOnPageChangeListener());
			this.setAdapter(new ViewPagerAdapter());
		} else {
			this.getAdapter().notifyDataSetChanged();
		}
		handler.removeCallbacksAndMessages(null);
		if(delayTime == -1)
			handler.postDelayed(viewPagerTask, SWITCH_DURATION);
		else
			handler.postDelayed(viewPagerTask, delayTime);
	}

	/**
	 * 停止滚动
	 */
	public void stopRoll() {
		hasSetAdapter = false;
		handler.removeCallbacksAndMessages(null);
		this.setOnPageChangeListener(null);
		if (dots != null && dots.size() > 0) {
			for (int i = 0; i < dots.size(); i++) {
				if (i == 0)
					dots.get(0).setBackgroundResource(dot_focus_resId);
				else
					dots.get(i).setBackgroundResource(dot_normal_resId);
			}
		}
		currentItem = 0;
	}

	public void setTimeDelay(int delay){
		delayTime = delay;
	}
	/**
	 * view改变时
	 * 
	 * @author 牛XX
	 * 
	 */
	class MyOnPageChangeListener implements OnPageChangeListener {
		int oldPosition = 0;

		@Override
		public void onPageSelected(int position) {
			currentItem = position;
			// if (title != null)
			// title.setText(titles[position]);
			try {
				position %= dots.size();
				dots.get(oldPosition).setBackgroundResource(
						R.drawable.dot_normal);
				dots.get(position).setBackgroundResource(R.drawable.dot_focus);
				oldPosition = position;
			} catch (Exception e) {
			}
		}

		boolean isAutoPlay = false;

		@Override
		public void onPageScrollStateChanged(int state) {
			switch (state) {
			case 1:// 手势滑动，空闲中
				isAutoPlay = false;
				break;
			case 2:// 界面切换中
				isAutoPlay = true;
				break;
			case 0:// 滑动结束，即切换完毕或者加载完毕
					// 当前为最后一张，此时从右向左滑，则切换到第一张
				if (RollViewPager.this.getCurrentItem() == RollViewPager.this
						.getAdapter().getCount() - 1 && !isAutoPlay) {
					RollViewPager.this.setCurrentItem(0);
				}
				// 当前为第一张，此时从左向右滑，则切换到最后一张
				else if (RollViewPager.this.getCurrentItem() == 0
						&& !isAutoPlay) {
					RollViewPager.this.setCurrentItem(RollViewPager.this
							.getAdapter().getCount() - 1);
				}
				break;
			}

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	/**
	 * 自带设配器,需要回调类来处理page的点击事件
	 * 
	 * @author dance
	 * 
	 */
	class ViewPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return isShowResImage ? resImageIds.length : uriList.size();
		}

		@Override
		public Object instantiateItem(View container, final int position) {
			View view = View.inflate(context, R.layout.viewpager_item, null);
			((ViewPager) container).addView(view);
			view.setOnTouchListener(myOnTouchListener);
			ImageView imageView = (ImageView) view.findViewById(R.id.image);
			if (isShowResImage) {
				imageView.setImageResource(resImageIds[position]);
			} else {
				if(from.equals("1")){
					imageView.setScaleType(ScaleType.FIT_XY);
				}else if(from.equals("2")){
					imageView.setScaleType(ScaleType.CENTER_INSIDE);
				}
				if(isManualDownload){
					if(ImageUtil.isCache(context, uriList.get(position))){
						ImageUtil.displayPicImage(uriList.get(position), imageView);
					} else {
						imageView.setTag(uriList.get(position));
						imageView.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								String uri = (String) v.getTag();
								if(!TextUtils.isEmpty(uri)){
									ImageUtil.displayPicImage(uri, (ImageView)v);
								}
							}
						});
					}
				} else {
					ImageUtil.displayPicImage(uriList.get(position), imageView);
				}
			}
			return view;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			// 将ImageView从ViewPager移除
			((ViewPager) arg0).removeView((View) arg2);
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		handler.removeCallbacksAndMessages(null);
		super.onDetachedFromWindow();
	}

	/**
	 * 处理page点击的回调接口
	 * 
	 * @author dance
	 * 
	 */
	public interface OnPagerClickCallback {
		public abstract void onPagerClick(int position);
	}
}
