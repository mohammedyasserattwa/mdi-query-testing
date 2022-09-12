package com.main;
import com.excel.Excel;
import com.Helpers.Map;
import com.database.Database;

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
            
            // map.getAll();
            // System.out.println("Hena");
            // Excel excel2 = new Excel(paths[0]);
            // // // System.out.println(excel2.records.getCount());
            // Excel excel1 = new Excel(paths[0]); 
            // Excel.compare(excel1.records, excel2.records,"EXCEL","EXCEL","Locations","Locations2");
            // System.out.println(excel1.records.getCount());
            // Node node = new Node(new ArrayList<String>());
            // ArrayList<String> data = new ArrayList<String>();
            // data.add("Hello");
            // node.insert(data);
            // node.show();
            
            
            // Simulator sim1 = new Simulator(this.paths[0],this.table);
            // sim1.runInsert();
            
            com.Helpers.Record dbrecord = db.read("*",this.table);
            // System.out.println(dbrecord.next.keys);
            com.Helpers.Record dbrecord2 = db.read("*", this.table2);
            // System.out.println(dbrecord2.next.keys);
            Excel.compare(dbrecord, dbrecord2,"MYSQL","MYSQL");
            // excel1.compare(excel1.records.range(1,50), excel1.records.range(156,200), "EXCEL", "EXCEL");  
            // System.out.println(excel1.records.range(1,50).getCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
