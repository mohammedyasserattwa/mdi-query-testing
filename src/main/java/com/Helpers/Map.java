package com.Helpers;

import java.io.*;
import java.util.*;

import org.apache.poi.xssf.usermodel.*;

public class Map {
                    // VARIABLES //
    private String source;
    private String target;
    private String filter;
    private ArrayList<String> sourceKey;
    private ArrayList<String> targetKey;
    private ArrayList<String> sourceCols;
    private ArrayList<String> targetCols;
    private ArrayList<Function> functions;
    private ArrayList<String> SQL_QUERIES;
    private ArrayList<condition> DEFAULT_VALUES;
                // FILE_VARIABLES //
    private String mapFile;
    FileInputStream fis;
    XSSFWorkbook mapWorkbook;
    XSSFSheet mapSheet;

                // CONTRUCTORS //
    public Map(String mapFile) throws Exception{
        this.mapFile = mapFile;
        this.fis = new FileInputStream(this.mapFile);
        this.mapWorkbook = new XSSFWorkbook(fis);
        this.mapSheet = this.mapWorkbook.getSheetAt(0);
        this.sourceCols = new ArrayList<String>();
        this.targetCols = new ArrayList<String>();
        this.functions = new ArrayList<Function>();
        this.SQL_QUERIES = new ArrayList<String>();
        this.DEFAULT_VALUES = new ArrayList<condition>();
    }
                // BASIC INFO //
    public void setAll() throws IOException{
        this.source = this.mapSheet.getRow(1).getCell(0).toString();
        this.target = this.mapSheet.getRow(1).getCell(1).toString();
        this.filter = this.mapSheet.getRow(3).getCell(2).toString();
        String sourceKeys = this.mapSheet.getRow(3).getCell(0).toString();
        this.sourceKey = new ArrayList<String>(Arrays.asList(sourceKeys.split(",")));
        String targetKeys = this.mapSheet.getRow(3).getCell(1).toString();
        this.targetKey = new ArrayList<String>(Arrays.asList(targetKeys.split(",")));
        int i = 5;
        while(true){
            String source = (this.mapSheet.getRow(i) == null)?"NA": this.mapSheet.getRow(i).getCell(0).toString();
            String target = (this.mapSheet.getRow(i) == null)?"NA": this.mapSheet.getRow(i).getCell(1).toString();
            if(source == "NA" || target == "NA")
                break;
            else{
                this.sourceCols.add(source);
                this.targetCols.add(target);
                if(this.mapSheet.getRow(i).getCell(2) != null)
                    this.functions.add(new Function(this.mapSheet.getRow(i).getCell(2).toString()));
                this.SQL_QUERIES.add((this.mapSheet.getRow(i).getCell(3) == null) ? "NA"
                        : this.mapSheet.getRow(i).getCell(3).toString());
                if(this.mapSheet.getRow(i).getCell(4) != null)
                    this.DEFAULT_VALUES.add(new condition(this.mapSheet.getRow(i).getCell(4).toString(), source, target));
            }
            i++;
        }
    }
    public ArrayList<String> getSource(){
        return this.sourceCols;
    }
    public ArrayList<String> getTarget(){
        return this.targetCols;
    }
    public String getSourceName(){
        return this.source;
    }
    public String getTargetName(){
        return this.target;
    }
    public ArrayList<String> getSourceKeys(){
        return this.sourceKey;
    }
    public ArrayList<String> getTargetKeys(){
        return this.targetKey;
    }
    public ArrayList<Function> getFunctions(){
        return this.functions;
    }
    public ArrayList<condition> getDefaults(){
        return this.DEFAULT_VALUES;
    }
}
