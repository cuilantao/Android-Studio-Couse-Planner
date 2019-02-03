package com.example.gift;

import java.util.ArrayList;
import java.util.List;

public class course_manager {
    public static course_manager single_instance = null;
    private List<course> current_course = new ArrayList<>();
    public static course_manager get_instance(){
        if (single_instance == null){
            return new course_manager();
        }
        return single_instance;
    }
    public void add_course(course a){
        current_course.add(a);
    }
    public void delete_course(course a){
        current_course.remove(a);
    }
}
