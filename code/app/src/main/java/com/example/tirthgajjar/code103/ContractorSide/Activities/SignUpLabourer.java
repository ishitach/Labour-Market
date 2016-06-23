package com.example.tirthgajjar.code103.ContractorSide.Activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tirthgajjar.code103.ContractorSide.AppController;
import com.example.tirthgajjar.code103.ContractorSide.utils.Const;
import com.example.tirthgajjar.code103.GCM.GCMClientManager;
import com.example.tirthgajjar.code103.R;

public class SignUpLabourer extends AppCompatActivity {

    private String[] GenderSpinnerArray;
    private EditText name, phone_num, age, village, password, ConfirmPassword;
    Spinner GenderSpinner;
    int GenderTemp;
    int inter_cultivation=0,ploughing=0,pesticides=0,crop_seeding=0,plumbing=0,digging=0,flooring_ceiling=0,brick_work=0,other=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_labourer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.GenderSpinnerArray = new String[]{
                getString(R.string.GenderSpinnerItem1), getString(R.string.GenderSpinnerItem2)
        };

        GenderSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapterGender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GenderSpinnerArray);
        GenderSpinner.setAdapter(adapterGender);


        name = (EditText) findViewById(R.id.NameSignUp);
        phone_num = (EditText) findViewById(R.id.SignUpphoneNum_Lab);
        age = (EditText) findViewById(R.id.ageSignUp_Lab);
        village = (EditText) findViewById(R.id.villageSignUp_Lab);
        password = (EditText) findViewById(R.id.SignUpPassword);
        ConfirmPassword = (EditText) findViewById(R.id.Confirmpassword);

        Button mRegisterOption = (Button) findViewById(R.id.register_button);
        mRegisterOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               valid(name,phone_num,age,village,password);
            }
        });

    }

    public void makeJsonStringRequest() {







        String URL_SIGNUP_LAB = "http://192.168.201.1/webservice/Signup_Laborer.php?name_l="+name.getText().toString()
                +"&phone_num_l="+phone_num.getText().toString()+"&password_l="+password.getText().toString()
                +"&age_l="+age.getText().toString()+"&gender_l="+GenderTemp+"&rating_l=0&village_l="+village.getText().toString()+
                "&inter_cultivation="+inter_cultivation+"&ploughing="+ploughing+"&pesticides="+pesticides+"&crop_seeding="+crop_seeding+
                "&plumbing="+plumbing+"&digging="+digging+"&flooring_ceiling="+flooring_ceiling+"&brick_work="+brick_work+"&other="+other;
        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL_SIGNUP_LAB, new Response.Listener<String>() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(String response) {

                //    progressDialog.hide();
                if (response.trim().equals("Success")) {




                    Log.d("response","gone");
                    Toast.makeText(getApplicationContext(), response.trim(), Toast.LENGTH_SHORT).show();
                    GCMClientManager pushClientManager = new GCMClientManager(SignUpLabourer.this, Const.PROJECT_NUMBER);
                    pushClientManager.registerIfNeeded(new GCMClientManager.RegistrationCompletedHandler() {
                        @Override
                        public void onSuccess(String registrationId, boolean isNewRegistration) {

                            Const.REGISTRATION_TOKEN = registrationId;
                            Log.d("Registration id", registrationId);
                            //send this registrationId to your server
                        }

                        @Override
                        public void onFailure(String ex) {
                            super.onFailure(ex);
                        }
                    });
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();


                } else if(response.equals("User_Exists")) {



                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(), response.trim(), Toast.LENGTH_LONG).show();
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
    public void checkBoxClicked(View view) {
        // action for checkbox click
        switch (view.getId()) {
            case R.id.checkBox1:
                inter_cultivation=1;
                break;
            case R.id.checkBox2:
                ploughing=1;
                break;
            case R.id.checkBox3:
                pesticides=1;
                break;
            case R.id.checkBox4:
                crop_seeding=1;
                break;
            case R.id.checkBox5:
                plumbing=1;
                break;
            case R.id.checkBox6:
                digging=1;
                break;
            case R.id.checkBox7:
                flooring_ceiling=1;
                break;
            case R.id.checkBox8:
                brick_work=1;
                break;
            case R.id.checkBox9:
                other=1;
                break;
        }
    }


    public void valid(EditText name,EditText phone_num,EditText age,EditText village,EditText password) {
        boolean result = true;
        if (TextUtils.isEmpty(name.getText())) {
            name.setError("This filed is required");
            result = false;
        }
        if (TextUtils.isEmpty(phone_num.getText())) {
            phone_num.setError("This filed is required");
            result = false;
        } else if (phone_num.getText().length() != 10) {

            phone_num.setError("Phone number should of 10 digits, Please correct it :) :)");
            result = false;
        }

        if (TextUtils.isEmpty(age.getText())) {
            age.setError("This filed is required");
            result = false;
        } else if (Integer.parseInt(age.getText().toString()) < 18) {
            result = false;
            age.setError("You age must be more than 18 years to work as per Government rules..So come back when you are 18 :)");
        }

        if (TextUtils.isEmpty(village.getText())) {
            village.setError("This filed is required");
            result = false;
        }
        if (TextUtils.isEmpty(password.getText())) {
            password.setError("This filed is required");
            result = false;
        } else if (password.getText().length() < 8) {
            password.setError("Password length should be at least 8 characters");
            result = false;
        } else if (TextUtils.isEmpty(ConfirmPassword.getText())) {
            ConfirmPassword.setError("You must confirm your password");
        } else if (!ConfirmPassword.getText().toString().equals(password.getText().toString())) {
            ConfirmPassword.setError("This must match with password");
            result = false;
        }

        if(GenderSpinner.getSelectedItem().toString().equals("Male")){
            GenderTemp=1;
        }else {
            GenderTemp=0;
        }

        if(result==true){
            makeJsonStringRequest();
        }
    }



}
