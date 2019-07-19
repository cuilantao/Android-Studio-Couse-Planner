package com.example.gift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button course_plan = findViewById(R.id.course_plan);
        course_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump();
            }
        });
    }
    private void jump(){
        Intent tmp = new Intent(this, course_info.class);
        startActivity(tmp);
    }
}
