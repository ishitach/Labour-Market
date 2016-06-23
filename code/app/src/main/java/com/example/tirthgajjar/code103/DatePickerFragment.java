package com.example.tirthgajjar.code103;

/**
 * Created by Tirth Gajjar on 3/30/2016.
 */
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;

import android.widget.DatePicker;

import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    TextView startDate;
    TextView endDate;
    String type;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

         startDate = (TextView)getActivity().findViewById(R.id.BusyDateStartText);
       endDate = (TextView)getActivity().findViewById(R.id.BusyDateEndText);

        Bundle args = getArguments();
        type = args.getString("Type");



        //Create a new DatePickerDialog instance and return it
        /*
            DatePickerDialog Public Constructors which support Theme declaration
            public DatePickerDialog (Context context, int theme, DatePickerDialog.OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth)
            Supported Themes are....
                THEME_DEVICE_DEFAULT_DARK
                THEME_DEVICE_DEFAULT_LIGHT
                THEME_HOLO_DARK
                THEME_HOLO_LIGHT
                THEME_TRADITIONAL

         */
        //*********** Just uncomment any one below line to apply another Theme ************
        return new DatePickerDialog(getActivity(),this, year, month, day);
        //return new DatePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, this, year, month, day);
        //return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK, this, year, month, day);
        //return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);
        //return new DatePickerDialog(getActivity(), AlertDialog.THEME_TRADITIONAL, this, year, month, day);
    }






    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        if (type.equals("StartDate")) {
            startDate.setText(dateFormat.format(calendar.getTime()));
        }
        else {
            endDate.setText(dateFormat.format(calendar.getTime()));
        }
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }



}
