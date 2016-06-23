package com.example.tirthgajjar.code103.ContractorSide.laborers_lists;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tirthgajjar.code103.ContractorSide.Adapters.Laborer;
import com.example.tirthgajjar.code103.ContractorSide.Adapters.Laborer_adapter;
import com.example.tirthgajjar.code103.ContractorSide.AppController;
import com.example.tirthgajjar.code103.ContractorSide.utils.Const;
import com.example.tirthgajjar.code103.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class C_laborers_list extends AppCompatActivity {

    private String TAG = C_laborers_list.class.getSimpleName();



    public static List<String> selectedItemList = new ArrayList<>();


    //  private ProgressDialog pDialog=new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
    private List<Laborer> laborerArrayList = new ArrayList<>();
    private Laborer_adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static List<String> selectedLaborers = new ArrayList<>();

    private Boolean MODE = false;


    // These tags will be used to cancel the requests
    String tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_a_laborers_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.LaborersRecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        makeJsonArryReq();
        adapter = new Laborer_adapter(this, laborerArrayList);
        //recyclerView.addOnItemTouchListener(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new A_laborers_list.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Laborer laborer = laborerArrayList.get(position);
                if (MODE) {
                    if (addLaborerToSelection(laborer.getPhone_num())) {
                        view.setBackgroundColor(Color.BLUE);
                    } else {
                        view.setBackgroundColor(Color.RED);
                    }
                }
                Toast.makeText(getApplicationContext(), laborer.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

                Laborer laborer = laborerArrayList.get(position);
                if (!MODE) {
                    MODE = true;
                    if (addLaborerToSelection(laborer.getPhone_num())) {
                        view.setBackgroundColor(Color.BLUE);
                    } else {
                        view.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        }));
        recyclerView.setAdapter(adapter);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private Boolean addLaborerToSelection(String phone_num) {

        if (!selectedLaborers.contains(phone_num)) {
            selectedLaborers.add(phone_num);
            return true;
        } else {
            Iterator<String> i = selectedLaborers.iterator();
            while (i.hasNext()) {
                String listPhone = i.next();
                if (listPhone.equals(phone_num)) {
                    i.remove();
                    return false;
                }
            }
            if (selectedLaborers.size() < 1) {
                MODE = false;

            }
        }

        return false;
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private A_laborers_list.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final A_laborers_list.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {

                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private void makeJsonArryReq() {
        //     showProgressDialog();
        System.out.print("Tirth: " + Const.URL_JSON_ARRAY);
        Log.d("tirth", Const.URL_JSON_ARRAY);
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Laborer laborer;
                        Log.d("tirth", "onresponse");
                        try {
                            if (response.length() > 0) {
                                laborerArrayList.clear();
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    laborer = new Laborer();
                                    if (!jsonObject.isNull("user_name_l")) {
                                        Log.d("user_name", jsonObject.getString("user_name_l"));
                                        laborer.setName(jsonObject.getString("user_name_l"));
                                    }
                                    if (!jsonObject.isNull("phone_num_l")) {
                                        Log.d("user_phone", jsonObject.getString("phone_num_l"));
                                        laborer.setPhone_num(jsonObject.getString("phone_num_l"));
                                    }
                                    if (!jsonObject.isNull("gender_l")) {
                                        if (jsonObject.getString("gender_l").equals("male")) {
                                            laborer.setGender(true);
                                        } else {
                                            laborer.setGender(false);
                                        }
                                        Log.d("GENDER", jsonObject.getString("gender_l"));
                                    }
                                    if (!jsonObject.isNull("age_l")) {
                                        laborer.setAge(jsonObject.getInt("age_l"));
                                    }
                                    if (!jsonObject.isNull("rating_l")) {

                                        laborer.setRating(jsonObject.getInt("rating_l"));
                                    }

                                    if (!jsonObject.isNull("village_l")) {
                                        laborer.setVillageName(jsonObject.getString("village_l"));
                                    }
                                    laborerArrayList.add(i, laborer);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //  hideProgressDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(req);
    }

/*    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }
*/


}
