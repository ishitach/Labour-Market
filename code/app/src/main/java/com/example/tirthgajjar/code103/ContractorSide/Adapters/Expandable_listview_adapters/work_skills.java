package com.example.tirthgajjar.code103.ContractorSide.Adapters.Expandable_listview_adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tirth Gajjar on 3/25/2016.
 */
public class work_skills {

    public static HashMap<String,List<String>> getSkills(){

        HashMap<String,List<String>> skillsOfWork = new HashMap<String,List<String>>();
        List<String> Agricultural_workSkills= new ArrayList<String>();
        Agricultural_workSkills.add("Agricultural skill 1");
        Agricultural_workSkills.add("Agricultural skill 2");
        Agricultural_workSkills.add("Agricultural skill 3");
        Agricultural_workSkills.add("Agricultural skill 4");
        List<String> Construction_workSkills= new ArrayList<String>();
        Construction_workSkills.add("Construction work skill 1");
        Construction_workSkills.add("Construction work skill 2");
        Construction_workSkills.add("Construction work skill 3");
        Construction_workSkills.add("Construction work skill 4");
        List<String> Other_workSkills= new ArrayList<String>();
        Other_workSkills.add("Normal labour work");


        skillsOfWork.put("Agricultural work",Agricultural_workSkills);
        skillsOfWork.put("Construction work",Construction_workSkills);
        skillsOfWork.put("Other work",Other_workSkills);

        return skillsOfWork;
    }
}
