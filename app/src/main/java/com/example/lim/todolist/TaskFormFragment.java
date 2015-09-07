package com.example.lim.todolist;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskFormFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private String mParam1;
    private String mParam2;
    private String mParam3;
    EditText editDate;
    EditText editTime;
    Button btnSave;
    private OnFragmentInteractionListener mListener;

    public static TaskFormFragment newInstance(String param1, String param2, String param3) {
        TaskFormFragment fragment = new TaskFormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    public TaskFormFragment() {
    }

    ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_form, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void showDatePickerDialog();
        public void showTimePickerDialog();
        public void saveTask(String title, String date, String time, String notification);
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);
        editDate = (EditText) getActivity().findViewById(R.id.edit_Date);
        editTime = (EditText) getActivity().findViewById(R.id.edit_Time);
        btnSave = (Button) getActivity().findViewById(R.id.button_save);
        editDate.setOnClickListener(this);
        editTime.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == editDate)
            mListener.showDatePickerDialog();
        else if(view == editTime)
            mListener.showTimePickerDialog();
        else if (view == btnSave) {
            EditText editEventTitle = (EditText) getActivity().findViewById(R.id.taskName);
            //EditText editDate = (EditText) getActivity().findViewById(R.id.edit_Date);
            CheckBox notification = (CheckBox) getActivity().findViewById(R.id.check_notify);
            String eventTitle = editEventTitle.getText().toString();
            String eventDate = editDate.getText().toString();
            String eventTime = editTime.getText().toString();
            String checkedNotification;
            if (notification.isChecked())
                checkedNotification = "TRUE";
            else checkedNotification = "FALSE";
            mListener.saveTask(eventTitle, eventDate, eventTime, checkedNotification);
        }
        ;
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            month+=1;
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            String date = day + "/" + month + "/" + year;
            EditText editText = (EditText) getActivity().findViewById(R.id.edit_Date);
            editText.setText(date);
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            EditText editText2 = (EditText) getActivity().findViewById(R.id.edit_Time);
            String timeString;
            if(hourOfDay==0 && minute<10)
                timeString = "0" + hourOfDay+":0"+minute; // <-- just for example, you'll want to do better time-formatting here.
            else if(minute<10)
                timeString = "" + hourOfDay+":0"+minute;
            else if(hourOfDay<10)
                timeString = "0" + hourOfDay+":"+minute;
            else
                timeString = "" + hourOfDay+":"+minute;
            editText2.setText(timeString);
            Toast toast = Toast.makeText(getActivity(),
                    "Time set",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}


