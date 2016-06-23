package com.example.tirthgajjar.code103.ContractorSide.Swipe_tabs.swipetab_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tirthgajjar.code103.ContractorSide.AppController;
import com.example.tirthgajjar.code103.ContractorSide.utils.Const;
import com.example.tirthgajjar.code103.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Profile extends Fragment {

    TextView phoneNum, Name, Village, LicenceNo;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.profile, container, false);

        phoneNum = (TextView)rootView.findViewById(R.id.Phone);
        Name = (TextView)rootView.findViewById(R.id.Name);
        Village = (TextView)rootView.findViewById(R.id.Village);
        LicenceNo = (TextView)rootView.findViewById(R.id.LicenceNo);

        makeJsonArryReq();
        return rootView;

    }

    private void makeJsonArryReq() {
        //     showProgressDialog();

        String URL_PROFILE_C="http://192.168.201.1/webservice/cprofile.php?phone_num_c="+Const.USER_ID;
        System.out.print("Tirth: " + Const.URL_JSON_ARRAY);
        Log.d("tirth", URL_PROFILE_C);
        JsonArrayRequest req = new JsonArrayRequest(URL_PROFILE_C,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        try {
                            if (response.length() > 0) {

                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonObject = response.getJSONObject(i);

                                    if (!jsonObject.isNull("user_name_c")) {

                                        Name.setText(jsonObject.getString("user_name_c"));
                                    }
                                    if (!jsonObject.isNull("phone_num_c")) {
                                       phoneNum.setText(jsonObject.getString("phone_num_c"));
                                    }
                                    if (!jsonObject.isNull("village_name_c")){
                                        Village.setText(jsonObject.getString("village_name_c"));
                                    }
                                    if (!jsonObject.isNull("license_num_c")){

                                        LicenceNo.setText(jsonObject.getString("license_num_c"));
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

}
