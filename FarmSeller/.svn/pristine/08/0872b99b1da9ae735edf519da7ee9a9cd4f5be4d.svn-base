package com.aotuo.vegetable.chooseimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.aotuo.vegetable.R;

public class PublishedAdapter extends BaseAdapter {

	private LayoutInflater inflater; // 视图容器
	private int selectedPosition = -1;// 选中的位置
	private boolean shape;

	public boolean isShape() {
		return shape;
	}

	public void setShape(boolean shape) {
		this.shape = shape;
	}

	public PublishedAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return (Bimp.bmp.size());
	}

	public Object getItem(int arg0) {

		return null;
	}

	public long getItemId(int arg0) {

		return 0;
	}

	public void setSelectedPosition(int position) {
		selectedPosition = position;
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		final int coord = position;
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_published_grida, arg2,
					false);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView
					.findViewById(R.id.item_grida_image);
			holder.del_button=(Button) convertView.findViewById(R.id.item_grida_bt);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (position == Bimp.bmp.size()) {
//			holder.image.setImageBitmap(BitmapFactory.decodeResource(
//					convertView.getResources(),
//					R.drawable.icon_addpic_unfocused));
			if (Bimp.bmp.size() == 9) {
				holder.image.setVisibility(View.GONE);
			}
		} else {
			
			holder.image.setImageBitmap(Bimp.bmp.get(position));
			holder.del_button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Bimp.bmp.get(coord).recycle();
					Bimp.bmp.remove(coord);
					Bimp.drr.remove(coord);
					Bimp.max--;
					notifyDataSetChanged();
				}
			});
		}

		return convertView;
	}

	public class ViewHolder {
		public ImageView image;
		public Button del_button;
	}

}
