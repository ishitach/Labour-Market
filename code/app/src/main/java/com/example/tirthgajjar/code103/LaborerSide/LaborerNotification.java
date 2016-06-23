package com.example.tirthgajjar.code103.LaborerSide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tirthgajjar.code103.ContractorSide.Adapters.Laborer;
import com.example.tirthgajjar.code103.ContractorSide.Adapters.Notifications.NotificationData;
import com.example.tirthgajjar.code103.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tirth Gajjar on 3/28/2016.
 */
public class  LaborerNotification extends Fragment {

    private static final String TAG = "RecyclerViewFragment";

    private static final int DATASET_COUNT =100 ;
    private static final int TYPE1=0,TYPE2=1,TYPE3=2;
    public static List<String> myNotifications=new ArrayList<>();


    protected RecyclerView mRecyclerView;
    protected NotificationData mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    public LaborerNotification() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.notification, container, false);

        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.MyRecyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);

        int notificationTypes[]={TYPE1,TYPE2,TYPE3};


        mAdapter = new NotificationData();
        // Set NotificationData as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private void initDataset() {
        mDataset = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset[i] = "This is element #" + i;
        }
    }

}
