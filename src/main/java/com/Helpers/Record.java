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
    public Record range(int start, int end){
        Record temp = this;
        Record result = new Record();
        int i = 0;
        // result = temp;
        // result.keys = temp.keys;
        // result.values = temp.values;
        
        while(i <= start){
            temp = temp.next;
            i++;
        }
        result = temp;
        while(i >= end){
            result.next = new Record();
            
            temp = temp.next;
            i++;
        }
        System.out.println("temp: " + result.getCount());
        // System.out.println(result.values.toString());
        return result;
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
