package com.example.liang.memoeri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteManager 
{
    private static final String  DB_NAME="PersonalBackUp.db";
    private static final String  USER_TABLE_NAME="User";
    private static final String  RECORD_TABLE_NAME="Record";
    private static final String  TAB_ID="_id";
    private static final String  TAB_USERNAME="username";
    private static final String  TAB_USERPASSWORD="userpassword";
    private static final String  TAB_TISHI="tishi";
    private static final String  TAB_SUBJECT="subject";
    private static final String  TAB_DATE="date";
    private static final String  TAB_INFORMATION="information";
    private static final String  TAB_FLAG="flag";
    private static final int VERSION=1;
    private Context  mContext=null;
    private static final String  CREATE_USER_TABLE= String.format("CREATE TABLE User (%s autoincreament Integer  primary key   ,%s  varchar(10) ,%s  varchar(16),%s  text)", TAB_ID, TAB_USERNAME, TAB_USERPASSWORD, TAB_TISHI);
    private static final String CREATE_RECORD_TABLE= String.format("CREATE TABLE Record(%s autoincreatement Integer primary key  ,%s  varchar(20),%s  varchar(20) ,%s  text ,%s  varchar(2) )", TAB_ID, TAB_SUBJECT, TAB_DATE, TAB_INFORMATION, TAB_FLAG);
    public SQLiteManager(Context context)
    {
        mContext=context;
    }
    private SQLiteDatabase mDatabase=null;
    private DatabaseHelper mDatabaseHelper=null;
    private class  DatabaseHelper extends SQLiteOpenHelper
    {

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USER_TABLE);
            db.execSQL(CREATE_RECORD_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);

        }
    }
    /*****************open数据库*************************/
    public void open()
    {
        mDatabaseHelper=new DatabaseHelper(mContext);
        mDatabase=mDatabaseHelper.getWritableDatabase();
    }
    /*******************关闭数据库************************************/
    public void close()
    {
        mDatabase.close();
        mDatabaseHelper.close();
    }
    //添加相应的密码
    public void zhuce(Integer index,String username,String userpassword,String tishi)
    {
        ContentValues values=new ContentValues();
        values.put(TAB_ID, index);
        values.put(TAB_USERNAME,username);
        values.put(TAB_USERPASSWORD,userpassword);
        values.put(TAB_TISHI,tishi);
        mDatabase.insert(USER_TABLE_NAME, null, values);
    }
    //登陆的时候验证密码
    public Cursor YanZheng()
    {
        Cursor cursor= mDatabase.query(USER_TABLE_NAME, new String[]{TAB_ID,TAB_USERNAME,TAB_USERPASSWORD,TAB_TISHI}, null, null, null, null, null);
        return cursor;
    }
    //添加记录
    public void addInformation(Integer index,String subject,String date,String neirong,String flag)
    {
        ContentValues values=new ContentValues();
        values.put(TAB_ID, index);
        values.put(TAB_SUBJECT, subject);
        values.put(TAB_DATE, date);
        values.put(TAB_INFORMATION, neirong);
        values.put(TAB_FLAG, flag);
        mDatabase.insert(RECORD_TABLE_NAME, null, values);
    }
    //返回所有记录表格中的数据，用于listview中显示
    public Cursor  getRecord()
    {
        Cursor cursor=mDatabase.query(RECORD_TABLE_NAME, new String[]{TAB_ID,TAB_SUBJECT,TAB_DATE,TAB_INFORMATION,TAB_FLAG}, null, null, null, null, null);
        return cursor;

    }
    //用于在查看单个记录的时候对这一条记录做出的修改功能
    public void updateRecord(Integer index,String subject,String date,String neirong,String flag)
    {
        ContentValues values=new ContentValues();
        values.put(TAB_SUBJECT, subject);
        values.put(TAB_DATE, date);
        values.put(TAB_INFORMATION, neirong);
        values.put(TAB_FLAG, flag);
        mDatabase.update(RECORD_TABLE_NAME, values, "_id="+index, null);
    }
    //用于在查看单个记录的时候对这一条记录做出的删除功能

    public void deleteRecord(int id)
    {
        mDatabase.execSQL("delete from "+RECORD_TABLE_NAME+" where _id="+id);
        //mDatabase.delete(RECORD_TABLE_NAME, " subject="+subject, null);
    }
}
