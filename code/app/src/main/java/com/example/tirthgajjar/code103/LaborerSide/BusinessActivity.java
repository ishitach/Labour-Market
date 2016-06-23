package com.example.tirthgajjar.code103.LaborerSide;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.tirthgajjar.code103.R;

import java.util.Calendar;

public class BusinessActivity extends AppCompatActivity {
    Button StartDateButton, EndDateButton;
    TextView StartDate, EndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        StartDateButton = (Button) findViewById(R.id.StartButton);
        EndDateButton = (Button) findViewById(R.id.EndBuuton);
        StartDate = (TextView) findViewById(R.id.StartDateText);
        EndDate = (TextView) findViewById(R.id.EndDateText);

        final Bundle bundle = new Bundle();


        StartDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment dialogfragment = new DatePickerDialogClass();
                bundle.putString("Type", "StartDateArg");
                dialogfragment.setArguments(bundle);
                dialogfragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });
        EndDateButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {

                DialogFragment dialogfragment = new DatePickerDialogClass();
                bundle.putString("Type", "EndDateArg");
                dialogfragment.setArguments(bundle);
                dialogfragment.show(getFragmentManager(), "Date Picker Dialog");

            }
        });
    }

    public static class DatePickerDialogClass extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        String type;

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            if (getArguments() != null) {
                type = getArguments().getString("Type");

            }
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_DEVICE_DEFAULT_DARK, this, year, month, day);
            return datepickerdialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            if (type.equals("StartDateArg")) {

                TextView textview = (TextView) getActivity().findViewById(R.id.StartDateText);
                textview.setText(day + "/" + (month + 1) + "/" + year);
            }else {
                TextView textview2 = (TextView) getActivity().findViewById(R.id.EndDateText);
                textview2.setText(day + "/" + (month + 1) + "/" + year);
            }

        }
    }

}
