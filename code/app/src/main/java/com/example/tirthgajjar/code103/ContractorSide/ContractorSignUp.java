package com.example.tirthgajjar.code103.ContractorSide;

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
import com.example.tirthgajjar.code103.ContractorSide.Activities.LoginActivity;
import com.example.tirthgajjar.code103.ContractorSide.utils.Const;
import com.example.tirthgajjar.code103.GCM.GCMClientManager;
import com.example.tirthgajjar.code103.R;

public class ContractorSignUp extends AppCompatActivity {

    private String[] GenderSpinnerArray;
    Spinner GenderSpinner;
    int GenderTemp;
    private EditText name, phone_num, village, password, ConfirmPassword,Licence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.GenderSpinnerArray = new String[]{
                getString(R.string.GenderSpinnerItem1), getString(R.string.GenderSpinnerItem2)
        };

        GenderSpinner = (Spinner) findViewById(R.id.spinnerCon);
        ArrayAdapter<String> adapterGender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GenderSpinnerArray);
        GenderSpinner.setAdapter(adapterGender);

        name = (EditText) findViewById(R.id.NameSignUpCon);
        phone_num = (EditText) findViewById(R.id.PhoneSignUpCon);

        village = (EditText) findViewById(R.id.villageSignUpCon);
        password = (EditText) findViewById(R.id.SignUpPasswordCon);
        ConfirmPassword = (EditText) findViewById(R.id.ConfirmpasswordCon);
        Licence=(EditText)findViewById(R.id.licenceContractorSignUp);

        Button mRegisterOption = (Button) findViewById(R.id.register_buttonCon);
        mRegisterOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valid(name, phone_num, village, password,Licence);
            }
        });
        ;
    }

    public void valid(EditText name, EditText phone_num, EditText village, EditText password,EditText Licence) {
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
        }if(GenderSpinner.getSelectedItem().toString().equals("Male")){
            GenderTemp=1;
        }else {
            GenderTemp=0;
        }

        if(Licence.getText()==null){
            Licence.setText("");
        }

        if (result == true) {
            makeJsonStringRequest();
        }
    }

    public void makeJsonStringRequest() {


        String URL_SIGNUP_LAB = "http://192.168.201.1/webservice/Signup_Contractor.php?name_c="+name.getText().toString()+"&phone_num_c="+phone_num.getText().toString()+"&password_c="+password.getText().toString()+"&village_c="+village.getText().toString()+"&gender_c="+GenderTemp+"&license_num_c="+Licence.getText().toString();
        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL_SIGNUP_LAB, new Response.Listener<String>() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(String response) {

                //    progressDialog.hide();
                if (response.trim().equals("Success")) {


                    Log.d("response", "gone");
                    Toast.makeText(getApplicationContext(), response.trim(), Toast.LENGTH_SHORT).show();
                    GCMClientManager pushClientManager = new GCMClientManager(ContractorSignUp.this, Const.PROJECT_NUMBER);
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


                } else if (response.equals("User Exists")) {


                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                } else {
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

}
