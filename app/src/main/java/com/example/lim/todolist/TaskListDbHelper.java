package com.example.lim.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class TaskListDbHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "taskDB";
    SQLiteDatabase db;
    int rowID;

    String[] projection = {
            TaskContract.TaskEntry._ID,
            TaskContract.TaskEntry.COLUMN_TASK_NAME,
            TaskContract.TaskEntry.COLUMN_TASK_DATE,
            TaskContract.TaskEntry.COLUMN_TASK_TIME,
            TaskContract.TaskEntry.COLUMN_NOTIFICATION
    };
    String selection = TaskContract.TaskEntry._ID + " LIKE ? ";
    String[] selectionArgs = {String.valueOf(rowID)};
    String sortOrder = TaskContract.TaskEntry.COLUMN_TASK_DATE;


    TaskListDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TaskContract.TaskEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(TaskContract.TaskEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long addTask(String name, String date, String time, String notification,SQLiteDatabase sqlDb){

        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_TASK_NAME, name);
        values.put(TaskContract.TaskEntry.COLUMN_TASK_DATE, date);
        values.put(TaskContract.TaskEntry.COLUMN_TASK_TIME, time);
        values.put(TaskContract.TaskEntry.COLUMN_NOTIFICATION, notification);
        long rowID = sqlDb.insert(TaskContract.TaskEntry.TABLE_NAME, null, values);
        return rowID;
    }

    public long updateTask(String name, String date, String time, String notification,SQLiteDatabase sqlDb){

        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_TASK_NAME, name);
        values.put(TaskContract.TaskEntry.COLUMN_TASK_DATE, date);
        values.put(TaskContract.TaskEntry.COLUMN_TASK_TIME, time);
        values.put(TaskContract.TaskEntry.COLUMN_NOTIFICATION, notification);
        long rowID = sqlDb.insert(TaskContract.TaskEntry.TABLE_NAME, null, values);
        return rowID;
    }



    public Cursor getAllTask(){
        Cursor c = db.query(TaskContract.TaskEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,null,
                sortOrder);

        return c;
    }

}
