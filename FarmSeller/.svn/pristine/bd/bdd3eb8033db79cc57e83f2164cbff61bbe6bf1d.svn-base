package com.aotuo.vegetable.util;

import android.graphics.Bitmap;

import com.custom.nostra13.universalimageloader.core.DisplayImageOptions;
import com.custom.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.custom.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.aotuo.vegetable.R;

public class Options {
	public static DisplayImageOptions getUserOptions() {
		DisplayImageOptions useroptions = new DisplayImageOptions.Builder()
				// 设置图片在下载前是否重置，复位
//				.showImageOnLoading(R.drawable.mine_photo)
				// 设置图片Uri为空或是错误的时候显示的图片
				.showImageForEmptyUri(R.drawable.mine_photo)
				// 设置图片加载/解码过程中错误时候显示的图片
				.showImageOnFail(R.drawable.mine_photo)
				// 设置下载的图片是否缓存在内存中
				.cacheInMemory(true)
				// 设置下载的图片是否缓存在SD卡中
				.cacheOnDisc(true)
				// 是否考虑JPEG图像EXIF参数（旋转，翻转）
				// 设置图片以如何的编码方式显示
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				//
				// 设置图片的解码类型,默认值——Bitmap.Config.ARGB_8888
				.bitmapConfig(Bitmap.Config.RGB_565)
				// 设置图片在下载前是否重置，复位
				.resetViewBeforeLoading(true)
				.displayer(new RoundedBitmapDisplayer(8)).build();
		return useroptions;
	}

	public static DisplayImageOptions getPicOptions() {
		DisplayImageOptions useroptions = new DisplayImageOptions.Builder()
//				.showImageOnLoading(R.drawable.defaultimg)
				.showImageForEmptyUri(R.drawable.defaultimg)
				.showImageOnFail(R.drawable.defaultimg).cacheInMemory(true)
				.cacheOnDisc(true)
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				//
				.bitmapConfig(Bitmap.Config.RGB_565)
				.resetViewBeforeLoading(true)
				// .displayer(new RoundedBitmapDisplayer(0))
				.build();
		return useroptions;
	}
}
