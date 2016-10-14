package com.aotuo.vegetable.act;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.util.FileCache;
import com.aotuo.vegetable.util.GetPathFromUri4kitkat;
import com.aotuo.vegetable.util.Log;
import com.aotuo.vegetable.view.crop.CropActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetPicActivity extends Activity {
	private static final String TAG = "UplodingActivity";
	public static final int GET_SHOPPING_USER_PHOTO = 5;
	public static final int GET_USER_PHOTO = 6;
	private static final int MEDIA_TYPE_IMAGE = 1;
	private static final int TAKE_PHOTO = 1;
	private static final int GET_PICTURE_FROM_G = 2;
	private static final int PHOTOZOOM = 3;
	public static final int UPHEADICON = 2001; // 上传头像成功

	private Uri uri;
	private FileCache mFileCache;

	private TextView mTakePhoto;
	private TextView mGetPictureFromG;
	private TextView mCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uploding_picture);
		init();
	}

	private void init() {
		mTakePhoto = (TextView) findViewById(R.id.take_photo);
		mGetPictureFromG = (TextView) findViewById(R.id.getpicture_g);
		mCancel = (TextView) findViewById(R.id.picture_cancel);
		mFileCache = new FileCache(GetPicActivity.this);
		makeRootDirectory(mFileCache.getAbsolutePath() + "/uplodingPic");
		Bundle bundle = getIntent().getExtras();

		mTakePhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				uri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

				Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent1.putExtra("autofocus", true);// 自动对焦
				intent1.putExtra("fullScreen", false);// 全屏
				intent1.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				startActivityForResult(intent1, TAKE_PHOTO);

			}
		});

		mGetPictureFromG.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent2 = new Intent(Intent.ACTION_PICK, null);
				intent2.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
						"image/png");
				startActivityForResult(intent2, GET_PICTURE_FROM_G);
			}
		});

		mCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				finish();
			}
		});

	}

	private Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	private File getOutputMediaFile(int type) {
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.
		File mediaStorageDir = new File("/storage/emulated/0/Android/data/com.aotuo.vegetable/cache");
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile = null;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
		}

		return mediaFile;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.err.println("--------requestCode---------" + resultCode);
		if (requestCode == GET_PICTURE_FROM_G && data != null) { // 相册返回
			String path = GetPathFromUri4kitkat.getPath(
					getApplicationContext(), data.getData());
			File f = new File(path);
			startPhotoCrop(Uri.fromFile(f));
			// startPhotoZoom(Uri.fromFile(f));
		}

		if (requestCode == TAKE_PHOTO && resultCode == -1) { // 照相机返回
			//String path = GetPathFromUri4kitkat.getPath(
			//		getApplicationContext(), data.getData());

			File f = new File(uri.getPath());
			startPhotoCrop(Uri.fromFile(f));
		}
		if (requestCode == PHOTOZOOM) {
			if (data != null) {
				// Toast.makeText(GetPicActivity.this, "添加照片成功",
				// Toast.LENGTH_SHORT).show();
				setResult(RESULT_OK, data);
				finish();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public final static String IMAGE_URI = "iamge_uri";

	private void startPhotoCrop(Uri uri) {
		Intent intent = new Intent(GetPicActivity.this, CropActivity.class);
		intent.putExtra(IMAGE_URI, uri);

		intent.putExtra("cropWidth", 640);
		intent.putExtra("cropHeight", 640);
		startActivityForResult(intent, PHOTOZOOM);
	}

	/**
	 * 保存文件前先新建保存文件的路径
	 * 
	 * @param filePath
	 */
	public static void makeRootDirectory(String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 保存裁剪完的图片
	 * 
	 * @param picdata
	 */
	@SuppressLint("NewApi")
	private Bitmap setPicToView(Intent picdata) {
		Bundle extras = picdata.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			return photo;
		}
		return null;
	}

	// --------------

	private File mFile;

}
