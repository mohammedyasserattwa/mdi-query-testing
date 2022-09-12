package com.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import com.database.Database;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;

import com.Helpers.Function;
import com.Helpers.Map;
import com.Helpers.Record;
import com.Helpers.condition;

public class Excel {
    public String path;
    private FileInputStream fis;
    private XSSFWorkbook workbook;
    public Record records;
    private Database db;
    public String fileName;
    public String fileType;

    public Excel(String path) throws IOException, IllegalArgumentException, IllegalAccessException {
        this.path = path;
        this.fis = new FileInputStream(path);
        this.workbook = new XSSFWorkbook(this.fis);
        this.db = new Database("jdbc:mysql://localhost:3306", "root", "rootroot", "fady");
        this.init(0);
        this.getData(0);
    }

    public Excel(String path, int start, int end) throws IOException, IllegalArgumentException, IllegalAccessException {
        this.path = path;
        this.fis = new FileInputStream(path);
        this.workbook = new XSSFWorkbook(this.fis);
        this.db = new Database("jdbc:mysql://localhost:3306", "root", "rootroot", "fady");
        this.init(0);
        this.getData(0, start, end);
    }

    public Excel(String path, int range, String action)
            throws IOException, IllegalArgumentException, IllegalAccessException {
        this.path = path;
        this.fis = new FileInputStream(path);
        this.workbook = new XSSFWorkbook(this.fis);
        this.db = new Database("jdbc:mysql://localhost:3306", "root", "rootroot", "fady");
        this.init(0);
        this.getData(0, range, action);
    }

    private XSSFSheet sheet;
    private Path filepath;
    // private String fileName;
    private int rows;
    private int cols;
    private ArrayList<String> titles;
    private static Map map;

    /**
     * This function initializes the sheet, filepath, filename, rows, cols, and
     * titles of the sheet
     * 
     * @param sheetNo the sheet number in the excel file
     */
    public void init(int sheetNo) {
        this.sheet = this.workbook.getSheetAt(sheetNo);
        this.filepath = Paths.get(this.path);
        String fileName = this.filepath.getFileName().toString();
        this.fileName = fileName;
        this.rows = sheet.getLastRowNum();
        this.cols = sheet.getRow(1).getLastCellNum();
        this.titles = getTitles(sheet, cols);
    }

