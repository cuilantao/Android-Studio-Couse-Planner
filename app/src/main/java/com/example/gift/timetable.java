package com.example.gift;

import java.util.ArrayList;
import java.util.HashMap;
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
    private static timetable single_instance = null;
    public static timetable get_instance(){
        if (single_instance == null){
            single_instance = new timetable();
        }
        return single_instance;
    }
    public boolean append_course(course A){
        if (detect_collision(A)){
            return false;
        }
        return true;
    }
    private boolean detect_collision(course A){
        Set<String> time = A.time.keySet();

    }

}
