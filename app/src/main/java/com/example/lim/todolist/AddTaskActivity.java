package com.example.lim.todolist;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class AddTaskActivity extends ActionBarActivity implements TaskFormFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDatePickerDialog() {
        DialogFragment newFragment = new com.example.lim.todolist.TaskFormFragment.DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog() {
        DialogFragment newFragment = new TaskFormFragment.TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void saveTask(String name,String date, String time, String notification){
        TaskListDbHelper mDbHelper = new TaskListDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long newRowId = mDbHelper.addTask(name,date,time, notification,db);
        db.close();
        Toast.makeText(this,"Values inserted column ID is : " + newRowId,Toast.LENGTH_SHORT).show();
    }
}
