package com.example.gift;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.webianks.library.scroll_choice.ScrollChoice;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class addcourse extends AppCompatActivity {
    List<String> datas = new ArrayList<>();
    private static List<String> avaliable_times = new ArrayList<>();
    List<String> backup_times = new ArrayList<>();
    List<String> delete_times = new ArrayList<>();
    TextView textView;
    TextView textView1;
    ScrollChoice scrollChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        backup_times.add("Wednesday");
        delete_times.add("Wednesday");
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
                    display();
                }
            }
        });
        Button modify = findViewById(R.id.delete);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
        Button finish_button = findViewById(R.id.finish_button);
        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog();
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
                delete_times.add(name);
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
    private void delete(){
        String a = delete_times.get(delete_times.size()-1);
        if (avaliable_times.remove(delete_times.get(delete_times.size()-1))) {
            Context context = getApplicationContext();
            CharSequence text = "Succesfully deleted " + a;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            display();
        }
    }
    private void display(){
        int i;
        String a = "";
        for (i = 0; i < avaliable_times.size(); i++){
            a += avaliable_times.get(i);
            a += ",";
        }
        textView1.setText("Current time " + a);
    }
    private void opendialog(){
        dialog p = new dialog();
        p.show(getSupportFragmentManager(), "example dialog");
    }
    public static String current_days(){
        String x = "";
        int i;
        for (i = 0; i < avaliable_times.size(); i++){
            x += avaliable_times.get(i);
            String a = System.getProperty("line.separator");
            x += a;
        }
        return x;
    }
}
