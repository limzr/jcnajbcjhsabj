package com.example.lim.todolist;

import android.provider.BaseColumns;

public class TaskContract {
    public TaskContract(){}

    public  static abstract class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_TASK_NAME = "taskName";
        public static final String COLUMN_TASK_DATE = "taskDate";
        public static final String COLUMN_TASK_TIME = "taskTime";
        public static final String COLUMN_NOTIFICATION = "taskNotification";
        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ", ";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TaskEntry.TABLE_NAME + " (" + TaskEntry._ID+" INTEGER PRIMARY KEY, " +
                        TaskEntry.COLUMN_TASK_NAME + TEXT_TYPE + COMMA_SEP +
                        TaskEntry.COLUMN_TASK_DATE + TEXT_TYPE + COMMA_SEP +
                        TaskEntry.COLUMN_TASK_TIME + TEXT_TYPE + COMMA_SEP +
                        TaskEntry.COLUMN_NOTIFICATION + TEXT_TYPE + " )";
        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME;


    }
}
