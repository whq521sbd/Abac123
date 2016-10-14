package com.aotuo.vegetable.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.view.View;
import android.widget.ImageView;

import com.custom.nostra13.universalimageloader.cache.disc.DiscCacheAware;
import com.custom.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import com.custom.nostra13.universalimageloader.core.DisplayImageOptions;
import com.custom.nostra13.universalimageloader.core.ImageLoader;
import com.custom.nostra13.universalimageloader.core.assist.FailReason;
import com.custom.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class ImageUtil {
	private static LruCacheUtils lru;
	public static void displayUserImage(String url, ImageView view) {
		ImageLoader imageLoader = ImageLoader.getInstance();
		DisplayImageOptions options = Options.getUserOptions();
		imageLoader.displayImage(url, view, options);
	}

	public static void displayPicImage(String url, ImageView view) {
		ImageLoader imageLoader = ImageLoader.getInstance();
		DisplayImageOptions options = Options.getPicOptions();
		//imageLoader.displayImage(url, view, options);
		imageLoader.displayImage(url, view, options,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String s, View view1) {
						// TODO Auto-generated method stub
						super.onLoadingStarted(s, view1);
					}

					@Override
					public void onLoadingFailed(String s, View view1,
							FailReason failreason) {
						// TODO Auto-generated method stub
						super.onLoadingFailed(s, view1, failreason);
					}

					@Override
					public void onLoadingComplete(String s, View view, Bitmap bm) {
						// TODO Auto-generated method stub
						ImageLoader imageLoader = ImageLoader.getInstance();
						if(bm == null || bm.isRecycled()){
							super.onLoadingComplete(s, view, bm);
							return;
						}
						DiscCacheAware faf = imageLoader.getDiscCache();
						File ff = faf.get(s);
						if(!ff.exists()){
							super.onLoadingComplete(s, view, bm);
							return;
						}
						int degree = 0;
						try {
							ExifInterface exifInterface = new ExifInterface(ff
									.getPath());
							int orientation = exifInterface.getAttributeInt(
									ExifInterface.TAG_ORIENTATION,
									ExifInterface.ORIENTATION_NORMAL);
							switch (orientation) {
							case ExifInterface.ORIENTATION_ROTATE_90:
								degree = 90;
								rotate(degree, bm, view, s);
								break;

							case ExifInterface.ORIENTATION_ROTATE_180:
								degree = 180;
								rotate(degree, bm, view, s);
								break;
							case ExifInterface.ORIENTATION_ROTATE_270:
								degree = 270;
								rotate(degree, bm, view, s);
								break;
							}
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							super.onLoadingComplete(s, view, bm);
						}
					}

					@Override
					public void onLoadingCancelled(String s, View view1) {
						// TODO Auto-generated method stub
						super.onLoadingCancelled(s, view1);
					}
				});
	}
	
	private static void rotate(int degree, Bitmap bm, View view, String url){
		if(lru == null){
			lru = new LruCacheUtils();
		}
		Bitmap returnBm = null;
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);
		if(lru.getBitmapFromMemCache(url) != null){
			returnBm = lru.getBitmapFromMemCache(url);
		} else {
			try {
				returnBm = Bitmap.createBitmap(bm, 0, 0,
						bm.getWidth(), bm.getHeight(),
						matrix, true);
			} catch (OutOfMemoryError e) {
			}
		
			if (returnBm == null) {
				returnBm = bm;
				Log.i("ImageUtil", "create bitmap failed!!!");
			} else {
				lru.addBitmapToMemoryCache(url, returnBm);
			}
		}
		if(view instanceof ImageView){
			((ImageView) view).setImageBitmap(returnBm);
		}
	}

	public static boolean isCache(Context context, String uri) {
		ImageLoader imageLoader = ImageLoader.getInstance();
		MemoryCacheAware<String, Bitmap> cacheMap = imageLoader
				.getMemoryCache();
		if (cacheMap == null)
			return false;
		Collection<String> keys = cacheMap.keys();
		Iterator<String> iterator = keys.iterator();
		if (iterator == null)
			return false;
		while (iterator.hasNext()) {
			if (iterator.next().contains(uri))
				return true;
		}
		return false;
	}

}
