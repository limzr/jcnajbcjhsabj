package com.example.lim.todolist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class ViewTaskActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        Intent intent = getIntent();
        Task task = (Task)intent.getSerializableExtra(TaskListActivity.EXTRA_TASK);
        TextView taskName = (TextView)findViewById(R.id.viewName);
        TextView taskDate = (TextView)findViewById(R.id.viewDate);
        TextView taskTime = (TextView)findViewById(R.id.viewTime);
        taskName.append(task.getTaskName());
        taskDate.append(task.getTaskDate());
        taskTime.append(task.getTaskTime());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            editTask();
        }

        return super.onOptionsItemSelected(item);
    }

    public void editTask(){
        Intent intent = new Intent(this,EditTaskActivity.class);
        startActivity(intent);
    }
}
