package com.example.pr18;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {

    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "mytab";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMG = "img";
    public static final String COLUMN_TXT = "txt";

    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public DB(Context context) {
        this.context = context;
    }

    public void open() {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public Cursor getAllData() {
        return db.query(DB_TABLE, null, null, null, null, null, null);
    }

    public void addRec(String text, int img) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TXT, text);
        cv.put(COLUMN_IMG, img);
        db.insert(DB_TABLE, null, cv);
    }

    public void delRec(long id) {
        db.delete(DB_TABLE, COLUMN_ID + "=" + id, null);
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    "CREATE TABLE " + DB_TABLE + "(" +
                            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            COLUMN_IMG + " INTEGER, " +
                            COLUMN_TXT + " TEXT);"
            );

            for (int i = 1; i <= 4; i++) {
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_TXT, "sometext " + i);
                cv.put(COLUMN_IMG, android.R.drawable.ic_menu_gallery);
                db.insert(DB_TABLE, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    }
}