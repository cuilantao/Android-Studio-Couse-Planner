package com.example.gift;

import android.os.HardwarePropertiesManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.lang.String;
import java.util.Set;

public class timetable {
    public timetable(course_manager courses){
        HashMap<String, HashMap<String, ArrayList<Integer>>> code = new HashMap<>();
        for (int i=0; i<courses.get_list().size(); i++){
            String course_name = courses.get_list().get(i).name;
            if (!(code.containsKey(course_name))){
                HashMap<String,ArrayList<Integer>> tmp = new HashMap<>();
                ArrayList<Integer> tmp1 = new ArrayList<>();
                fill_array(tmp1, courses.get_list().get(i).time);
                tmp.put(courses.get_list().get(i).lecture_code, tmp1);
                code.put(course_name, tmp);
            }
            else{
                ArrayList<Integer> tmp1 = new ArrayList<>();
                fill_array(tmp1, courses.get_list().get(i).time);
                code.get(course_name).put(courses.get_list().get(i).lecture_code, tmp1);
            }
        }
        System.out.println(code.get("CSC165").keySet());
        System.out.println(code.get("CSC148").keySet());
        for (int i=0; i<code.get("CSC165").get("0101").size();i++){
            System.out.println(code.get("CSC165").get("0101").get(i));
        }
        for (int i=0; i<code.get("CSC165").get("0201").size();i++){
            System.out.println(code.get("CSC165").get("0201").get(i));
        }
        for (int i=0; i<code.get("CSC148").get("0101").size();i++){
            System.out.println(code.get("CSC148").get("0101").get(i));
        }
    }

    public void fill_array(ArrayList<Integer> tmp1, HashMap<String, String> course_time){
        Iterator<String> w = course_time.keySet().iterator();
        while (w.hasNext()){
            tranform(course_time.get(w.next()), tmp1);
        }
    }
    public void tranform(String time, ArrayList<Integer> tmp1){
        int slice = time.indexOf(":");
        String sub1 = time.substring(0, slice);
        Integer start = Integer.parseInt(sub1);
        int middle = time.indexOf("-");
        int slice2 = time.lastIndexOf(":");
        String sub2 = time.substring(middle+1, slice2);
        Integer end = Integer.parseInt(sub2);
        tmp1.add(start);
        tmp1.add(end);
    }
/*    public boolean append_course(course A){
        if (detect_collision(A)){
            return false;
        }
        return true;
    }*/
 /*   private boolean detect_collision(course A){
        Set<String> time = A.time.keySet();

    }*/
}
