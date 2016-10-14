package com.aotuo.vegetable.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.SortMessage;
import com.custom.nostra13.universalimageloader.core.DisplayImageOptions;
import com.custom.nostra13.universalimageloader.core.ImageLoader;
import com.custom.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class FilterContentAdapter extends BaseAdapter implements SectionIndexer {
	private LayoutInflater mInflater;
	private List<SortMessage> list;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;
	private int flag;
	private int clickTem = -1;
	private Context context;
	private int intFilter;

	public FilterContentAdapter(Context context, List<SortMessage> list,
			int intFilter) {
		this.mInflater = LayoutInflater.from(context);
		this.context = context;
		this.list = list;
		this.intFilter = intFilter;
		initConfig();
	}

	public void setSeclection(int clickTemp) {
		clickTem = clickTemp;
	}
	
	public void changeData(List<SortMessage> list){
		this.list = list;
		notifyDataSetChanged();
	}

	private void initConfig() {
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.defaultimg)
				.showImageOnFail(R.drawable.defaultimg).cacheOnDisc(true)
				.cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565)
				.considerExifParams(true)
				.displayer(new FadeInBitmapDisplayer(300)).build();
	}

	/**
	 * 当ListView数据发生变化时,调用此方法来更新ListView
	 * 
	 * @param list
	 * 
	 */

	public void updateListView(List<SortMessage> list, int flag) {
		this.list = list;
		this.flag = flag;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list==null?0:list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.filter_content_adapter,
					null);
			findView(holder, convertView);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		showDoubleNum(holder, position);
		return convertView;
	}

	private void findView(ViewHolder holder, View convertView) {
		holder.tvTitle = (TextView) convertView.findViewById(R.id.title);
		holder.tvLetter = (TextView) convertView.findViewById(R.id.catalog);
		convertView.setTag(holder);
	}

	private void showDoubleNum(ViewHolder holder, int position) {
		final SortMessage mContent = list.get(position);
		if (flag == 0) {
			int section = getSectionForPosition(position);
			if (position == getPositionForSection(section)) {
				holder.tvLetter.setVisibility(View.VISIBLE);
				holder.tvLetter.setText(mContent.getSortLetters());
			} else {
				holder.tvLetter.setVisibility(View.GONE);
			}
		} else {
			holder.tvLetter.setVisibility(View.GONE);
		}

		holder.tvTitle.setText(list.get(position).getName());
		if (clickTem == position) {
			holder.tvTitle.setTextColor(context.getResources().getColor(
					R.color.yellow));
		} else {
			holder.tvTitle.setTextColor(context.getResources().getColor(
					R.color.black));
		}

	}

	public class ViewHolder {
		private TextView tvTitle;
		private TextView tvLetter;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
		try {
			return list.get(position).getSortLetters().charAt(0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	public int getPositionForSection(int section) {
		try {
			for (int i = 0; i < getCount(); i++) {
				String sortStr = list.get(i).getSortLetters();
				char firstChar = sortStr.toUpperCase().charAt(0);
				if (firstChar == section) {
					return i;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}

	/**
	 * 提取英文的首字母，非英文字母用#代替。
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String sortStr = str.trim().substring(0, 1).toUpperCase();
		// 正则表达式，判断首字母是否是英文字母
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}
