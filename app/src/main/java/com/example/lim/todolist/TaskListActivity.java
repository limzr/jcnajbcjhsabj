package com.example.lim.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class TaskListActivity extends ActionBarActivity {

    public static final String EXTRA_TASK = "com.example.lim.todolist.Task";
    public static final String EXTRA_ID = "com.example.lim.todolist.ItemID";

    int rowID=0;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        ListView taskListView;
        ArrayAdapter<String> listAdapter;
        taskListView = (ListView)findViewById(R.id.listView);
        TaskListDbHelper mDbHelper = new TaskListDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor c = db.query(TaskContract.TaskEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,null,
                sortOrder);
        //db.close();
        ArrayList<String> taskList = new ArrayList<String>();
        String taskName;
        String taskDate;
        String taskTime;
        String notification;
        String [] date;
        Toast.makeText(this, Integer.toString(c.getCount()), Toast.LENGTH_SHORT).show();
        final Task [] tasks = new Task[c.getCount()];
        Calendar calendar = Calendar.getInstance();
        int year,month,day;
        boolean notificationChecked;

        //c.moveToFirst();
        for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
            taskName =c.getString(c.getColumnIndex(TaskContract.TaskEntry.COLUMN_TASK_NAME));
            taskDate =c.getString(c.getColumnIndex(TaskContract.TaskEntry.COLUMN_TASK_DATE));
            taskTime =c.getString(c.getColumnIndex(TaskContract.TaskEntry.COLUMN_TASK_TIME));
            notification=c.getString(c.getColumnIndex(TaskContract.TaskEntry.COLUMN_NOTIFICATION));
            taskList.add(taskName);
            /*date= taskDate.split("/");
            Toast.makeText(this,date[0]+date[1]+date[2],Toast.LENGTH_SHORT).show();
            year=Integer.valueOf(date[2]);
            month = Integer.valueOf(date[1])-1;
            day=Integer.valueOf(date[0]);
            calendar.set(year,month,day);*/
            if(notification=="TRUE")
                notificationChecked=true;
            else
                notificationChecked=false;
            tasks[rowID]=new Task(taskName,taskDate, taskTime, notificationChecked);
            rowID++;
        }
        db.close();
        listAdapter = new ArrayAdapter<String>(this,R.layout.task_detail,taskList);

        taskListView.setAdapter(listAdapter);
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TaskListActivity.this,ViewTaskActivity.class);
                intent.putExtra(EXTRA_TASK,tasks[(int)id]);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_task_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_add_task){
            addTask();
        }

        return super.onOptionsItemSelected(item);
    }

    public void addTask(){
        Intent intent = new Intent(this,AddTaskActivity.class);
        startActivity(intent);
    }
}
