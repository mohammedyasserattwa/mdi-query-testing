package com.Helpers;

import java.util.ArrayList;

public class condition {
    public String condition;
    public String source;
    public String target;
    public ArrayList<Integer> conditions;
    public ArrayList<String> values;
    public condition(String condition, String source, String target){
        this.condition = condition;
        this.source = source;
        this.target = target;
        this.conditions = new ArrayList<Integer>();
        this.values = new ArrayList<String>();
        this.translate();
    }
    public void translate(){
        String tmpString = this.condition;
        int tmpCounter = 0;
        while(tmpString.length() > 1){
            if(tmpCounter++ > 0)
                tmpString = tmpString.substring(1);
            String c = tmpString.substring(5,tmpString.indexOf(":"));
            String value = tmpString.substring(tmpString.indexOf(":")+1,tmpString.indexOf(";"));
            if(!c.contains("otherwise"))
                this.conditions.add(Integer.parseInt(c));
            this.values.add(value);
            tmpString = tmpString.substring(tmpString.indexOf(";"));
        }
    }
    public String apply(int value){
        for(int i = 0;i < this.conditions.size(); i++)
            if(value < this.conditions.get(i))
                return this.values.get(i);
        return this.values.get(this.values.size()-1);
    }
    
}
