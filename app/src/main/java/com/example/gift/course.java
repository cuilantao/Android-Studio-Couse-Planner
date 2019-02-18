package com.example.gift;

import java.util.HashMap;

public class course {
    String name;
    HashMap time;
    String lecture_code;

    public course(String name, String lecture_code, HashMap time) {
        this.name = name;
        this.lecture_code = lecture_code;
        this.time = time;
    }
}
