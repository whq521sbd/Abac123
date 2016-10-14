package com.aotuo.vegetable.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AreaDBOpenHelper extends SQLiteOpenHelper {
    private final static int VERSION = 1;

    public AreaDBOpenHelper(Context context, String name,
                            CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public AreaDBOpenHelper(Context context, String name, CursorFactory factory) {
        this(context, name, null, VERSION);
    }

    public AreaDBOpenHelper(Context context, String name) {
        this(context, name, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS area(_id integer primary key autoincrement,gc_id text,gc_name text,gc_parent_id text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS area");
        onCreate(db);
    }

}
