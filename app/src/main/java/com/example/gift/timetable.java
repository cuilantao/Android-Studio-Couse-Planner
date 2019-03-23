package com.example.gift;

import android.os.HardwarePropertiesManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class timetable {
    private ArrayList<course> monday =  new ArrayList<>();
    private ArrayList<course> tuesday =  new ArrayList<>();
    private ArrayList<course> wednesday =  new ArrayList<>();
    private ArrayList<course> thursday =  new ArrayList<>();
    private ArrayList<course> friday =  new ArrayList<>();
    private ArrayList<course> saturday =  new ArrayList<>();
    private ArrayList<course> sunday =  new ArrayList<>();
    private HashMap<String, ArrayList<course>> coursemap = new HashMap<>();
    public timetable(course_manager courses){
        for (int i=0; i<courses.get_list().size(); i++){
            Iterator<String> w = courses.get_list().get(i).time.keySet().iterator();
            ArrayList<String> tmp = new ArrayList<>();
            while (w.hasNext()){
                tmp.add(w.next());
            }
            AVLNode tmp1 = new AVLNode(courses.get_list().get(i).name, courses.get_list().get(i).lecture_code, tmp);
        }
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
