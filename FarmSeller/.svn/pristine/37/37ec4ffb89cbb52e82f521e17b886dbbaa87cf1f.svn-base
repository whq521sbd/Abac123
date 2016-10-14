package com.aotuo.vegetable.sqlite;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aotuo.vegetable.entity.CityMessage;
import com.aotuo.vegetable.entity.CitySubMessage;

public class DBCityUtils {
	private static DBManager dbm;
	private static SQLiteDatabase db;

	public DBCityUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	// ///////////////////////////////////////
	/**
	 * 查省、市、县区
	 */
	public static ArrayList<CityMessage> getprovince(Context context, String apid) {
		ArrayList<CityMessage> list = new ArrayList<CityMessage>();
		dbm = new DBManager(context.getApplicationContext());
		dbm.openDatabase();
		db = dbm.getDatabase();
		// SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from city where area_parent_id=?",
				new String[] { apid });
		while (cursor.moveToNext()) {
			int area_id = cursor.getInt(cursor.getColumnIndex("area_id"));
			String area_name = cursor.getString(cursor
					.getColumnIndex("area_name"));
			String area_parent_id = cursor.getString(cursor
					.getColumnIndex("area_parent_id"));
			String area_deep = cursor.getString(cursor
					.getColumnIndex("area_deep"));
			String letter = cursor.getString(cursor
					.getColumnIndex("letter"));
			String area_region = cursor.getString(cursor
					.getColumnIndex("area_region"));
			CityMessage scm = new CityMessage(area_id, area_name,
					area_parent_id, area_deep, letter, area_region);
			list.add(scm);
		}
		cursor.close();
		db.close();
		dbm.closeDatabase();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 查省、市、县区
	 */
	public static ArrayList<CityMessage> getallcity(Context context) {
		ArrayList<CityMessage> list = new ArrayList<CityMessage>();
		dbm = new DBManager(context.getApplicationContext());
		dbm.openDatabase();
		db = dbm.getDatabase();
		// SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from city where area_deep =? and area_parent_id < ?",
				new String[]{"2", "35"});
		while (cursor.moveToNext()) {
			int area_id = cursor.getInt(cursor.getColumnIndex("area_id"));
			String area_name = cursor.getString(cursor
					.getColumnIndex("area_name"));
			String area_parent_id = cursor.getString(cursor
					.getColumnIndex("area_parent_id"));
			String area_deep = cursor.getString(cursor
					.getColumnIndex("area_deep"));
			String letter = cursor.getString(cursor
					.getColumnIndex("letter"));
			String area_region = cursor.getString(cursor
					.getColumnIndex("area_region"));
			CityMessage scm = new CityMessage(area_id, area_name,
					area_parent_id, area_deep, letter, area_region);
			list.add(scm);
		}
		cursor.close();
		db.close();
		dbm.closeDatabase();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	/**
	 * 查省、市、县区
	 */
	public static ArrayList<CityMessage> getSearchCity(Context context, String key) {
		ArrayList<CityMessage> list = new ArrayList<CityMessage>();
		dbm = new DBManager(context.getApplicationContext());
		dbm.openDatabase();
		db = dbm.getDatabase();
		// SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from city where area_deep =? and area_parent_id < ?",
				new String[]{"2", "35"});
		while (cursor.moveToNext()) {
			int area_id = cursor.getInt(cursor.getColumnIndex("area_id"));
			String area_name = cursor.getString(cursor
					.getColumnIndex("area_name"));
			
			if(!area_name.contains(key))
				continue;
			
			String area_parent_id = cursor.getString(cursor
					.getColumnIndex("area_parent_id"));
			String area_deep = cursor.getString(cursor
					.getColumnIndex("area_deep"));
			String letter = cursor.getString(cursor
					.getColumnIndex("letter"));
			String area_region = cursor.getString(cursor
					.getColumnIndex("area_region"));
			CityMessage scm = new CityMessage(area_id, area_name,
					area_parent_id, area_deep, letter, area_region);
			list.add(scm);
		}
		cursor.close();
		db.close();
		dbm.closeDatabase();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	/**
	 * 查省、市、县区
	 */
	public static ArrayList<CityMessage> getSearchCityID(Context context, String key) {
		ArrayList<CityMessage> list = new ArrayList<CityMessage>();
		dbm = new DBManager(context.getApplicationContext());
		dbm.openDatabase();
		db = dbm.getDatabase();
		// SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from city where area_deep =? and area_parent_id < ?",
				new String[]{"2", "35"});
		while (cursor.moveToNext()) {
			int area_id = cursor.getInt(cursor.getColumnIndex("area_id"));
			String area_name = cursor.getString(cursor
					.getColumnIndex("area_name"));
			
			if(!area_name.contains(key))
				continue;
			
			String area_parent_id = cursor.getString(cursor
					.getColumnIndex("area_parent_id"));
			String area_deep = cursor.getString(cursor
					.getColumnIndex("area_deep"));
			String letter = cursor.getString(cursor
					.getColumnIndex("letter"));
			String area_region = cursor.getString(cursor
					.getColumnIndex("area_region"));
			CityMessage scm = new CityMessage(area_id, area_name,
					area_parent_id, area_deep, letter, area_region);
			list.add(scm);
		}
		cursor.close();
		db.close();
		dbm.closeDatabase();
		if (list != null && list.size() > 0) {
			if(list.size() == 1)
				return list;
			else {
				for(CityMessage scm: list){
					if(scm.getArea_name().equals(key) || scm.getArea_name().equals(key + "市")){
						list.clear();
						list.add(scm);
						break;
					}
				}
				return list;
			}
		}
		return null;
	}

	/**
	 * 查找省市的id
	 */
	public static int getid(Context context, String area_city) {
		dbm = new DBManager(context.getApplicationContext());
		dbm.openDatabase();
		db = dbm.getDatabase();
		// SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		String sql = "select area_id from city where area_name like '%"
				+ area_city + "%'";
		Cursor cursor = db.rawQuery(sql, null);
		int area_id = 0;
		while (cursor.moveToNext()) {
			area_id = cursor.getInt(cursor.getColumnIndex("area_id"));
		}
		cursor.close();
		db.close();
		dbm.closeDatabase();
		if (area_id != 0) {
			return area_id;
		}
		return 0;
	}

	/**
	 * 查省
	 */
	public static ArrayList<CitySubMessage> getallprovince(Context context) {
		ArrayList<CitySubMessage> list = new ArrayList<CitySubMessage>();
		dbm = new DBManager(context.getApplicationContext());
		dbm.openDatabase();
		db = dbm.getDatabase();
		// SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from city where area_parent_id=?",
				new String[] { "0" });
		while (cursor.moveToNext()) {
			int area_id = cursor.getInt(cursor.getColumnIndex("area_id"));
			String area_name = cursor.getString(cursor
					.getColumnIndex("area_name"));
			CitySubMessage scm = new CitySubMessage(area_id, area_name);
			list.add(scm);
		}
		cursor.close();
		db.close();
		dbm.closeDatabase();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 查市
	 */
	public static ArrayList<CityMessage> getProvince(Context context, String apid) {
		ArrayList<CityMessage> list = new ArrayList<CityMessage>();
		dbm = new DBManager(context.getApplicationContext());
		dbm.openDatabase();
		db = dbm.getDatabase();
		// SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from city where area_parent_id=?",
				new String[] { apid });
		while (cursor.moveToNext()) {
			int area_id = cursor.getInt(cursor.getColumnIndex("area_id"));
			String area_name = cursor.getString(cursor
					.getColumnIndex("area_name"));
			String area_parent_id = cursor.getString(cursor
					.getColumnIndex("area_parent_id"));
			String area_deep = cursor.getString(cursor
					.getColumnIndex("area_deep"));
			String letter = cursor.getString(cursor
					.getColumnIndex("letter"));
			String area_region = cursor.getString(cursor
					.getColumnIndex("area_region"));
			CityMessage scm = new CityMessage(area_id, area_name,
					area_parent_id, area_deep, letter, area_region);
			
			list.add(scm);
		}
		cursor.close();
		db.close();
		dbm.closeDatabase();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	/**
	 * 查市
	 */
	public static ArrayList<CityMessage> getCityInPrivince(Context context, String apid) {
		ArrayList<CityMessage> list = new ArrayList<CityMessage>();
		dbm = new DBManager(context.getApplicationContext());
		dbm.openDatabase();
		db = dbm.getDatabase();
		// SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from city where area_parent_id=?",
				new String[] { apid });
		while (cursor.moveToNext()) {
			int area_id = cursor.getInt(cursor.getColumnIndex("area_id"));
			String area_name = cursor.getString(cursor
					.getColumnIndex("area_name"));
			String area_parent_id = cursor.getString(cursor
					.getColumnIndex("area_parent_id"));
			String area_deep = cursor.getString(cursor
					.getColumnIndex("area_deep"));
			String letter = cursor.getString(cursor
					.getColumnIndex("letter"));
			String area_region = cursor.getString(cursor
					.getColumnIndex("area_region"));
			CityMessage scm = new CityMessage(area_id, area_name,
					area_parent_id, area_deep, letter, area_region);
			
			list.add(scm);
		}
		cursor.close();
		db.close();
		dbm.closeDatabase();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	/**
	 * 查市
	 */
	public static ArrayList<CitySubMessage> getsearchcity(Context context, String name) {
		ArrayList<CitySubMessage> list = new ArrayList<CitySubMessage>();
		dbm = new DBManager(context.getApplicationContext());
		dbm.openDatabase();
		db = dbm.getDatabase();
		// SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		String sql = "select * from city where area_deep=2 and area_name like '%"
				+ name + "%'";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			int area_id = cursor.getInt(cursor.getColumnIndex("area_id"));
			String area_name = cursor.getString(cursor
					.getColumnIndex("area_name"));
			CitySubMessage scm = new CitySubMessage(area_id, area_name);
			list.add(scm);
		}
		cursor.close();
		db.close();
		dbm.closeDatabase();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
