package com.Helpers;
import java.io.IOException;
import java.sql.SQLException;

import com.database.Database;
import com.excel.Excel;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
public class Simulator {
    Database db1 = new Database("jdbc:mysql://localhost:3306","root","root","trial");
    Excel ex1 = null;
    String table = "";
    public Simulator(String path,String table) throws IllegalArgumentException, IllegalAccessException, IOException{
        //"src/main/java/com/excel/sheets/locations.xlsx"
        // this.ex1 = new Excel(path);
        this.table = table;
    }
    // public void runInsert() throws SQLException, IOException{
    //     System.out.println("Hena");
    //     // Record records = this.ex1.records.next;
    //     // Record map = this.ex1.map();
        
    //     int threshold = 1000;
    //     int j = 0;
    //     String data;
    //     String titles, titles2;
    //     ArrayList<String> values = new ArrayList<String>();
    //     ArrayList<String> values2 = new ArrayList<String>();
    //     Iterator<String> itr = records.keys.iterator();
    //     while(itr.hasNext()){
    //         String it = itr.next();
    //         values.add(it + " VARCHAR(45)");
    //         values2.add(it);
    //     }
    //     titles = values.toString().substring(1,values.toString().length() - 1);
    //     titles2 = values2.toString().substring(1,values2.toString().length()-1);
    //     this.db1.createTable(titles, this.table);
    //     while(records.next != null){
            
    //         if(j++ < threshold){
    //         // System.out.println(records.values.toString());
    //         String step1 = StringUtils.join(records.values, "', '");// Join with ", "
    //         String step2 = StringUtils.wrap(step1, "'");// Wrap step1 with "
    //         data = StringUtils.join(step2,",").substring(0,StringUtils.join(step2,",").length()-1);
    //         // System.out.println(titles);
    //         // data = data.substring(0, data.length() - 2);
    //         // titles = titles.substring(0,titles.length() - 2);
    //         // System.out.println(titles.length());
            
    //         this.db1.insert(data,titles2,this.table);
    //         // data = "";
    //         // titles = "";
    //         // System.out.println("Hena");
    //         records = records.next;
    //         }
    //         else{
                
    //             return;
    //         }
    //     }
    // }
    
}
