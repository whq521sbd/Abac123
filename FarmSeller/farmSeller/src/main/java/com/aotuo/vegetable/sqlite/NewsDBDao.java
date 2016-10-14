package com.aotuo.vegetable.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.aotuo.vegetable.hx.MessageData;
import com.aotuo.vegetable.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class NewsDBDao {
    private NewsDBOpenHelper dbOpenHelper = null;

    public NewsDBDao(Context context) {
        dbOpenHelper = new NewsDBOpenHelper(context, "mynews.db");
    }

    /**
     * {ID="",SendID="",RecID="",Content="",Time="",SendName=""}
     * 添加类别
     */
    public void addNews(List<MessageData> newsData) {
        try {
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            String sql = "insert into mynews(ID,SendID,RecID,Content,Time,SendName,isSend,chatter) values(?,?,?,?,?,?,?,?)";
            db.beginTransaction();

            SQLiteStatement stmt = db.compileStatement(sql);
            for (MessageData md : newsData) {

                stmt.bindString(1, md.getID());
                stmt.bindString(2, md.getSendID());
                stmt.bindString(3, md.getRecID());
                stmt.bindString(4, md.getContent());
                stmt.bindString(5, md.getTime());
                stmt.bindString(6, md.getSendName());
                if(!StringUtils.isEmpty(md.getIsSend()))
                    stmt.bindString(7, md.getIsSend());
                else
                    stmt.bindString(7, "false");
                stmt.bindString(8, md.getSendID());

                stmt.execute();
                stmt.clearBindings();
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加发送数据
     * @param newsData
     */
    public void addSendNews(String chatter, MessageData newsData) {

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        String sql = "insert into mynews(ID,SendID,RecID,Content,Time,SendName,isSend,chatter) values(?,?,?,?,?,?,?,?)";
        db.beginTransaction();

        SQLiteStatement stmt = db.compileStatement(sql);

        stmt.bindString(1, newsData.getID());
        stmt.bindString(2, newsData.getSendID());
        stmt.bindString(3, newsData.getRecID());
        stmt.bindString(4, newsData.getContent());
        stmt.bindString(5, newsData.getTime());
        stmt.bindString(6, newsData.getSendName());
        if(!StringUtils.isEmpty(newsData.getIsSend()))
            stmt.bindString(7, newsData.getIsSend());
        else
            stmt.bindString(7, "false");
        stmt.bindString(8, chatter!=null?chatter:"");

        stmt.execute();
        stmt.clearBindings();

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    /**
     * 查询所有类别
     */
    public List<MessageData> getAllNews(String chatter) {
        List<MessageData> list = new ArrayList<MessageData>();
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("mynews", new String[]{"ID",
                "SendID", "RecID", "Content", "Time", "SendName", "isSend"}, null, null, null, null, "Time asc", null);
        while (cursor.moveToNext()) {
            String ID = cursor.getString(cursor.getColumnIndex("ID"));
            String SendID = cursor.getString(cursor.getColumnIndex("SendID"));
            String RecID = cursor.getString(cursor.getColumnIndex("RecID"));
            String Content = cursor.getString(cursor.getColumnIndex("Content"));
            String Time = cursor.getString(cursor.getColumnIndex("Time"));
            String SendName = cursor.getString(cursor.getColumnIndex("SendName"));
            String isSend = cursor.getString(cursor.getColumnIndex("isSend"));

            MessageData md = new MessageData();
            md.setID(ID);
            md.setSendID(SendID);
            md.setRecID(RecID);
            md.setContent(Content);
            md.setTime(Time);
            md.setSendName(SendName);
            md.setIsSend(isSend);
            list.add(md);
        }
        cursor.close();
        db.close();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * 查询所有类别
     */
    public List<MessageData> getCurrNews(String rec) {
        List<MessageData> list = new ArrayList<MessageData>();
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("mynews", new String[]{"ID",
                "SendID", "RecID", "Content", "Time", "SendName", "isSend"},
                "chatter=?",
                new String[]{rec},
                null, null, "Time asc", null);
        while (cursor.moveToNext()) {
            String ID = cursor.getString(cursor.getColumnIndex("ID"));
            String SendID = cursor.getString(cursor.getColumnIndex("SendID"));
            String RecID = cursor.getString(cursor.getColumnIndex("RecID"));
            String Content = cursor.getString(cursor.getColumnIndex("Content"));
            String Time = cursor.getString(cursor.getColumnIndex("Time"));
            String SendName = cursor.getString(cursor.getColumnIndex("SendName"));
            String isSend = cursor.getString(cursor.getColumnIndex("isSend"));

            MessageData md = new MessageData();
            md.setID(ID);
            md.setSendID(SendID);
            md.setRecID(RecID);
            md.setContent(Content);
            md.setTime(Time);
            md.setSendName(SendName);
            md.setIsSend(isSend);
            list.add(md);
            if(list.size()>100)
                list.remove(0);
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
            db.delete("mynews", null, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

}
