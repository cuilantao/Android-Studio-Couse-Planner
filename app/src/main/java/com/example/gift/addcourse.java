package com.example.gift;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class addcourse extends AppCompatActivity {
    List<String> datas = new ArrayList<>();
    List<String> avaliable_times = new ArrayList<>();
    List<String> backup_times = new ArrayList<>();
    List<String> delete_times = new ArrayList<>();
    TextView textView;
    TextView textView1;
    ScrollChoice scrollChoice;
    boolean add = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        backup_times.add("Wednesday");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse);
        Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(avaliable_times.contains(backup_times.get(backup_times.size()-1)))) {
                    avaliable_times.add(backup_times.get(backup_times.size() - 1));
                    Context context = getApplicationContext();
                    CharSequence text = "Succesfully added " + backup_times.get(backup_times.size() - 1);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    int i;
                    String a = "";
                    for (i = 0; i < avaliable_times.size(); i++){
                        a += avaliable_times.get(i);
                        a += ",";
                    }
                    textView1.setText("Current time " + a);
                }
            }
        });
        Button modify = findViewById(R.id.delete);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        initViews();
        loadDatas();
        scrollChoice.addItems(datas,2);
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                textView.setText("Choice " + name);
                backup_times.add(name);
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
        textView1 = (TextView) findViewById(R.id.current_time);
    }
}
