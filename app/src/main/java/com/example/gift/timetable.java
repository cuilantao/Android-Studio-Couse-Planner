package com.example.gift;

import android.os.HardwarePropertiesManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.lang.String;
import java.util.Set;

public class timetable {
    int[] Monday = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
    int[] Tuesday = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
    int[] Wednesday = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
    int[] Thursday = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
    int[] Friday = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
    public timetable(course_manager courses){
        HashMap<String, HashMap<String, HashMap<String, ArrayList<Integer>>>> code = new HashMap<>();
        for (int i=0; i<courses.get_list().size(); i++){
            String course_name = courses.get_list().get(i).name;
            if (!(code.containsKey(course_name))){
                HashMap<String,HashMap<String,ArrayList<Integer>>> tmp = new HashMap<>();
                HashMap<String, ArrayList<Integer>> weekday_to_time = new HashMap<>();
                ArrayList<Integer> tmp1 = new ArrayList<>();
                fill_array(weekday_to_time, courses.get_list().get(i).time);
                tmp.put(courses.get_list().get(i).lecture_code, weekday_to_time);
                code.put(course_name, tmp);
            }
            else{
                ArrayList<Integer> tmp1 = new ArrayList<>();
                HashMap<String, ArrayList<Integer>> weekday_to_time = new HashMap<>();
                fill_array(weekday_to_time, courses.get_list().get(i).time);
                code.get(course_name).put(courses.get_list().get(i).lecture_code, weekday_to_time);
            }
        }
        Iterator<String> all_course = code.keySet().iterator();
        String first = all_course.next();
        sort(code, first, Monday, Tuesday, Wednesday, Thursday, Friday); // a recursive planning function
    }

    public void fill_array(HashMap<String, ArrayList<Integer>> weekday_to_time, HashMap<String, String> course_time){
        Iterator<String> w = course_time.keySet().iterator();
        while (w.hasNext()){
            ArrayList<Integer> tmp1 = new ArrayList<>();
            String tmp = w.next();
            tranform(course_time.get(tmp), tmp1);
            weekday_to_time.put(tmp, tmp1);
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
    public int sort(HashMap<String, HashMap<String, HashMap<String, ArrayList<Integer>>>> code, String course_name,
                        int[] Monday, int[] Tuesday,
                        int[] Wednesday, int[] Thursday, int[] Friday){
        HashMap<String, HashMap<String, ArrayList<Integer>>> next = code.get(course_name);
        Iterator<String> lecture_codes = next.keySet().iterator();
        while (lecture_codes.hasNext()) {
            String next_lect = lecture_codes.next();
            HashMap<String, ArrayList<Integer>> next_next = next.get(next_lect);
            Iterator<String> time = next_next.keySet().iterator();
            reset(code.get(course_name), next_lect, Monday, Tuesday, Wednesday, Thursday, Friday);//we know the current lecture section
            int[] monday_tmp = Monday.clone();
            int[] tuesday_tmp = Tuesday.clone();
            int[] wednesday_tmp = Wednesday.clone();
            int[] thursday_tmp = Thursday.clone();
            int[] friday_tmp = Friday.clone();
            while (time.hasNext()) {
                String index = time.next();
                ArrayList<Integer> tmp = next_next.get(index);
                int i = tmp.get(0);
                int j = tmp.get(1);
                boolean result;
                if (index == "Monday") {
                    result = set_array(Monday, i, j);
                } else if (index == "Tuesday") {
                    result = set_array(Tuesday, i, j);
                } else if (index == "Wednesday") {
                    result = set_array(Wednesday, i, j);
                } else if (index == "Thursday") {
                    result = set_array(Thursday, i, j);
                } else {
                    result = set_array(Friday, i, j);
                }
                if (!result && !lecture_codes.hasNext()) { //already false and no other lecture sections
                    Monday = monday_tmp;
                    Tuesday = tuesday_tmp;
                    Wednesday = wednesday_tmp;
                    Thursday = thursday_tmp;
                    Friday = friday_tmp;
                    return 0; // recursive going back to the previous unfinished loop
                } else if (result && !time.hasNext()) {// already entered every time for this lecture section and everything seems to be okay, we can skip to the next course
                    String rec_next = get_next_class(code.keySet().iterator(), course_name);
                    if (rec_next == "NULL") {
                        return 1;
                    }
                    sort(code,rec_next, Monday, Tuesday, Wednesday, Thursday, Friday);
                }
            }
        }
        return 1;//should never get to this line;
    }
    public boolean set_array(int[] day, int start, int end) {
        for (int i = start; i<end+1;i++) {
            if (day[i - 9] == 1) {
                return false;
            }
            day[i - 9] = 1;
        }
    return true;
    }
    public String get_next_class(Iterator<String> iter, String current){
        while (iter.hasNext()){
            if (iter.next() == current){
                if (iter.hasNext()){
                    return iter.next();
                }
                return "NULL";
            }
        }
        return "Error";
    }

    public void reset(HashMap<String, HashMap<String, ArrayList<Integer>>> time, String next_lect, int[] Monday, int[] Tuedsday, int[] Wednesday, int[] Thursday, int[] Friday){
        Iterator<String> lectures= time.keySet().iterator();
        while (lectures.hasNext()){
            HashMap<String, ArrayList<Integer>> weekday = time.get(lectures.next());
            Iterator<String> clear = weekday.keySet().iterator();
            while(clear.hasNext()){
                String p = clear.next();
                int i = weekday.get(p).get(0);
                int j = weekday.get(p).get(1);
                if (p == "Monday"){
                    clear(Monday,i,j);
                }
                else if (p == "Tuesday"){
                    clear(Tuedsday,i,j);
                }
                else if (p == "Wednesday"){
                    clear(Wednesday,i,j);
                }
                else if (p == "Thursday"){
                    clear(Thursday,i,j);
                }
                else{
                    clear(Friday,i,j);
                }
            }
        }

    }
    public void clear(int [] day, int start, int end){
        for (int i = start; i<end+1;i++) {
            if (day[i - 9] == 1) {
                day[i-9] = 0;
            }
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
