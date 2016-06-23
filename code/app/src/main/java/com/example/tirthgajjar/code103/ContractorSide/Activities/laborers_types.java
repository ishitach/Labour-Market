package com.example.tirthgajjar.code103.ContractorSide.Activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.tirthgajjar.code103.ContractorSide.Adapters.Expandable_listview_adapters.workSkillsAdapter;
import com.example.tirthgajjar.code103.R;
import com.example.tirthgajjar.code103.ContractorSide.laborers_lists.A_laborers_list;
import com.example.tirthgajjar.code103.ContractorSide.laborers_lists.C_laborers_list;
import com.example.tirthgajjar.code103.ContractorSide.laborers_lists.O_laborers_list;
import com.example.tirthgajjar.code103.ContractorSide.Adapters.Expandable_listview_adapters.work_skills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class laborers_types extends AppCompatActivity {

    HashMap<String, List<String>> work_category;

    List<String> work_list;
    ExpandableListView expandableListView;

    private RecyclerView recyclerView;

    workSkillsAdapter workSkillsAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laborers_types);
        expandableListView = (ExpandableListView) findViewById(R.id.expanded_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        work_category = work_skills.getSkills();
        work_list = new ArrayList<String>(work_category.keySet());

        workSkillsAdapter1 = new workSkillsAdapter(this, work_category, work_list);

        expandableListView.setAdapter(workSkillsAdapter1);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                Bundle bundle = new Bundle();
                bundle.putInt("StartedFrom", childPosition);
                switch (groupPosition) {
                    case 0:
                        startActivity(new Intent(getApplicationContext(), C_laborers_list.class), bundle);

                        break;
                    case 1:

                        startActivity(new Intent(getApplicationContext(), A_laborers_list.class), bundle);

                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(), O_laborers_list.class), bundle);

                        break;
                    default:
                }

                return true;
            }
        });


 /*       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
  */


    }




}
