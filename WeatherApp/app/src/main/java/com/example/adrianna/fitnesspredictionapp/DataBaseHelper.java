package com.example.adrianna.fitnesspredictionapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;


public class DataBaseHelper extends SQLiteOpenHelper {
    //database version
    private static final int DATABASE_VERSION = 1;
    //database name
    private static final String DATABASE_NAME = "UserManager.database";
    //table name for the users
    private static final String TABLE_USER = "user";
    //columns for the table
    private static final String USER_ID = "user_id";
    private static final String USER_USERSNAME = "user_users_name";
    private static final String USER_NAME = "user_name";
    private static final String USER_PASSWORD = "user_password";
    SQLiteDatabase DB;

    private String createUserTable = "create table " + TABLE_USER + "(" + USER_ID + " integer primary key autoincrement, "
            + USER_USERSNAME + " text not null," + USER_NAME + " text not null," + USER_PASSWORD + " text not null"+")";

    private String dropUserTable = "drop table if exists " + TABLE_USER;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropUserTable);
        onCreate(db);
    }
    public void addUser(User user){
        DB=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(USER_NAME, user.getName());
        values.put(USER_USERSNAME,user.getUserName());
        values.put(USER_PASSWORD,user.getPassword());

        DB.insert(TABLE_USER,null,values);
        DB.close();
    }
    public String searchUser(String userName){
        DB=this.getReadableDatabase();
        String query="Select username and password from "+TABLE_USER;
        Cursor cursor= DB.rawQuery(query, null);
        String user,pass;
        pass="not found";
        if(cursor.moveToFirst()){
            do{
                user=cursor.getString(0);


                if(user.equals(userName)){
                    pass=cursor.getString(1);
                    break;
                }

            }while(cursor.moveToNext());
        }

        return pass;
    }

}
