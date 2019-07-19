package com.example.gift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Checkweek extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkweek);
        Button monday = findViewById(R.id.button);
        Button tuesday = findViewById(R.id.button4);
        Button wednesday = findViewById(R.id.button5);
        Button thursday = findViewById(R.id.button6);
        Button friday = findViewById(R.id.button7);
        Button back = findViewById(R.id.button12);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpback();
            }
        });
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump_monday();
            }
        });
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump_tuesday();
            }
        });
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump_wednesday();
            }
        });
        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump_thursday();
            }
        });
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump_friday();
            }
        });
    }
    public void jump_monday(){
        Intent tmp = new Intent(this, Monday.class);
        startActivity(tmp);
    }
    public void jump_tuesday(){
        Intent tmp = new Intent(this, Tuesday.class);
        startActivity(tmp);
    }
    public void jump_wednesday(){
        Intent tmp = new Intent(this, Wednesday.class);
        startActivity(tmp);
    }
    public void jump_thursday(){
        Intent tmp = new Intent(this, Thursday.class);
        startActivity(tmp);
    }
    public void jump_friday() {
        Intent tmp = new Intent(this, Friday.class);
        startActivity(tmp);
    }
    public void jumpback(){
        Intent tmp = new Intent(this, course_info.class);
        startActivity(tmp);
    }
}