    /**
     * It reads the data from the excel file and stores it in a linked list
     * 
     * @param sheetNo The sheet number in the excel file
     * @param range   the row number to start reading from
     * @param action  START or END
     */
    public void getData(int sheetNo, int range, String action) {
        Record result = new Record();
        Record tmp = new Record();
        XSSFCell cell;
        for (int i = (action == "START") ? range : 1; i <= ((action == "START") ? this.rows : range); i++) {
            XSSFRow row = this.sheet.getRow(i);
            for (int j = 0; j < this.cols; j++) {
                cell = row.getCell(j);
                tmp.put((String) this.titles.get(j).toString(),
                        (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
                                ? String.format("%.0f", cell.getNumericCellValue())
                                : cell.toString());
            }
            if (i == ((action == "START") ? range : 1))
                result.next = tmp;
            tmp.next = new Record();
            tmp = tmp.next;
        }
        System.out.println("\n[DONE] " + fileName + " successfully loaded [Entries = " + result.getCount() + "]");
        this.records = result;
    }

    public void getData(int sheetNo, int start, int end) {
        Record result = new Record();
        Record tmp = new Record();
        XSSFCell cell;
        XSSFRow row;
        if (start < end && end < rows) {
            for (int i = start; i <= end; i++) {
                row = this.sheet.getRow(i);
                for (int j = 0; j < this.cols; j++) {
                    cell = row.getCell(j);
                    tmp.put((String) this.titles.get(j).toString(),
                            (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
                                    ? String.format("%.0f", cell.getNumericCellValue())
                                    : cell.toString());
                }
                if (i == start)
                    result.next = tmp;
                tmp.next = new Record();
                tmp = tmp.next;
            }
            System.out.println("\n[DONE] " + fileName + " successfully loaded [Entries = " + result.getCount() + "]");
            this.records = result;
        } else
            System.out.println("ERROR: The start and end of the range given is invalid");
    }

    public void getData(int sheetNo) throws IllegalArgumentException, IllegalAccessException, IOException {
        Record result = new Record();
        Record tmp = new Record();
        XSSFCell cell;
        XSSFRow row;
        for (int i = 1; i <= this.rows; i++) {
            row = this.sheet.getRow(i);
            for (int j = 0; j < this.cols; j++) {
                cell = row.getCell(j);
                tmp.put((String) this.titles.get(j).toString(),
                        (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
                                ? String.format("%.0f", cell.getNumericCellValue())
                                : cell.toString());
            }
            if (i == 1)
                result.next = tmp;
            tmp.next = new Record();
            tmp = tmp.next;
        }
        System.out.println("\n[DONE] " + fileName + " successfully loaded [Entries = " + result.getCount() + "]");
        this.records = result;
    }

    /**
     * It takes a sheet and the number of columns in the sheet and returns an
     * ArrayList of the titles of
     * the columns
     * 
     * @param sheet the sheet you want to read
     * @param cols  the number of columns in the sheet
     * @return An ArrayList of Strings.
     */
    private ArrayList<String> getTitles(XSSFSheet sheet, int cols) {
        XSSFRow row = sheet.getRow(0);
        ArrayList<String> titles = new ArrayList<String>();
        for (int i = 0; i < cols; i++) {
            XSSFCell cell = row.getCell(i);
            titles.add(cell.toString());
        }
        return titles;
    }

    private static ArrayList<Function> functionsArray = new ArrayList<Function>();

    public static Record map() throws IOException {
        String path = "src/main/java/com/excel/sheets/map.xlsx";
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(1).getLastCellNum();
        Record result = new Record();
        Record tmp = new Record();
        XSSFSheet functions = workbook.getSheetAt(1);
        int funcrows = functions.getLastRowNum();
        for (int i = 0; i <= rows; i++) {
            XSSFRow row = sheet.getRow(i);
            ArrayList<String> myArr = new ArrayList<String>();
            for (int j = 0; j < cols; j++) {
                XSSFCell cell = row.getCell(j);
                myArr.add(cell.toString());
            }
            tmp.put(myArr.get(0), myArr.get(1));
            if (i == 0)
                result = tmp;
            tmp.next = new Record();
            tmp = tmp.next;
        }
        for (int i = 0; i <= funcrows; i++) {
            XSSFRow row = functions.getRow(i);
            Function f = new Function(row.getCell(0).toString());
            Excel.functionsArray.add(f);
        }

        System.out.println("[DONE] Successfully created the map array");
        return result;
    }

    public static void compare(Record db1, Record db2, String fileType1, String fileType2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date date = new Date();
        map = new Map("src/main/java/com/excel/sheets/map.xlsx");
        map.setAll();
        ArrayList<String> source = map.getSource();
        ArrayList<String> target = map.getTarget();
        ArrayList<Function> functions = map.getFunctions();
        ArrayList<condition> defaults = map.getDefaults();
        String sourceName = map.getSourceName();
        String targetName = map.getTargetName();
        String FILE_PATH = "./src/main/java/com/main/log/" + sourceName + "_" + targetName + "_log_" + sdf.format(date)
                + ".txt";
        File log = new File(FILE_PATH);
        log.createNewFile();
        String result = "";

        FileWriter myWriter = new FileWriter(FILE_PATH);

        result += "Comparing " + fileType1 + " file with " + fileType2 + " file\n\n-->> Comparing Length of both files_"
                + sdf.format(date) + " \n";
        if (db1.getCount() == db2.getCount())
            result += ("[INFO] Number of records in both files is the same [" + db1.getCount() + "]\n\n");
        else
            result += ("[WARNING] Number of records in both files is NOT the same [SOURCE = " + db1.getCount()
                    + "][TARGET = " + db2.getCount() + "]\n\n");
        result += ("-->> Comparing COLUMNS in both files_" + sdf.format(date) + " \n");
        if (db1.keys.size() == db2.keys.size())
            result += ("[INFO] Number of columns in both files is the same [" + db2.next.keys.size() + "]\n\n");
        else
            result += ("[WARNING] Number of columns is NOT the same [# of columns in SOURCE = " + db1.next.keys.size()
                    + "][# of columns in TARGET = " + db2.next.keys.size() + "]\n\n");
        ArrayList<String> sourceDiffs = new ArrayList<String>(source);
        ArrayList<String> targetDiffs = new ArrayList<String>(target);
        sourceDiffs.removeAll(db1.next.keys);
        targetDiffs.removeAll(db2.next.keys);
        for (int i = 0; i < sourceDiffs.size(); i++) {
            target.remove(target.get(source.indexOf(source.get(source.indexOf(sourceDiffs.get(i))))));
            source.remove(source.get(source.indexOf(sourceDiffs.get(i))));
            result += "[WARNING] The column " + sourceDiffs.get(i)
                    + " from the SOURCE file is not identical to the original sheet. The column will not be compared\n\n";
        }
        for (int i = 0; i < targetDiffs.size(); i++) {
            source.remove(source.get(target.indexOf(targetDiffs.get(i))));
            target.remove(target.get(target.indexOf(targetDiffs.get(i))));
            result += "[WARNING] The column " + targetDiffs.get(i)
                    + " from the TARGET file is not identical to the original sheet. The column will not be compared\n\n";
        }
        result += ("-->> Comparing DATA in both files_" + sdf.format(date) + "\n\n");
        // Record map = map();
        // Record tmp = map;
        
        int ERROR_COUNT = 0;
        myWriter.write(result);
        db1 = db1.next;
        db2 = db2.next;
        while (db1.next != null && db2.next != null) {
            for (int j = 0; j < functions.size(); j++) {
                long f1 = functions.get(j).apply(db1.values.get(db1.keys.indexOf(functions.get(j).source)),
                        db1.values.get(db1.keys.indexOf(functions.get(j).target)));
                long f2 = functions.get(j).apply(db2.values.get(db2.keys.indexOf(functions.get(j).source)),
                        db2.values.get(db2.keys.indexOf(functions.get(j).target)));
                if(!Function.compare(f1, f2)){
                    myWriter.write("\tERROR[" + sdf.format(date) + "]: @" + db1.keys.get(0) + " = ["
                            + db1.values.get(0) + "] FUNCTION in SOURCE = [" 
                            + db1.keys.get(db1.keys.indexOf(functions.get(j).source))
                            + functions.get(j).function 
                            + functions.get(j).target + "] VALUE of function for SOURCE = ["
                            + f1 + "]  FUNCTION in TARGET = ["
                            + target.get(source.indexOf(functions.get(j).source))
                            + functions.get(j).function
                            + target.get(source.indexOf(functions.get(j).target))
                            + "] VALUE of function in TARGET= ["
                            + f2 + "]\n");
                }
            }
            if (!db1.values.toString().equals(db2.values.toString())) {
                ERROR_COUNT++;
                int j = 0;
                for (int i = 0; i < source.size(); i++){
                    String sourceValue = db1.values.get(db1.keys.indexOf(source.get(i)));
                    String targetValue = db2.values.get(db2.keys.indexOf(target.get(i)));
                    if(j<defaults.size()){
                        if(defaults.get(j).source == (source.get(i)))
                            sourceValue = (defaults.get(j)
                                    .apply(Integer.parseInt(db1.values.get(db1.keys.indexOf(source.get(i))))));
                        if(defaults.get(j).target == (target.get(i)))
                            targetValue = defaults.get(j++).apply(Integer.parseInt(db2.values.get(db2.keys.indexOf(target.get(i)))));
                    }
                    if (!sourceValue.equals(targetValue))
                        myWriter.write("\tERROR[" + sdf.format(date) + "]: @" + db1.keys.get(0) + " = ["
                                + db1.values.get(0) + "] COLUMN_NAME in SOURCE = [" + source.get(i)
                                + "] VALUE in SOURCE = ["
                                + sourceValue + "]  COLUMN_NAME in TARGET = ["
                                        + target.get(i) + "] VALUE in TARGET= ["
                                        + targetValue + "]\n");
                }
                myWriter.write("\n");
            }
            db1 = db1.next;
            db2 = db2.next;
        }
        myWriter.write((ERROR_COUNT > 0) ? "Comparing Done." : "Both Files are identical.");
        myWriter.close();
        System.out.println("\n[DONE] Successfully generated the log file ");
    }
}
