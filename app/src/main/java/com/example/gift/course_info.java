package com.example.gift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class course_info extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    TextView textView3;
    static List<TextView> sad = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        initviews();
        sad.add(textView1);
        sad.add(textView2);
        sad.add(textView3);
        final EditText mEdit = (EditText)findViewById(R.id.coursecode);
        Button select_time = findViewById(R.id.select_time);
        Button confirm = findViewById(R.id.confirm);
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
    }
    private void opendialog(){
        dialog_for_time p = new dialog_for_time();
        p.show(getSupportFragmentManager(), "example dialog");
    }
    public static void showtime(List time){
        real(time);
    }
    private static void real(List time){
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
    }
    private void makecourse(){

    }
}
