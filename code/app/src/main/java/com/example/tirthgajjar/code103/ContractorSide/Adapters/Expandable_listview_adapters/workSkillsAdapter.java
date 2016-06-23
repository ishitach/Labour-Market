package com.example.tirthgajjar.code103.ContractorSide.Adapters.Expandable_listview_adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.tirthgajjar.code103.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Tirth Gajjar on 3/25/2016.
 */
public class workSkillsAdapter extends BaseExpandableListAdapter {

    private Context ctx;
    private HashMap<String,List<String>> workSkills_category;
    private List<String> skills_list;

    public workSkillsAdapter(Context ctx, HashMap<String,List<String>> tempHash,List<String> tempList){
        this.ctx=ctx;
        this.workSkills_category=tempHash;
        this.skills_list=tempList;

    }
    @Override
    public int getGroupCount() {
        return workSkills_category.size();
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return workSkills_category.get(skills_list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return skills_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return workSkills_category.get(skills_list.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        String group_title = (String)getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_exlist_layout,parent,false);
        }
        TextView header=(TextView)convertView.findViewById(R.id.header_exlist);
        header.setTypeface(null, Typeface.BOLD);
        header.setText(group_title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String child_title = (String)getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_exlist_layout,parent,false);
        }
        TextView child_textView=(TextView)convertView.findViewById(R.id.child_exlist);
        child_textView.setText(child_title);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
