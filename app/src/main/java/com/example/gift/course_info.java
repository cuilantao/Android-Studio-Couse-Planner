package com.example.gift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class course_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        Button select_time = findViewById(R.id.select_time);
        select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog();
            }
        });
    }
    private void opendialog(){
        dialog_for_time p = new dialog_for_time();
        p.show(getSupportFragmentManager(), "example dialog");
    }
}
