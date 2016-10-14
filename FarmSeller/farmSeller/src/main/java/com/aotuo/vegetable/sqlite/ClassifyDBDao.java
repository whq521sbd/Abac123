package com.aotuo.vegetable.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.aotuo.vegetable.entity.ClassicData;
import com.aotuo.vegetable.entity.ClassifyMessage;
import com.aotuo.vegetable.entity.KeyValueData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClassifyDBDao {
    private ClassifyDBOpenHelper dbOpenHelper = null;

    public ClassifyDBDao(Context context) {
        dbOpenHelper = new ClassifyDBOpenHelper(context, "myclassifies.db");
    }

    /**
     * 添加类别
     */
    public void addClassifyAll(ClassicData classicData) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        String sql = "insert into myclassify(gc_id,gc_name,gc_parent_id,gc_sort,gc_img) values(?,?,?,?,?)";
        db.beginTransaction();

        SQLiteStatement stmt = db.compileStatement(sql);
        for (KeyValueData kv : classicData.getClassX()) {

            stmt.bindString(1, kv.getKey());
            stmt.bindString(2, kv.getValue());
            stmt.bindString(3, "0");//parent
            stmt.bindLong(4, 0);
            stmt.bindString(5, "");
            stmt.execute();
            stmt.clearBindings();
        }
        for (KeyValueData kv : classicData.getClassXX()) {

            stmt.bindString(1, kv.getKey());
            stmt.bindString(2, kv.getValue());
            stmt.bindString(3, kv.getKey().substring(0, kv.getKey().length() - 2));//parent
            stmt.bindLong(4, 0);
            stmt.bindString(5, "");
            stmt.execute();
            stmt.clearBindings();
        }
        for (KeyValueData kv : classicData.getClassXXX()) {

            stmt.bindString(1, kv.getKey());
            stmt.bindString(2, kv.getValue());
            stmt.bindString(3, kv.getKey().substring(0, 4));//parent
            stmt.bindLong(4, 0);
            stmt.bindString(5, "");
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
    public void addclassify(List<ClassifyMessage> list) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        String sql = "insert into myclassify(gc_id,gc_name,gc_parent_id,gc_sort,gc_img) values(?,?,?,?,?)";
        db.beginTransaction();

        SQLiteStatement stmt = db.compileStatement(sql);
        for (int i = 0; i < list.size(); i++) {
            stmt.bindString(1, list.get(i).getGc_id());
            stmt.bindString(2, list.get(i).getGc_name());
            stmt.bindString(3, list.get(i).getGc_parent_id());
            stmt.bindLong(4, list.get(i).getGc_sort());
            if (list.get(i).getGc_img() == null)
                stmt.bindString(5, "");
            else
                stmt.bindString(5, list.get(i).getGc_img());
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
    public ArrayList<ClassifyMessage> getAllclassify() {
        ArrayList<ClassifyMessage> list = new ArrayList<ClassifyMessage>();
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("myclassify", new String[]{"gc_id",
                "gc_name", "gc_parent_id", "gc_img"}, null, null, null, null, "gc_id asc", null);
        while (cursor.moveToNext()) {
            String gc_id = cursor.getString(cursor.getColumnIndex("gc_id"));
            String gc_name = cursor.getString(cursor
                    .getColumnIndex("gc_name"));
            String gc_parent_id = cursor.getString(cursor
                    .getColumnIndex("gc_parent_id"));
            String gc_img = cursor.getString(cursor
                    .getColumnIndex("gc_img"));
            int gc_sort = cursor.getInt(cursor.getColumnIndex("gc_sort"));
            ClassifyMessage scm = new ClassifyMessage(gc_id, gc_name,
                    gc_parent_id, gc_sort, gc_img);
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
            db.delete("myclassify", null, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /**
     * 按条件查找组类别（第二级）
     */
    public ArrayList<ClassifyMessage> getGroupsClassify(String gid) {
        if(gid == null)
            return null;
        ArrayList<ClassifyMessage> list = new ArrayList<ClassifyMessage>();
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from myclassify where gc_parent_id=?", new String[]{gid});
        while (cursor.moveToNext()) {
            String gc_id = cursor.getString(cursor.getColumnIndex("gc_id"));
            String gc_name = cursor.getString(cursor
                    .getColumnIndex("gc_name"));
            String gc_parent_id = cursor.getString(cursor
                    .getColumnIndex("gc_parent_id"));
            String gc_img = cursor.getString(cursor
                    .getColumnIndex("gc_img"));
            int gc_sort = cursor.getInt(cursor.getColumnIndex("gc_sort"));
            ClassifyMessage scm = new ClassifyMessage(gc_id, gc_name,
                    gc_parent_id, gc_sort, gc_img);
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
     * 按条件查找组类别（第二级）
     */
    public ClassifyMessage getClassify(String id) {
        ClassifyMessage data = null;
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from myclassify where gc_id=?", new String[]{id});
        if (cursor.moveToNext()) {
            String gc_id = cursor.getString(cursor.getColumnIndex("gc_id"));
            String gc_name = cursor.getString(cursor
                    .getColumnIndex("gc_name"));
            String gc_parent_id = cursor.getString(cursor
                    .getColumnIndex("gc_parent_id"));
            String gc_img = cursor.getString(cursor
                    .getColumnIndex("gc_img"));
            int gc_sort = cursor.getInt(cursor.getColumnIndex("gc_sort"));
            data = new ClassifyMessage(gc_id, gc_name,
                    gc_parent_id, gc_sort, gc_img);
        }
        cursor.close();
        db.close();

        return data;
    }

    /**
     * 按条件查找第三级孩子
     */
    public Map<String, List<ClassifyMessage>> getChildsClassify(String gid) {
        Map<String, List<ClassifyMessage>> childs = new HashMap<String, List<ClassifyMessage>>();
        ArrayList<ClassifyMessage> list = new ArrayList<ClassifyMessage>();
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from myclassify where gc_parent_id=?", new String[]{gid});
        while (cursor.moveToNext()) {
            String gc_id = cursor.getString(cursor.getColumnIndex("gc_id"));
            String gc_name = cursor.getString(cursor
                    .getColumnIndex("gc_name"));
            String gc_parent_id = cursor.getString(cursor
                    .getColumnIndex("gc_parent_id"));
            String gc_img = cursor.getString(cursor
                    .getColumnIndex("gc_img"));
            int gc_sort = cursor.getInt(cursor.getColumnIndex("gc_sort"));
            ClassifyMessage scm = new ClassifyMessage(gc_id, gc_name,
                    gc_parent_id, gc_sort, gc_img);
            list.add(scm);
        }
        cursor.close();
        db.close();
        childs.put(gid, list);
        if (childs != null && childs.size() > 0) {
            return childs;
        }
        return null;
    }

    /**
     * 按条件查找第四级分类
     */
    public ArrayList<ClassifyMessage> getThirdClassify(String gid) {
        ArrayList<ClassifyMessage> list = new ArrayList<ClassifyMessage>();
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from myclassify where gc_parent_id=?", new String[]{gid});
        while (cursor.moveToNext()) {
            String gc_id = cursor.getString(cursor.getColumnIndex("gc_id"));
            String gc_name = cursor.getString(cursor
                    .getColumnIndex("gc_name"));
            String gc_parent_id = cursor.getString(cursor
                    .getColumnIndex("gc_parent_id"));
            String gc_img = cursor.getString(cursor
                    .getColumnIndex("gc_img"));
            int gc_sort = cursor.getInt(cursor.getColumnIndex("gc_sort"));
            ClassifyMessage scm = new ClassifyMessage(gc_id, gc_name,
                    gc_parent_id, gc_sort, gc_img);
            list.add(scm);
        }
        cursor.close();
        db.close();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }
}
