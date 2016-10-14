package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.UriHttpEntity;
import com.aotuo.vegetable.util.FinalContent;

import net.tsz.afinal.FinalBitmap;

import java.util.List;


public class GridImgAdapter extends BaseAdapter {
	private List<UriHttpEntity> imgs;
	private LayoutInflater inflater;
	private int width;
	private int row = 1;
	
	public GridImgAdapter(Context mContext, List<UriHttpEntity> imgs) {
		super();
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(mContext);
		this.imgs = imgs;
		WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
		width = wm.getDefaultDisplay().getWidth();;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imgs == null? 0 : imgs.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return imgs.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder vh = new ViewHolder();
		v = inflater.inflate(R.layout.grid_img_item, null);
		vh.img = (ImageView) v.findViewById(R.id.img);
		vh.imgRl = (RelativeLayout) v.findViewById(R.id.imgRl);
		
		if(imgs.get(arg0).getUri() == null)
			if(imgs.get(arg0).getUrl() == null){
				vh.img.setImageResource(R.drawable.icon_addpic);
			} else {
				FinalBitmap.create(inflater.getContext()).display(vh.img,
						FinalContent.FinalUrl + imgs.get(arg0).getUrl());
			}
		else {
			vh.img.setImageURI(imgs.get(arg0).getUri());
		}

		LayoutParams params = vh.imgRl.getLayoutParams();
		params.height = (width - 30) / row;
		vh.imgRl.setLayoutParams(params);
		return v;
	}
	
	private class ViewHolder{
		ImageView img;
		RelativeLayout imgRl;
	}
}
