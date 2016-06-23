package com.example.tirthgajjar.code103.ContractorSide.Swipe_tabs;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tirthgajjar.code103.ContractorSide.Activities.laborers_types;
import com.example.tirthgajjar.code103.ContractorSide.AppController;
import com.example.tirthgajjar.code103.ContractorSide.Swipe_tabs.swipetab_fragments.Hire;
import com.example.tirthgajjar.code103.ContractorSide.Swipe_tabs.swipetab_fragments.Notification;
import com.example.tirthgajjar.code103.ContractorSide.Swipe_tabs.swipetab_fragments.Profile;
import com.example.tirthgajjar.code103.ContractorSide.utils.Const;
import com.example.tirthgajjar.code103.GCM.GCMClientManager;
import com.example.tirthgajjar.code103.R;
import com.example.tirthgajjar.code103.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SwipeTab extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipetab);


        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetails();

        // name
        Const.USER_ID = user.get(SessionManager.KEY_PHONE);

        // email
        Const.USER_IS = user.get(SessionManager.KEY_USER_TYPE);

        GCMClientManager pushClientManager = new GCMClientManager(this, Const.PROJECT_NUMBER);
        pushClientManager.registerIfNeeded(new GCMClientManager.RegistrationCompletedHandler() {
            @Override
            public void onSuccess(String registrationId, boolean isNewRegistration) {

                Const.REGISTRATION_TOKEN=registrationId;
                Log.d("Registration id", registrationId);
                //send this registrationId to your server
                makeStringReq();
            }

            @Override
            public void onFailure(String ex) {
                super.onFailure(ex);
            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Const.USER_ID= getIntent().getExtras().getString("USER_ID");
        //   Const.USER_IS= getIntent().getExtras().getString("USER_IS");

        onNewIntent(getIntent());

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), laborers_types.class));
            }
        });


    }
    @Override
    public void onNewIntent(Intent intent){
        Bundle extras = intent.getExtras();
        if(extras != null){
            if(extras.containsKey("message"))
            {
               // setContentView(R.layout.viewmain);
                // extract the extra-data in the Notification
                String msg = extras.getString("message");
                Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
            }
        }


    }



    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("profile");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_contacts, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);


        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText(" notification");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_call, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText(" Hire");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_favourite, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);


    }

    /**
     * Adding fragments to ViewPager
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Profile(), "profile");
        adapter.addFrag(new Notification(), "Notification");
        adapter.addFrag(new Hire(), "Hire");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }




    }
    private void makeStringReq() {

        // progressDialog.show();


        String URL_SAVE_TOKEN = "http://192.168.201.1/webservice/SaveToken.php?phone="+Const.USER_ID+"&token="+Const.REGISTRATION_TOKEN;
        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL_SAVE_TOKEN, new Response.Listener<String>() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(String response) {

                //    progressDialog.hide();
                if (response.equals("Success")) {


                } else if (response.equals("Failed")) {

                } else {

                    Toast.makeText(getApplicationContext(),"SENT" , Toast.LENGTH_SHORT).show();
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
