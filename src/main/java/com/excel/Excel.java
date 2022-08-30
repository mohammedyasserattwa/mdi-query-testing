package com.excel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import com.database.Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter; 

import com.Helpers.Record;

public class Excel {
public String path;
private FileInputStream fis;
private XSSFWorkbook workbook;
public ArrayList<HashMap> sheetList;
public Record records;
private Database db;
public Excel(String path) throws IOException {
    this.path = path;
    this.fis = new FileInputStream(path);
    this.workbook = new XSSFWorkbook(this.fis);
    this.db = new Database("jdbc:mysql://localhost:3306","root","rootroot","fady");
}

public void getData(int sheetNo){
    XSSFSheet sheet = this.workbook.getSheetAt(sheetNo);
    int rows = sheet.getLastRowNum();
    int cols = sheet.getRow(1).getLastCellNum();
    ArrayList<String> titles = getTitles(sheet,cols);
    Record result = new Record();
    Record tmp = new Record();
    for(int i = 1; i<rows;i++){
        XSSFRow row = sheet.getRow(i);
        for(int j = 0;j<cols;j++){
            XSSFCell cell = row.getCell(j);
            tmp.put((String) titles.get(j).toString(), cell.toString());
        }
        if(i == 1)
            result.next = tmp;
        tmp.next = new Record();
        tmp = tmp.next;
    }
    System.out.println(result.getCount());
    this.records = result;
}

public ArrayList<HashMap> toList(int sheetNo,String dbTable) throws SQLException {

    ArrayList<HashMap> result = new ArrayList<HashMap>();
    XSSFSheet sheet = this.workbook.getSheetAt(sheetNo);
    int rows = sheet.getLastRowNum();
    int cols = sheet.getRow(1).getLastCellNum();
    ArrayList titles = getTitles(sheet,cols);
    this.createTable(sheet,cols,dbTable);
    for(int i = 1;i<rows;i++){
        XSSFRow row = sheet.getRow(i);
        Map myMap = new HashMap();
        String data = "";
        for(int j = 0;j<cols;j++){
            XSSFCell cell = row.getCell(j);
            data+= ("'") + (cell.toString()) + ("',");

            switch(cell.getCellType()){
                case Cell.CELL_TYPE_STRING: myMap.put(titles.get(j),cell.toString()); break;
                case Cell.CELL_TYPE_NUMERIC: myMap.put(titles.get(j),String.format("%.0f",cell.getNumericCellValue())); break;
                case Cell.CELL_TYPE_BOOLEAN: myMap.put(titles.get(j),cell.getBooleanCellValue()); break;
            }
        }
        data = data.substring(0,data.length() - 1);
        this.db.insert(data,this.toString(titles),dbTable);
        result.add((HashMap) myMap);
    }
    this.sheetList = result;
    return result;
}
private ArrayList<String> getTitles(XSSFSheet sheet,int cols){
    XSSFRow row = sheet.getRow(0);
    ArrayList<String> titles = new ArrayList<String>();
    for(int i = 0;i<cols;i++){
        XSSFCell cell = row.getCell(i);
        titles.add(cell.toString());
    }
    return titles;
}
private void createTable(XSSFSheet sheet,int cols,String dbTable) throws SQLException {
    XSSFRow row = sheet.getRow(0);
    String query = row.getCell(0).toString() +" INT(10) PRIMARY KEY,";
    for(int i = 1;i<cols;i++){
        XSSFCell cell = row.getCell(i);
        query += cell.toString() + " VARCHAR(45),";

    }
    query = query.substring(0,query.length()-1);
    this.db.createTable(query,dbTable);
}
private String toString(ArrayList<String> title){
    String titles = "";
    for (Object o : title) {
        titles += o + (",");
    }
    titles = titles.substring(0,titles.length()-1);
    return titles;
}
public void compare(Record db1 , Record db2,String fileType1, String fileType2) throws IOException{
    String FILE_PATH = "./src/main/java/com/main/log/log["+db1.hashCode()+","+db2.hashCode()+"].txt";
    File log = new File(FILE_PATH);
    log.createNewFile();
    FileWriter myWriter = new FileWriter(FILE_PATH);
    int ERROR_ID = 1;
    myWriter.write("--> Comparing "+fileType1+" file to "+fileType2+"\n");
    myWriter.write("--> LENGTH TEST: \n");
    if(db1.getCount() == db2.getCount())
        myWriter.write("[PASSED] Number of records are equal ["+db1.getCount()+"]\n");
    else
        myWriter.write("[FAILED] Number of records are different [sheet#1 = "+db1.getCount()+"][sheet#2 = "+db2.getCount()+"]\n");
    myWriter.write("--> COLUMN_SIZE TEST: \n");
    if(db1.keys.size() == db2.keys.size())
        myWriter.write("[PASSED] Number of columns are the same ["+db2.next.keys.size()+"]\n");
    else
        myWriter.write("[FAILED] Number of columns are different [sheet#1 = "+db1.next.keys.size()+"][sheet#2 = "+db2.next.keys.size()+"]\n");
    myWriter.write("--> Comparing DATA in both files\n");
    while(db1.next != null && db2.next != null){
        if(!db1.values.toString().equals(db2.values.toString())){
            String ID = db1.values.get(0);
            String ID2 = db1.values.get(0);
            myWriter.write("====================================================== ERROR["+(ERROR_ID++)+"]: Record of ID(SHEET#1) = ["+ID+"] and ID(SHEET#2) = ["+ID2+"]\n");
            
            for(int i = 0;i<db1.values.size();i++){
                if(!db1.values.get(i).equals(db2.values.get(i))){
                    myWriter.write("COLUMN_NAME = ["+db1.keys.get(i)+","+db2.keys.get(i)+"] VALUES = ["+db1.values.get(i)+","+db2.values.get(i)+"]\n");
                }
            }
            myWriter.write("\n");
        }
        db1 = db1.next;
        db2 = db2.next;
    }
    myWriter.close();
}
}
