package com.Helpers;

import java.util.ArrayList;

public class Record {
    public ArrayList<String> keys;
    public ArrayList<String> values;
    public Record head;
    public Record next;
    public Record(){
        this.keys = new ArrayList<String>();
        this.values = new ArrayList<String>();
        this.next = null;
    }
    public Record(Record next){
        this.keys = new ArrayList<String>();
        this.values = new ArrayList<String>();
        this.next = next;
    }
    public void put(String key, String value){
        this.keys.add(key);
        this.values.add(value);
    }
    public Record next(){
        return this.next;
    }
    public int getCount(){
        Record temp = this;
        int count = 0;
        while(temp.next != null){
            temp = temp.next;
            count++;
        }
        return count;
    }
    public void display(){
        System.out.println(this.values);
    }
}
