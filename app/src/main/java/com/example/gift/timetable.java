package com.example.gift;

import android.os.HardwarePropertiesManager;
import android.util.Log;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.lang.String;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.transform.sax.TemplatesHandler;

public class timetable {
    int[] Monday = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] Tuesday = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] Wednesday = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] Thursday = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] Friday = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static List<Integer> info;
    public static HashMap<String, HashMap<String, HashMap<String, ArrayList<Integer>>>> code1;

    public timetable(course_manager courses) {
        HashMap<String, HashMap<String, HashMap<String, ArrayList<Integer>>>> code = new HashMap<>();
        for (int i = 0; i < courses.get_list().size(); i++) {
            String course_name = courses.get_list().get(i).name;
            if (!(code.containsKey(course_name))) {
                HashMap<String, HashMap<String, ArrayList<Integer>>> tmp = new HashMap<>();
                HashMap<String, ArrayList<Integer>> weekday_to_time = new HashMap<>();
                ArrayList<Integer> tmp1 = new ArrayList<>();
                fill_array(weekday_to_time, courses.get_list().get(i).time);
                tmp.put(courses.get_list().get(i).lecture_code, weekday_to_time);
                code.put(course_name, tmp);
            } else {
                ArrayList<Integer> tmp1 = new ArrayList<>();
                HashMap<String, ArrayList<Integer>> weekday_to_time = new HashMap<>();
                fill_array(weekday_to_time, courses.get_list().get(i).time);
                code.get(course_name).put(courses.get_list().get(i).lecture_code, weekday_to_time);
            }
        }
        sort(code, Monday, Tuesday, Wednesday, Thursday, Friday); // a recursive planning function
        code1 = code;
    }

    public void fill_array(HashMap<String, ArrayList<Integer>> weekday_to_time, HashMap<String, String> course_time) {
        Iterator<String> w = course_time.keySet().iterator();
        while (w.hasNext()) {
            ArrayList<Integer> tmp1 = new ArrayList<>();
            String tmp = w.next();
            tranform(course_time.get(tmp), tmp1);
            weekday_to_time.put(tmp, tmp1);
        }
    }

    public void tranform(String time, ArrayList<Integer> tmp1) {
        int slice = time.indexOf(":");
        String sub1 = time.substring(0, slice);
        Integer start = Integer.parseInt(sub1);
        int middle = time.indexOf("-");
        int slice2 = time.lastIndexOf(":");
        String sub2 = time.substring(middle + 1, slice2);
        Integer end = Integer.parseInt(sub2);
        tmp1.add(start);
        tmp1.add(end);
    }

    public void sort(HashMap<String, HashMap<String, HashMap<String, ArrayList<Integer>>>> code,
                    int[] Monday, int[] Tuesday,
                    int[] Wednesday, int[] Thursday, int[] Friday) {
        int length = code.size();
        List<List<Integer>> tmp = new ArrayList<>();
        for (int i = 0; i < code.size(); i++) {
            Iterator<String> lecture_codes = code.keySet().iterator();
            String course = lecture_codes.next();
            int l = code.get(course).size();
            List<Integer> w = new ArrayList<>();
            for (int j = 0; j < l; j++) {
                w.add(j);
            }
            tmp.add(w);
        }
        List<List<Integer>> brute = cartesianProduct(tmp);
        info = brute_force(brute, code, Monday, Tuesday, Wednesday, Thursday, Friday);
    }
    protected <T> List<List<T>> cartesianProduct(List<List<T>> lists) {
        List<List<T>> resultLists = new ArrayList<List<T>>();
        if (lists.size() == 0) {
            resultLists.add(new ArrayList<T>());
            return resultLists;
        } else {
            List<T> firstList = lists.get(0);
            List<List<T>> remainingLists = cartesianProduct(lists.subList(1, lists.size()));
            for (T condition : firstList) {
                for (List<T> remainingList : remainingLists) {
                    ArrayList<T> resultList = new ArrayList<T>();
                    resultList.add(condition);
                    resultList.addAll(remainingList);
                    resultLists.add(resultList);
                }
            }
        }
        return resultLists;
    }

    public List<Integer> brute_force(List<List<Integer>> brute, HashMap<String, HashMap<String, HashMap<String, ArrayList<Integer>>>> code,
                           int[] Monday, int[] Tuesday,
                           int[] Wednesday, int[] Thursday, int[] Friday){
        int suc = 0;
        List<Integer> failure =  new LinkedList<>();
        failure.add(-1);
        for (int i = 0; i < brute.size();i++){
            List<Integer> cur = brute.get(i);
            for (int j = 0; j < cur.size(); j++){
                int pos = cur.get(j); //index of the jth course
                Iterator<String> iter = code.keySet().iterator();
                String name = iter.next();//name of the course
                HashMap<String, HashMap<String, ArrayList<Integer>>> cur_course = code.get(name);//avaliable lection section
                Iterator<String> iter1 = cur_course.keySet().iterator();
                int count = -1;
                String name_of_lect = "";
                while (count != pos){
                    name_of_lect = iter1.next();
                    count++;
                }
                HashMap<String, ArrayList<Integer>> lecture_section = cur_course.get(name_of_lect);//we get a hashmap of weekdays corresponds to the lecture sections
                Iterator<String> time = lecture_section.keySet().iterator();
                while (time.hasNext()) {
                    String index = time.next();
                    ArrayList<Integer> tmp = lecture_section.get(index);
                    int m = tmp.get(0);
                    int n = tmp.get(1);
                    boolean result;
                    if (index == "Monday") {
                        result = set_array(Monday, m, n);
                    } else if (index == "Tuesday") {
                        result = set_array(Tuesday, m, n);
                    } else if (index == "Wednesday") {
                        result = set_array(Wednesday, m, n);
                    } else if (index == "Thursday") {
                        result = set_array(Thursday, m, n);
                    } else {
                        result = set_array(Friday, m, n);
                    }
                    if (result == false){
                        suc = -1;
                    }
                    else if(result == true && time.hasNext() == false && suc != -1){
                        suc = 1;
                    }
                }
            }
            if (suc == 1){
                return cur;
            }
            suc = 0;
        }
        return failure;
    }

    public static String get_text(String day) {
        String infomation = "";
        if (info.get(0) == -1) {
            return "null";
        } else {
            Iterator<String> iter = code1.keySet().iterator();
            for (int i = 0; i < info.size(); i++){
                String name = iter.next();//name of the course
                HashMap<String, HashMap<String, ArrayList<Integer>>> cur_course = code1.get(name);
                Iterator<String> iter1 = cur_course.keySet().iterator();
                int count = -1;
                String name_of_lect = "";
                while (count != info.get(i)) {
                    name_of_lect = iter1.next();
                    count++;
                }
                HashMap<String, ArrayList<Integer>> days = cur_course.get(name_of_lect);
                Iterator<String> weekdays = days.keySet().iterator();
                while (weekdays.hasNext()) {
                    String p = weekdays.next();
                    if (p == day) {
                        infomation += days.get(day);
                    }
                }
            }
        }
        return infomation;
    }
