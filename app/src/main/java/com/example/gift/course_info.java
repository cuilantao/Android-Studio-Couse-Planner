package com.example.gift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class course_info extends AppCompatActivity {
    EditText time;
    EditText time1;
    EditText time2;
    EditText coursecode;
    EditText mEdit;
    TextView textView1;
    private course_manager current = course_manager.get_instance();
    TextView textView2;
    TextView textView3;
    static List<TextView> sad = new ArrayList<>();
    List<EditText> course = new ArrayList<>();
    doneclass we = new doneclass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        initviews();
        if (sad.size() != 3) {
            sad.add(textView1);
            sad.add(textView2);
            sad.add(textView3);
        }
        if (course.size() != 3) {
            course.add(time);
            course.add(time1);
            course.add(time2);
        }
        Button select_time = findViewById(R.id.select_time);
        Button confirm = findViewById(R.id.confirm);
        final Button timetable = findViewById(R.id.timetable);
        select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makecourse();
            }
        });
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timetable p = new timetable(current);
            }
        });
    }
    private void opendialog(){
        dialog_for_time p = new dialog_for_time();
        p.show(getSupportFragmentManager(), "example dialog");
    }
    public static void showtime(List time){
        int i;
        for (i=0;i<time.size();i++){
            sad.get(i).setText(time.get(i).toString());
        }
        int j;
        for (j=i;j<3;j++){
            sad.get(j).setText("Null");
        }
    }
    private void initviews(){
        textView1 = findViewById(R.id.textView5);
        textView2 = findViewById(R.id.textView6);
        textView3 = findViewById(R.id.textView7);
        time = findViewById(R.id.editText);
        time1 = findViewById(R.id.editText2);
        time2 = findViewById(R.id.editText3);
        coursecode = findViewById(R.id.course_code);
        mEdit = findViewById(R.id.coursecode);
    }
    private void makecourse(){
        HashMap<String, String> course_time = new HashMap<>();
        int i;
        int j = 0;
        for (i=0;i<3;i++) {
            if (sad.get(i).getText().toString() != "Null") {
                j++;
            }
        }
        for (i=0;i<j;i++){
            course_time.put(sad.get(i).getText().toString(), course.get(i).getText().toString());

        }
        course p = new course(mEdit.getText().toString(), coursecode.getText().toString(), course_time);
        current.add_course(p);
    }
}
