package com.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.Helpers.Record;
public class Database {
    public String url,username,password,schema;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private Statement stmt;
    private Connection con;
    public Database(String url, String username, String password, String schema){
        this.url = url;
        this.username = username;
        this.password = password;
        this.schema = schema;
        try {
            Class.forName(this.driver);
            this.con = DriverManager.getConnection(
                    this.url+"/"+this.schema,this.username,this.password
            );
            this.stmt = this.con.createStatement();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public Record read(String col, String table) throws SQLException {
        ResultSet rs = this.stmt.executeQuery("SELECT " + col + " from " + table);
        ResultSetMetaData metaData = rs.getMetaData();
        // ArrayList<HashMap> result = new ArrayList();
        Record result = new Record();
        Record tmp = new Record();
        int j = 1;
        while(rs.next()) {
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                tmp.put(metaData.getColumnName(i + 1), rs.getString(metaData.getColumnName(i + 1)));
            }
            if(j++ == 1)
                result.next = tmp;
            tmp.next = new Record();
            tmp = tmp.next;
        }
        return result;
    }
    public Record read(String col,String table, String condition) throws SQLException{
        ResultSet rs = this.stmt.executeQuery("SELECT " + col + " from " + table + " WHERE " + condition);
        ResultSetMetaData metaData = rs.getMetaData();
        Record result = new Record();
        Record tmp = new Record();
        int j = 1;
        while(rs.next()) {
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                tmp.put(metaData.getColumnName(i + 1), rs.getString(metaData.getColumnName(i + 1)));
            }
            if(j++ == 1)
                result.next = tmp;
            tmp.next = new Record();
            tmp = tmp.next;
        }
        return result;
    }
    public void insert(String data,String fields, String table) throws SQLException {
        String sql = "INSERT IGNORE INTO " + table + "("+fields+") VALUES("+ data +")";
        this.stmt.executeUpdate(sql);
    }
    public void delete(String ID, String IDField, String table) throws SQLException {
        this.stmt.executeUpdate("DELETE FROM " + table + " WHERE "+IDField+" = " + ID);
    }
    public void update(String table,String data,String ID) throws SQLException {
        this.stmt.executeUpdate("UPDATE " + table + " SET " + data + " WHERE ID = " + ID);
    }
    public void createTable(String fields, String name) throws SQLException {
        String sql = "create table if not exists "+name+" ("+fields+")";
        this.stmt.execute(sql);
    }
}
