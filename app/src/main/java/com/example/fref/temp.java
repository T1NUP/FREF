package com.example.fref;

public class temp {

    String name,time,msg;

    temp(){

    }

    temp(String n, String t, String m)
    {
        this.name= n;
        this.time= t;
        this.msg= m;
    }

    public String getName() {
        return name;
    }

    public String getMsg() {
        return msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setName(String name) {
        this.name = name;
    }
}
