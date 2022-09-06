package com.Helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.apache.poi.ss.usermodel.DateUtil;

//ADD LOCATION_ID LOC_ID to new_col
public class Function {
    public String command;
    public String function;
    public String source;
    public String target;
    private String new_col;
    private String FILE_PATH;
    public Function(String command) throws IOException {
        this.command = command;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date date = new Date();
        this.FILE_PATH = "./src/main/java/com/main/functions/functions[" + this.command + "]_["
                + sdf.format(date) + "].txt";
        File log = new File(this.FILE_PATH);
        log.createNewFile();
        this.interpreter();
    }
    public void interpreter(){
        this.source = this.command.substring(0,this.command.indexOf(" "));
        this.function = this.command.substring(this.command.indexOf(" ")+1,this.command.indexOf(" ",this.command.indexOf(" ")+1));
        this.target = this.command.substring(this.command.lastIndexOf(" ")+1);
        // System.out.println(this.target);
    }
    public void apply(String val1, String val2) throws IOException{
        FileWriter myWriter = new FileWriter(this.FILE_PATH,true);
        switch(this.function){
            case "+":
                if(isInt(val1) && isInt(val2)){
                    myWriter.write("Applied Function ADD " + (Integer.parseInt(val1) + Integer.parseInt(val2)) + "\n");
                }
                break;
            case "-":
                // System.out.println(val1);
                if(isInt(val1) && isInt(val2)){
                    myWriter.write("Applied Function SUBSTRACT " + (Integer.parseInt(val1) - Integer.parseInt(val2)) + "\n");
                }
                break;
            case "DIFF":
                DateValidator dv = new DateValidator("MM/dd/yyyy");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate d1 = (!dv.isValid(val1)) ? null : LocalDate.parse(val1, formatter);
                LocalDate d2 = (!dv.isValid(val2)) ? null : LocalDate.parse(val2, formatter);

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                if (!dv.isValid(val1))
                    d1 = LocalDate.parse(sdf.format(DateUtil.getJavaDate(Double.parseDouble(val1))), formatter);
                if (!dv.isValid(val2))
                    d2 = LocalDate.parse(sdf.format(DateUtil.getJavaDate(Double.parseDouble(val2))), formatter);

                long daysBetween = ChronoUnit.DAYS.between(d1, d2);
                long years = daysBetween / 365;
                daysBetween %= 365;
                long months = daysBetween / 30;
                daysBetween %= 30;
                myWriter.write(years + " Year/s " + months + " Month/s " + daysBetween + " Days \n");
            break;
                case "*":
                if (isInt(val1) && isInt(val2)) {
                    myWriter.write("Applied Function MULTIPLY " + (Integer.parseInt(val1) * Integer.parseInt(val2)) + "\n"); 
                }
                break;
                case "/":
                if (isInt(val1) && isInt(val2)) {
                    myWriter.write("Applied Function DIVIDE " + (Integer.parseInt(val1) / Integer.parseInt(val2))+"\n");
                }
                break;

            }
            myWriter.close();
    }
    
    private boolean isInt(String val){
        if(val == null)
            return false;
        int length = val.length();
        if(length == 0)
            return false;
        int i = 0;
        if(val.charAt(0) == '-'){
            if(length == 1)
                return false;
            i = 1;
        }
        for(;i<length;i++){
            char c = val.charAt(i);
            if(c < '0' || c > '9')
                return false;
        }
        return true;
    }
}