/*        HashMap<String, HashMap<String, ArrayList<Integer>>> next = code.get(course_name);
        Iterator<String> lecture_codes = next.keySet().iterator();
        while (lecture_codes.hasNext()) {
            String next_lect = lecture_codes.next();
            HashMap<String, ArrayList<Integer>> next_next = next.get(next_lect);
            Iterator<String> time = next_next.keySet().iterator();//we know the current lecture section, we want to reset the previous one
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
                if (!result) { //already false and no other lecture sections
                    Monday = monday_tmp;
                    Tuesday = tuesday_tmp;
                    Wednesday = wednesday_tmp;
                    Thursday = thursday_tmp;
                    Friday = friday_tmp;
                    break;
                } else if (result && !time.hasNext()) {// already entered every time for this lecture section and everything seems to be okay, we can skip to the next course
                    String rec_next = get_next_class(code.keySet().iterator(), course_name);
                    if (rec_next == "NULL") {
                        return 1;
                    }
                    if (sort(code, rec_next, Monday, Tuesday, Wednesday, Thursday, Friday) == 1) {
                        return 1;
                    }
                    else{
                        reset(code.get(course_name), next_lect, Monday, Tuesday, Wednesday, Thursday, Friday);
                    }
                }
            }
        }
        return 0;
    }*/

    public boolean set_array(int[] day, int start, int end) {
        int count = 0;
        for (int i = start; i < end + 1; i++) {
            if (day[i - 9] == 1) {
                count += 1;
            }
        }
        if (count > 1){
            return false;
        }
        return true;
    }

    public String get_next_class(Iterator<String> iter, String current) {
        while (iter.hasNext()) {
            if (iter.next() == current) {
                if (iter.hasNext()) {
                    return iter.next();
                }
                return "NULL";
            }
        }
        return "Error";
    }

    public void reset(HashMap<String, HashMap<String, ArrayList<Integer>>> time, String next_lect, int[] Monday, int[] Tuedsday, int[] Wednesday, int[] Thursday, int[] Friday) {
        if (next_lect == ""){
            Iterator<String> lectures = time.keySet().iterator();
            while (lectures.hasNext()){
                String cur = lectures.next();
                HashMap<String, ArrayList<Integer>> cur1 = time.get(cur);
                Iterator<String> cur2 = cur1.keySet().iterator();
                while (cur2.hasNext()) {
                    String cur3 = cur2.next();
                    int i = cur1.get(cur3).get(0);
                    int j = cur1.get(cur3).get(1);
                    if (cur3 == "Monday") {
                        clear(Monday, i, j);
                    } else if (cur3 == "Tuesday") {
                        clear(Tuedsday, i, j);
                    } else if (cur3 == "Wednesday") {
                        clear(Wednesday, i, j);
                    } else if (cur3 == "Thursday") {
                        clear(Thursday, i, j);
                    } else {
                        clear(Friday, i, j);
                    }
                }
            }
        }
        else {
            Iterator<String> lectures = time.keySet().iterator();
            String w = "";
            String p = "";
            while (true) {// look for previous one
                if (lectures.hasNext()) {
                    p = lectures.next();
                    if (lectures.hasNext()) {
                        w = lectures.next();
                        if (w != next_lect) {
                            w = "";
                        } else {
                            break;
                        }
                    }
                }
                break;
            }
            if (w != "") {
                HashMap<String, ArrayList<Integer>> weekday = time.get(p);
                Iterator<String> clear = weekday.keySet().iterator();
                while (clear.hasNext()) {
                    String sec = clear.next();
                    int i = weekday.get(sec).get(0);
                    int j = weekday.get(sec).get(1);
                    if (sec == "Monday") {
                        clear(Monday, i, j);
                    } else if (sec == "Tuesday") {
                        clear(Tuedsday, i, j);
                    } else if (sec == "Wednesday") {
                        clear(Wednesday, i, j);
                    } else if (sec == "Thursday") {
                        clear(Thursday, i, j);
                    } else {
                        clear(Friday, i, j);
                    }
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
