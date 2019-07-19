package com.example.gift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Wednesday extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wednesday);
        find();
        Button back = (Button)findViewById(R.id.button9);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }
    public void find(){
        String tmp = timetable.get_text("Wednesday");
        TextView no1 = (TextView)findViewById(R.id.textView10);
        no1.setText(tmp);
    }
    public void back(){
        Intent tmp = new Intent(this, Checkweek.class);
        startActivity(tmp);
    }
}
