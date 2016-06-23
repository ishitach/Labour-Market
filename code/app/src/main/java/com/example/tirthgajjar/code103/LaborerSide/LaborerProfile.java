package com.example.tirthgajjar.code103.LaborerSide;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tirthgajjar.code103.ContractorSide.Adapters.Laborer;
import com.example.tirthgajjar.code103.ContractorSide.AppController;
import com.example.tirthgajjar.code103.ContractorSide.utils.Const;
import com.example.tirthgajjar.code103.DatePickerFragment;
import com.example.tirthgajjar.code103.DateRangePickerFragment;
import com.example.tirthgajjar.code103.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.GenericDeclaration;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by Tirth Gajjar on 3/28/2016.
 */
public class LaborerProfile extends Fragment  {

    Switch switch1;
    DateRangePickerFragment.OnDateRangeSelectedListener onDateRangeSelectedListener = new DateRangePickerFragment.OnDateRangeSelectedListener() {
        @Override
        public void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
            Log.d("range : ","from: "+startDay+"-"+startMonth+"-"+startYear+" to : "+endDay+"-"+endMonth+"-"+endYear );
        }
    };
    TextView Name,Phone_num,Skill,Age,Gender,Village;
    RatingBar ratingBar;
    String startDate;
    String endDate;


    public LaborerProfile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        makeJsonArryReq();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.laborer_profile, container, false);
        switch1 = (Switch) rootView.findViewById(R.id.switch1);
        Name = (TextView)rootView.findViewById(R.id.Name_L);
        Phone_num = (TextView)rootView.findViewById(R.id.Phone_L);
        Skill = (TextView)rootView.findViewById(R.id.Skill_L);
        Age = (TextView)rootView.findViewById(R.id.Age_L);
        Gender = (TextView)rootView.findViewById(R.id.Gender_L);
        Village = (TextView)rootView.findViewById(R.id.Village_L);
        ratingBar = (RatingBar) rootView.findViewById(R.id.Rating_L);


        switch1.setChecked(true);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                   showDateRangePicker();
                    // /startActivity(new Intent(getActivity(),BusinessActivity.class));
                } else {

                }
            }
        });

        if (switch1.isChecked()) {

        } else {

        }


        return rootView;
    }


    public void showDateRangePicker(){
        DateRangePickerFragment dateRangePickerFragment= DateRangePickerFragment.newInstance(onDateRangeSelectedListener, false);
        dateRangePickerFragment.show(getFragmentManager(),"datePicker");
    }


    private void makeJsonArryReq() {
        //     showProgressDialog();

        String URL_PROFILE_L="http://192.168.201.1/webservice/Lprofile.php?phone_num_l="+ Const.USER_ID;

        Log.d("tirth", URL_PROFILE_L);
        JsonArrayRequest req = new JsonArrayRequest(URL_PROFILE_L,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        try {
                            if (response.length() > 0) {

                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonObject = response.getJSONObject(i);

                                    if (!jsonObject.isNull("user_name_l")) {

                                        Name.setText(jsonObject.getString("user_name_l"));
                                    }
                                    if (!jsonObject.isNull("phone_num_l")) {
                                        Phone_num.setText(jsonObject.getString("phone_num_l"));
                                    }

                                    if (!jsonObject.isNull("age_l")){

                                        Age.setText(jsonObject.getString("age_l"));
                                    }

                                    if (!jsonObject.isNull("gender_l")){

                                        if(jsonObject.getString("gender_l").equals("1")){
                                            Gender.setText("Male");
                                        }else{
                                            Gender.setText("Female");
                                        }
                                    }
                                    if (!jsonObject.isNull("village_l")){
                                        Village.setText(jsonObject.getString("village_l"));
                                    }



                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //  hideProgressDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(req);}

/*
    @Override
    public void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {


        Log.d("range : ","from: "+startDay+"-"+startMonth+"-"+startYear+" to : "+endDay+"-"+endMonth+"-"+endYear );

    }*/
}
