package com.example.lim.todolist;


import java.io.Serializable;
import java.util.Calendar;

public class Task implements Serializable{
    private String taskName;
    private String taskDate;
    private String taskTime;
    private boolean notification;

    public Task(String taskName,String taskDate, String taskTime, boolean notification){
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.notification = notification;
    }

    public Task(){ this("",null, null, false);}

    public String getTaskName(){return taskName;}
    public String getTaskDate(){return taskDate;}
    public String getTaskTime(){return taskTime;}
    public boolean isNotification(){return notification;}
    public void setTaskName(String taskName){this.taskName = taskName;}
    public void setTaskDate(String taskDate){this.taskDate = taskDate;}
    public void setTaskTime(String taskTime){this.taskTime = taskTime;}
    public void setNotification(boolean notification){this.notification = notification;}
}
