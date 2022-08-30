package com.main;
import com.excel.Excel;
import com.database.Database;
import com.Helpers.Record;
public class App implements Runnable {
	public void run() {
        try {
            Excel excel = new Excel("C:\\Users\\moham\\IdeaProjects\\untitled1\\src\\main\\java\\org\\example\\locations.xlsx");
            excel.getData(0);
            //        System.out.println(excel.sheetList.get(0));
            Excel excel2 = new Excel("C:\\Users\\moham\\IdeaProjects\\untitled1\\src\\main\\java\\org\\example\\locations2.xlsx");
            excel2.getData(0);
            Database db = new Database("jdbc:mysql://localhost:3306","root","root","trial");
            // Record result = db.read("*","locations");
            // Record result2 = db.read("*","places");
            // System.out.println(excel);
            excel.compare(excel.records, excel2.records,"EXCEL","EXCEL");
            // System.out.println(excel.compare(excel2.sheetList, result));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
