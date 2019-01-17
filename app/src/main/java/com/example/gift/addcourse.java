package com.example.gift;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class addcourse extends AppCompatActivity {
    List<String> datas = new ArrayList<>();
    TextView textView;
    ScrollChoice scrollChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse);
        initViews();
        loadDatas();
        scrollChoice.addItems(datas,2);
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                textView.setText("Choice " +name);
            }
        });
    }
    private void loadDatas(){
        datas.add("Monday");
        datas.add("Tuesday");
        datas.add("Wednesday");
        datas.add("Thursday");
        datas.add("Friday");
        datas.add("Saturday");
        datas.add("Sunday");
    }
    private void initViews(){
        textView = (TextView) findViewById(R.id.txt_result);
        scrollChoice = (ScrollChoice)findViewById(R.id.scroll_choice);
    }
}
