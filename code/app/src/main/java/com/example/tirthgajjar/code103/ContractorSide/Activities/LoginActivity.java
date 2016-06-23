package com.example.tirthgajjar.code103.ContractorSide.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.tirthgajjar.code103.ContractorSide.AppController;
import com.example.tirthgajjar.code103.ContractorSide.ContractorSignUp;
import com.example.tirthgajjar.code103.ContractorSide.utils.Const;
import com.example.tirthgajjar.code103.LaborerSide.SwipeTabLaborer;
import com.example.tirthgajjar.code103.R;
import com.example.tirthgajjar.code103.ContractorSide.Swipe_tabs.SwipeTab;
import com.example.tirthgajjar.code103.SessionManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * A login screen that offers login via email/password.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LoginActivity extends AppCompatActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
  // private UserLoginTask mAuthTask = null;

    private EditText mPhoneNumView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private ProgressDialog progressDialog;
    private Dialog dialog;
    SessionManager sessionManager;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessionManager = new SessionManager(getApplicationContext());

        mPhoneNumView = (EditText) findViewById(R.id.phoneNum);
        mPasswordView = (EditText) findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);
    //    progressDialog.setMessage("Loading...");
    //    progressDialog.setCancelable(false);

        dialog= new Dialog(this);



       mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
               if (id == R.id.login || id == EditorInfo.IME_NULL) {
                   attemptLogin(mPhoneNumView.getText().toString(), mPasswordView.getText().toString());

                   return true;
               }
               return false;
           }
       });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(mPhoneNumView.getText().toString(), mPasswordView.getText().toString());

                //
            }
        });

        Button mRegisterOption = (Button) findViewById(R.id.register_option);
        mRegisterOption.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, SignUpLabourer.class));
            }
        });
        Button mRegisterContractor = (Button) findViewById(R.id.register_Con);
        mRegisterContractor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, ContractorSignUp.class));
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin(String phoneNumTemp, String passwordTemp) {
   /*     if (mAuthTask != null) {
            return;
        }
*/
        // Reset errors.

        mPasswordView.setError(null);
        mPhoneNumView.setError(null);


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordTemp) && !isPasswordValid(passwordTemp)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(phoneNumTemp)) {
            mPhoneNumView.setError(getString(R.string.error_field_required));
            focusView = mPhoneNumView;
            cancel = true;
        } else if (!isPhoneNumValid(phoneNumTemp)) {
            //mPhoneNumView.setError(getString(R.string.error_invalid_phoneNumber));
            focusView = mPhoneNumView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
           // showProgress(true);
       //     mAuthTask = new UserLoginTask(this);
       //     mAuthTask.execute(phoneNumTemp, passwordTemp);
            makeStringReq();
        }
    }

    private boolean isPhoneNumValid(String mPhoneNum) {
        if (mPhoneNum.length() != 10) {
            mPhoneNumView.setError(getString(R.string.error_short_phoneNumber));
            return false;
        }


        return true;


    }



    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


/*
    /**
     * Shows the progress UI and hides the login form.
     */
  /*  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

*/
    /**
     * Use an AsyncTask to fetch the user's email addresses on a background thread, and update
     * the email text field with results on the main UI thread.
     * <p/>
     * <p/>
     * /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */

    private void makeStringReq() {

       // progressDialog.show();


        String URL_LOGIN = "http://192.168.201.1/webservice/login.php?phone_num="+mPhoneNumView.getText().toString()+"&password="+mPasswordView.getText().toString();
        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL_LOGIN, new Response.Listener<String>() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(String response) {

            //    progressDialog.hide();
                if(response.equals("Contractor")){


                    sessionManager.createLoginSession(mPhoneNumView.getText().toString(),"Contractor");
                    startActivity(new Intent(getApplicationContext(),SwipeTab.class));
                    finish();

                }else if(response.equals("Laborer")){
                    sessionManager.createLoginSession(mPhoneNumView.getText().toString(),"Laborer");
                    startActivity(new Intent(getApplicationContext(), SwipeTabLaborer.class));
                    finish();

                }else {

                    Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Please check your internet connection", Toast.LENGTH_SHORT).show();

            //    progressDialog.hide();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);

    }

}

