package com.main;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.mysql.cj.xdevapi.Statement;

// import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Main1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Class.forName("oracle.jdbc.driver.OracleDriver");
        // String path = "jdbc:oracle:thin:@localhost:1521:xe";
        // String name = "SYS as SYSDBA";
        // String pass = "root";
        // try {
        //     Class.forName("oracle.jdbc.driver.OracleDriver");
        //     Connection con = DriverManager.getConnection(path, name, pass);
        //     System.out.println("connect oracle db");
        //     Statement stmt = con.createStatement();

        //     // String selectStat = "SELECT * FROM fady";
        //     PreparedStatement ps = con.prepareStatement("SELECT * FROM locations10");

        //     ResultSet rs = ps.executeQuery();
        //     // ResultSetMetaData metaData = rs.getMetaData();
        //     while (rs.next())
        //         System.out.println(rs.getString(4));
        
            // while(rs.next()) {
            // System.out.println('h');
            // for (int i = 0; i < metaData.getColumnCount(); i++) {
            // System.out.println(rs.getString(i));
            // }

            // }
            // System.out.println(metaData.getColumnName(0));
            long heapspace = Runtime.getRuntime().totalMemory();
            long heapMaxSize = Runtime.getRuntime().maxMemory();
            long heapFreeSize = Runtime.getRuntime().freeMemory();
            System.out.println((heapspace) + " " + (heapMaxSize) + " " + heapFreeSize);
            // String query = "CREATE TABLE locations10(LOC_ID INT , STR_ADDRESS VARCHAR(45) , POSTAL_CODE varchar(45),CITY varchar(45),STATE_PROVINCE varchar(45),COUN_ID varchar(45) )";
            // stmt.execute(query);
            // String sql = "INSERT INTO locations10 (LOC_ID, STR_ADDRESS,POSTAL_CODE,CITY,STATE_PROVINCE,COUN_ID) VALUES (1, ' 1297 Via Cola di Rie', '989 ','Roma','Tokyo Prefecture ',' NL' ) ";
            // String sql2 = "INSERT INTO locations10 (LOC_ID, STR_ADDRESS,POSTAL_CODE,CITY,STATE_PROVINCE,COUN_ID) VALUES (2, ' 1297 Via Cola di Rie', '989 ','Roma','Tokyo Prefecture ','NL' ) ";
            // String sql3 = "INSERT INTO locations10 (LOC_ID, STR_ADDRESS,POSTAL_CODE,CITY,
            // STATE_PROVINCE,COUN_ID) VALUES (3, ' 1297 Via Cola di Rie', '989 ','Cairo
            // ','Alex Prefecture ','NL' ) ";
            // String sql4 = "INSERT INTO locations10 (LOC_ID, STR_ADDRESS,POSTAL_CODE,CITY,
            // STATE_PROVINCE,COUN_ID) VALUES (4, ' 1297 Via Cola di Rie', '989 ','Roma
            // ','Tokyo Prefecture ','NL' ) ";
            // String sql5 = "INSERT INTO locations10 (LOC_ID, STR_ADDRESS,POSTAL_CODE,CITY,
            // STATE_PROVINCE,COUN_ID) VALUES (5, ' 1297 Via Cola di Rie', '989 ','Roma
            // ','Tokyo Prefecture ','NL' ) ";

            // stmt.executeUpdate(sql);
            // stmt.executeUpdate(sql2);
            // stmt.executeUpdate(sql3);
            // stmt.executeUpdate(sql4);
            // stmt.executeUpdate(sql5);
        //     System.out.println("Table Created......");

        // } catch (Exception e) {
        //     System.out.println("opps!");
        //     e.printStackTrace();
        // }

    }
}