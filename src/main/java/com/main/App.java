package com.main;
import com.excel.Excel;
import com.database.Database;

import java.util.ArrayList;

import com.Helpers.Record;

import com.Helpers.Simulator;
public class App implements Runnable {
    String[] paths = {};
    String table = "";
    String table2 = "";
    public App(String[] paths, String table){
        this.paths = paths;
        this.table = table;
    }
    public App(String[] paths, String table, String table2){
        this.paths = paths;
        this.table = table;
        this.table2 = table2;
    }
    public App(String[] paths){
        this.paths = paths;
    }
    Database db = new Database("jdbc:mysql://localhost:3306","root","root","trial");
	public void run() {
        try {
            // System.out.println("Hena");
            // Excel excel2 = new Excel(paths[0],100,105);
            // // System.out.println(excel2.records.getCount());
            // Excel excel1 = new Excel(paths[1],100,105);
            // Excel.compare(excel1.records, excel2.records, "EXCEL", "EXCEL", excel1.fileName, excel2.fileName);
            // System.out.println(excel1.records.getCount());
            
            // Simulator sim1 = new Simulator(this.paths[0],this.table);
            // sim1.runInsert();
            
            // Record dbrecord = db.read("*",1,10,this.table);
            // Record dbrecord2 = db.read("*", this.table2);
            // Excel.compare(dbrecord, dbrecord2,"MYSQL","MYSQL");
            // excel1.compare(excel1.records.range(1,50), excel1.records.range(156,200), "EXCEL", "EXCEL");
            // System.out.println(excel1.records.range(1,50).getCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
