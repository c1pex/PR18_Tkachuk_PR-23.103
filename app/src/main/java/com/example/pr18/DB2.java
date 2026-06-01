package com.example.pr18;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DB2 {
    private static final String DB_NAME = "mydb2";
    private static final int DB_VERSION = 1;

    private static final String COMPANY_TABLE = "company";
    public static final String COMPANY_ID = "_id";
    public static final String COMPANY_NAME = "name";

    private static final String PHONE_TABLE = "phone";
    public static final String PHONE_ID = "_id";
    public static final String PHONE_NAME = "name";
    public static final String PHONE_COMPANY = "company";

    private SQLiteDatabase db;
    private DBHelper helper;

    private Context ctx;

    public DB2(Context ctx) {
        this.ctx = ctx;
    }

    public void open() {
        helper = new DBHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public Cursor getCompanyData() {
        return db.query(COMPANY_TABLE, null, null, null, null, null, null);
    }

    public Cursor getPhones(long companyId) {
        return db.query(PHONE_TABLE, null,
                PHONE_COMPANY + "=" + companyId,
                null, null, null, null);
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE company(_id INTEGER PRIMARY KEY, name TEXT);");
            db.execSQL("CREATE TABLE phone(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, company INTEGER);");

            ContentValues cv = new ContentValues();

            String[] companies = {"HTC", "Samsung", "LG"};

            for (int i = 0; i < companies.length; i++) {
                cv.clear();
                cv.put("_id", i + 1);
                cv.put("name", companies[i]);
                db.insert("company", null, cv);
            }

            String[][] phones = {
                    {"Desire", "Wildfire"},
                    {"Galaxy S", "Note"},
                    {"G2", "Nexus"}
            };

            for (int i = 0; i < phones.length; i++) {
                for (String phone : phones[i]) {
                    cv.clear();
                    cv.put("name", phone);
                    cv.put("company", i + 1);
                    db.insert("phone", null, cv);
                }
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    }
}
