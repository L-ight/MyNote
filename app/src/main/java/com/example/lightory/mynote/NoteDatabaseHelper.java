package com.example.lightory.mynote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Lightory on 2016/8/5.
 */
public class NoteDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_TABLE = "create table Notes ("
            + "id integer primary key autoincrement, "
            + "TITLE text, "
            + "CONTENT text, "
            + "DATE text)";
    private Context mContext;
    public NoteDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
