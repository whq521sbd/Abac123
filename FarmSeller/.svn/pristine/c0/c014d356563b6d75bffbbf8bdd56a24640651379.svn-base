package com.aotuo.vegetable.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class FileCache {
	private static final String TAG = FileCache.class.getSimpleName();
	private Context context;

	public FileCache(Context context) {
		this.context = context;
	}

	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/**
	 * 保存二进制流到指定路径
	 * 
	 * @param instream
	 * @param filepath
	 */
	public void saveFile(InputStream instream, String filepath) {
		if (!isExternalStorageWritable()) {
			return;
		}

		File file = new File(filepath);

		try {
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int cnt = 0;

			while ((cnt = instream.read(buffer)) != -1) {
				fos.write(buffer, 0, cnt);
			}

			instream.close();
			fos.close();

		} catch (FileNotFoundException e) {
			Log.i(TAG, e.getMessage());
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
		}
	}

	/**
	 * Copy file
	 * 
	 * @param from
	 *            origin file path
	 * @param to
	 *            target file path
	 */
	public void copyFile(String from, String to) {
		if (!isExternalStorageWritable()) {
			Log.i(TAG, "SD卡不可用，保存失败");
			return;
		}

		File fileFrom = new File(from);
		File fileTo = new File(to);

		try {

			FileInputStream fis = new FileInputStream(fileFrom);
			FileOutputStream fos = new FileOutputStream(fileTo);
			byte[] buffer = new byte[1024];
			int cnt = 0;

			while ((cnt = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, cnt);
			}

			fis.close();
			fos.close();

		} catch (FileNotFoundException e) {
			Log.i(TAG, e.getMessage());
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
		}
	}

	/**
	 * 保存 JSON 字符串到指定文件
	 * 
	 * @param json
	 * @param filepath
	 */
	public boolean saveJson(String json, String filepath) {
		if (!isExternalStorageWritable()) {
			Log.i(TAG, "SD卡不可用，保存失败");
			return false;
		}

		File file = new File(filepath);

		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(json.getBytes());
			fos.close();

		} catch (FileNotFoundException e) {
			Log.i(TAG, e.getMessage());
		} catch (IOException e) {
			return false;
		}

		return true;
	}

	/**
	 * 删除指定的 JSON 文件
	 * 
	 * @param filepath
	 * @return
	 */
	public boolean deleteJson(String filepath) {
		if (!isExternalStorageWritable()) {
			Log.i(TAG, "SD卡不可用，保存失败");
			return false;
		}

		File file = new File(filepath);

		if (file.exists()) {
			file.delete();
		}

		return false;
	}

	/**
	 * 从指定文件读取 JSON 字符串
	 * 
	 * @param filepath
	 * @return
	 */
	public String readJson(String filepath) {
		if (!isExternalStorageWritable()) {
			Log.i(TAG, "SD卡不可用，保存失败");
			return null;
		}

		File file = new File(filepath);
		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			reader.close();

		} catch (FileNotFoundException e) {
			Log.i(TAG, e.getMessage());
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
		}

		return sb.toString();
	}

	/**
	 * 保存图片到制定路径
	 * 
	 * @param filepath
	 * @param bitmap
	 */
	public void saveBitmap(String filepath, Bitmap bitmap) {
		if (!isExternalStorageWritable()) {
			Log.i(TAG, "SD卡不可用，保存失败");
			return;
		}

		if (bitmap == null) {
			return;
		}
		
		try {
			File files = new File(filepath.substring(0, filepath.lastIndexOf("/")));
			
			if (!files.exists()) {
				files.mkdirs();
			}
			
			File file = new File(filepath);
			FileOutputStream outputstream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputstream);
			outputstream.flush();
			outputstream.close();
		} catch (FileNotFoundException e) {
			Log.i(TAG, e.getMessage());
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
		}
	}
	
	public File saveMyBitmap(Bitmap bitmap, String bitName) throws IOException {
		File f = new File(getAbsolutePath() + "/uplodingPic/"
				+ bitName);
		Log.i("取名字", ">>>>>>>>>>>>>>" + f.getPath());
		f.createNewFile();
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fOut);
			fOut.flush();
			fOut.close();

			return f;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 删除 Files 目录下所有的图片
	 * 
	 * @return
	 */
	public boolean cleanCache() {
		if (!isExternalStorageWritable()) {
			Log.i(TAG, "SD卡不可用，保存失败");
			return false;
		}

		File dir = new File(getAbsolutePath() + "/bankImg");
		
		if (dir.isFile()) {
			dir.delete();
			return true;
		}

		if(dir.isDirectory()){
			File[] childFiles = dir.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				dir.delete();
				return true;
			}
	
			for (int i = 0; i < childFiles.length; i++) {
				childFiles[i].delete();
			}
			dir.delete();
		}

		return true;
	}
	
	/**
	 * 删除 uplodingPic 目录下所有的图片
	 * 
	 * @return
	 */
	public boolean cleanPicCache() {
		if (!isExternalStorageWritable()) {
			Log.i(TAG, "SD卡不可用，保存失败");
			return false;
		}

		File dir = new File(getAbsolutePath() + "/uplodingPic");
		
		if (dir.isFile()) {
			dir.delete();
			return true;
		}

		if(dir.isDirectory()){
			File[] childFiles = dir.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				dir.delete();
				return true;
			}
	
			for (int i = 0; i < childFiles.length; i++) {
				childFiles[i].delete();
			}
			dir.delete();
		}

		return true;
	}
	
	
	/**
	 * 删除advertImg 目录下所有的图片
	 * 
	 * @return
	 */
	public boolean cleanAdvertCache() {
		if (!isExternalStorageWritable()) {
			Log.i(TAG, "SD卡不可用，保存失败");
			return false;
		}

		File dir = new File(getAbsolutePath() + "/advertImg");

		if (dir.isFile()) {
			dir.delete();
			return true;
		}

		if(dir.isDirectory()){
			File[] childFiles = dir.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				dir.delete();
				return true;
			}
	
			for (int i = 0; i < childFiles.length; i++) {
				childFiles[i].delete();
			}
			dir.delete();
		}

		return true;
	}

	/**
	 * 计算 Files 目录下图片的大小
	 * 
	 * @return
	 */
	public String getCacheSize() {
		if (!isExternalStorageWritable()) {
			Log.i(TAG, "SD卡不可用，保存失败");
			return null;
		}

		long sum = 0;
		File dir = context.getExternalFilesDir(null);

		if (dir != null) {
			for (File file : dir.listFiles()) {
				sum += file.length();
			}
		}

		if (sum < 1024) {
			return sum + "字节";
		} else if (sum < 1024 * 1024) {
			return (sum / 1024) + "K";
		} else {
			return (sum / (1024 * 1024)) + "M";
		}
	}

	/**
	 * 返回当前应用 SD 卡的绝对路径 like
	 * /storage/sdcard0/Android/data/com.example.test/files
	 */
	public String getAbsolutePath() {
		File root = context.getExternalFilesDir(null);
		String pageName = context.getPackageName();
		
		
		if (root != null) {
			return root.getAbsolutePath();
		}

		return "/storage/emulated/0/Android/data/" + pageName + "/files";
	}

	/**
	 * 返回当前应用的 SD卡缓存文件夹绝对路径 like
	 * /storage/sdcard0/Android/data/com.example.test/cache
	 */
	public String getCachePath() {
		File root = context.getExternalCacheDir();
		String pageName = context.getPackageName();
		
		if (root != null) {
			return root.getAbsolutePath();
		}

		return "/storage/emulated/0/Android/data/" + pageName + "/cache";
	}

	public boolean isBitmapExists(String filename) {
		File dir = context.getExternalFilesDir(null);
		File file = new File(dir, filename);

		return file.exists();
	}
	   
	
	public Bitmap getBitmap(){
		Bitmap bitmap = null;
		bitmap = BitmapFactory.decodeFile(getAbsolutePath() + "/uplodingPic"  + "/image.png");
		Log.i(TAG, "------->Bitmap" + getAbsolutePath() + "/uplodingPic"  + "/image.png");
		return bitmap;
	}
	
	public String getBitmapPath(){
		return getAbsolutePath() + "/uplodingPic/" + "/image.jpg" ;
	}
}
