package com.aotuo.vegetable.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class NewsDBOpenHelper extends SQLiteOpenHelper {
    private final static int VERSION = 1;

    public NewsDBOpenHelper(Context context, String name,
                            CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public NewsDBOpenHelper(Context context, String name, CursorFactory factory) {
        this(context, name, null, VERSION);
    }

    public NewsDBOpenHelper(Context context, String name) {
        this(context, name, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS mynews(_id integer primary key autoincrement,ID text,SendID text,RecID text,Content text,Time text,SendName text,isSend text,chatter text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS mynews");
        onCreate(db);
    }

}
