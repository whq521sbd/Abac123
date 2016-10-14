package com.aotuo.vegetable.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.aotuo.vegetable.entity.AreaData;
import com.aotuo.vegetable.entity.AreaMessage;
import com.aotuo.vegetable.entity.KeyValueData;

import java.util.ArrayList;
import java.util.List;


public class AreaDBDao {
    private AreaDBOpenHelper dbOpenHelper = null;

    public AreaDBDao(Context context) {
        dbOpenHelper = new AreaDBOpenHelper(context, "myArea.db");
    }

    /**
     * 添加类别
     */
    public void addAreaAll(AreaData areaData) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        String sql = "insert into area(gc_id,gc_name,gc_parent_id) values(?,?,?)";
        db.beginTransaction();

        SQLiteStatement stmt = db.compileStatement(sql);
        for (KeyValueData kv : areaData.getProvince()) {
            stmt.bindString(1, kv.getKey());
            stmt.bindString(2, kv.getValue());
            stmt.bindString(3, "0");//parent

            stmt.execute();
            stmt.clearBindings();
        }
        for (KeyValueData kv : areaData.getCity()) {

            stmt.bindString(1, kv.getKey());
            stmt.bindString(2, kv.getValue());
            stmt.bindString(3, kv.getKey().substring(0, 2));//parent

            stmt.execute();
            stmt.clearBindings();
        }
        for (KeyValueData kv : areaData.getArea()) {

            stmt.bindString(1, kv.getKey());
            stmt.bindString(2, kv.getValue());
            stmt.bindString(3, kv.getKey().substring(0, 4));//parent

            stmt.execute();
            stmt.clearBindings();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    /**
     * 添加类别
     */
    public void addArea(List<AreaMessage> list) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        String sql = "insert into area(gc_id,gc_name,gc_parent_id) values(?,?,?)";
        db.beginTransaction();

        SQLiteStatement stmt = db.compileStatement(sql);
        for (int i = 0; i < list.size(); i++) {
            stmt.bindString(1, list.get(i).getGc_id());
            stmt.bindString(2, list.get(i).getGc_name());
            stmt.bindString(3, list.get(i).getGc_parent_id());

            stmt.execute();
            stmt.clearBindings();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    /**
     * 查询所有类别
     */
    public ArrayList<AreaMessage> getAllArea() {
        ArrayList<AreaMessage> list = new ArrayList<AreaMessage>();
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("area", new String[]{"gc_id",
                "gc_name", "gc_parent_id"}, null, null, null, null, "gc_id asc", null);
        while (cursor.moveToNext()) {
            String gc_id = cursor.getString(cursor.getColumnIndex("gc_id"));
            String gc_name = cursor.getString(cursor
                    .getColumnIndex("gc_name"));
            String gc_parent_id = cursor.getString(cursor
                    .getColumnIndex("gc_parent_id"));

            AreaMessage scm = new AreaMessage(gc_id, gc_name,
                    gc_parent_id);
            list.add(scm);
        }
        cursor.close();
        db.close();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    //清空表
    public void deletetable() {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete("area", null, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /**
     * 按条件查找子列表
     */
    public List<AreaMessage> getChildArea(String childId) {
        ArrayList<AreaMessage> list = new ArrayList<AreaMessage>();
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from area where gc_parent_id=?", new String[]{childId});
        while (cursor.moveToNext()) {
            String gc_id = cursor.getString(cursor.getColumnIndex("gc_id"));
            String gc_name = cursor.getString(cursor
                    .getColumnIndex("gc_name"));
            String gc_parent_id = cursor.getString(cursor
                    .getColumnIndex("gc_parent_id"));

            AreaMessage scm = new AreaMessage(gc_id, gc_name,
                    gc_parent_id);
            list.add(scm);
        }
        cursor.close();
        db.close();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * 按条件查找子列表
     */
    public AreaMessage getArea(String id) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from area where gc_id=?", new String[]{id});
        while (cursor.moveToNext()) {
            String gc_id = cursor.getString(cursor.getColumnIndex("gc_id"));
            String gc_name = cursor.getString(cursor
                    .getColumnIndex("gc_name"));
            String gc_parent_id = cursor.getString(cursor
                    .getColumnIndex("gc_parent_id"));

            AreaMessage scm = new AreaMessage(gc_id, gc_name,
                    gc_parent_id);
            return scm;
        }
        cursor.close();
        db.close();

        return null;
    }
}
