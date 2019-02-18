package com.example.gift;

import java.util.ArrayList;
import java.util.List;

public class course_manager {
    public static course_manager single_instance = null;
    private List<course> current_course = new ArrayList<>();
    public static course_manager get_instance(){
        if (single_instance == null){
            single_instance = new course_manager();
        }
        return single_instance;
    }
    public void add_course(course a){
        current_course.add(a);
    }
    public void delete_course(course a){
        current_course.remove(a);
    }
    public void print_course() {
        for (int i=0;i<course_manager.get_instance().current_course.size();i++){
            System.out.println(course_manager.get_instance().current_course.get(i).name);
            System.out.println(course_manager.get_instance().current_course.get(i).lecture_code);
            System.out.println(course_manager.get_instance().current_course.get(i).time);
        }
    }
}
