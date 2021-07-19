package com.example.softage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


public class DbActivity {
    myDbHelper h;
    public DbActivity(Context context){
        h = new myDbHelper(context);
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDb";
        private static final String TABLE_NAME = "myTb";
        private static final int DATABASE_VERSION = 3;
        private static final String UID="id";
        private static final String CODE= "employee_code";
        private static final String NAME = "name";
        private static final String EMAIL= "email";
        private static final String PASS= "password";
        private Context context;

        public myDbHelper(Context c) {
            super(c, DATABASE_NAME, null, DATABASE_VERSION);
            this.context =c;
        }

        public void onCreate(SQLiteDatabase db) {
            try{ db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CODE+" TEXT, "+NAME+" TEXT, "+EMAIL+" INTEGER, "+PASS+" TEXT)");}
            catch (Exception e){}
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
                onCreate(db);
            }
            catch (Exception e){}
        }
    }


    public long insert(String n1, String n2, String n3, String n4) {
        SQLiteDatabase db = h.getWritableDatabase();
        ContentValues c = new ContentValues();  c.put(myDbHelper.CODE, n1);  c.put(myDbHelper.NAME, n2);   c.put(myDbHelper.EMAIL, n3);    c.put(myDbHelper.PASS, n4);
        return db.insert(myDbHelper.TABLE_NAME, null , c);
    }

    public Cursor viewData(){
        SQLiteDatabase db= h.getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM "+myDbHelper.TABLE_NAME, null);
        return c;
    }

    public  int delete(String n) {
        SQLiteDatabase db = h.getWritableDatabase();
        String[] s= {n};
        return  db.delete(myDbHelper.TABLE_NAME ,myDbHelper.EMAIL+" = ?", s);
    }

    public int update(String n1, String n2, String n3, String n4) {
        SQLiteDatabase db = h.getWritableDatabase();
        ContentValues c = new ContentValues();  c.put(myDbHelper.CODE,n1);   c.put(myDbHelper.NAME, n2);     c.put(myDbHelper.PASS, n4);
        String[] s= {n3};
        return db.update(myDbHelper.TABLE_NAME,c, myDbHelper.EMAIL+" = ?", s);
    }


    public int enter(String n, String p) {
        SQLiteDatabase db = h.getWritableDatabase();
        ContentValues c = new ContentValues();  c.put(myDbHelper.PASS, p);
        String[] s= {n};
        return db.update(myDbHelper.TABLE_NAME,c, myDbHelper.EMAIL+" = ?", s);
    }

}

