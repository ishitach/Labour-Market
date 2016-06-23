package com.example.tirthgajjar.code103.ContractorSide;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tirthgajjar.code103.ContractorSide.Adapters.Laborer;
import com.example.tirthgajjar.code103.ContractorSide.Adapters.Laborer_adapter;
import com.example.tirthgajjar.code103.ContractorSide.laborers_lists.A_laborers_list;
import com.example.tirthgajjar.code103.ContractorSide.utils.Const;
import com.example.tirthgajjar.code103.DateRangePickerFragment;
import com.example.tirthgajjar.code103.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PickDates extends AppCompatActivity {

     Button pickDates;
    TextView pickedStart,pickedEnd;
    List<Laborer> myList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_dates);

        myList = A_laborers_list.selectedLaborers;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.hiredRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        Laborer_adapter adapter = new Laborer_adapter(this, myList);
        recyclerView.setAdapter(adapter);

        pickDates = (Button) findViewById(R.id.PickDatesButton);
        pickedStart = (TextView) findViewById(R.id.PickedStartDate);
        pickedEnd = (TextView) findViewById(R.id.PickedEndDate);

        pickDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateRangePicker();

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.SendRequestFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                makeStringReq();
            }
        });
    }
    public void showDateRangePicker(){
        DateRangePickerFragment dateRangePickerFragment= DateRangePickerFragment.newInstance(onDateRangeSelectedListener, false);
        dateRangePickerFragment.show(getSupportFragmentManager(),"datePicker");
    }
    DateRangePickerFragment.OnDateRangeSelectedListener onDateRangeSelectedListener = new DateRangePickerFragment.OnDateRangeSelectedListener() {
        @Override
        public void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
            pickedStart.setText(startDay + "/" + startMonth + "/" + startYear);
            pickedEnd.setText(endDay + "/" + endMonth + "/" + endYear);
            Log.d("range : ", "from: " + startDay + "-" + startMonth + "-" + startYear + " to : " + endDay + "-" + endMonth + "-" + endYear);
        }
    };

    public String prepareUrl(List<Laborer> myList){

        String UrlAppender="http://192.168.201.1/webservice/sendRequest.php?phone_num[]="+myList.get(0).getPhone_num();

        StringBuilder stringBuilder = new StringBuilder(UrlAppender);

        for (int i=1;i<myList.size();i++){
            stringBuilder.append("&phone_num[]="+myList.get(i).getPhone_num());
        }

        stringBuilder.append("&pickedStart="+pickedStart.getText());
        stringBuilder.append("&pickedEnd=" + pickedEnd.getText());
        stringBuilder.append("&sender="+Const.USER_ID);


        return UrlAppender;

    }

    private void makeStringReq() {

        // progressDialog.show();


        String URL_LOGIN = prepareUrl(myList);
        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL_LOGIN, new Response.Listener<String>() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(String response) {

                //    progressDialog.hide();
                if (response.equals("Contractor")) {


                } else if (response.equals("Laborer")) {

                } else {

                    Toast.makeText(getApplicationContext(),response.trim(), Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();

                //    progressDialog.hide();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);
    }

}
