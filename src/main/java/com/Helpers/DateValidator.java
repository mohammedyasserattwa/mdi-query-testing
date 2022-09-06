package com.Helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator{
    private String Date;
    public DateValidator(String Date){
        this.Date = Date;
    }
    public boolean isValid(String date){
        DateFormat sdf = new SimpleDateFormat(this.Date);
        sdf.setLenient(false);
        try{
            sdf.parse(date);
        }catch(ParseException e){
            return false;
    }
    return true;
    }
}
