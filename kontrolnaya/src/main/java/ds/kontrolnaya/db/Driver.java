/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ds.kontrolnaya.db;

import com.mysql.cj.jdbc.NonRegisteringDriver;
import ds.kontrolnaya.config.Options;
import ds.kontrolnaya.models.Fee;
import ds.kontrolnaya.models.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;



public class Driver {
    private java.sql.Driver driver;
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet res = null;
    private String sql;

    private static final String GET_FEES = "SELECT * FROM fees;";
   private static final String GET_IVANOV_SUM ="SELECT SUM(sum) FROM fees WHERE payer='Ivanov';";
    private static final String GET_IVANOV_count ="SELECT COUNT(id) FROM fees WHERE payer='Ivanov';";

    public Driver() {
        connect();
    }


    private void connect() {
        try {
            driver = new NonRegisteringDriver();
            DriverManager.registerDriver(driver);
            System.out.println("[dbDriver] Connecting to database...");
            conn = DriverManager.getConnection(Options.DB_URL, Options.DB_USER, Options.DB_PASS);
            stmt = conn.createStatement();

            System.out.println("Successfully connected!");
        } catch (SQLException  se) {
            System.out.println("Some problem with test.db connection");
            se.printStackTrace();
            System.exit(1);
        }
    }



    public ArrayList<Fee> getFees() {
        System.out.println("Selecting fees...");
        ArrayList<Fee> data = new ArrayList<>();
        try {
            res = stmt.executeQuery(GET_FEES);
            HashMap<String, Fee> map = new HashMap<>();
            while (res.next()) {
                String id = res.getString("id");
                Fee fee = map.get(id);
                if (fee == null) {
                    fee = new Fee(
                            id,
                            res.getInt("type"),
                            res.getString("title"),
                            res.getString("payer"),
                            res.getDouble("sum")
                    );
                }

                data.add(fee);
            }

            System.out.println("[dbDriver] Successfully select " + data.size() + " fees.");

        } catch (Exception se) {
            se.printStackTrace();
        }
        return data;
    }

   public String getIvanovSum() {
        int count = 0;
        double sum = 0;
        System.out.println("[dbDriver] Get Ivanov count...");
        try {
            res = stmt.executeQuery(GET_IVANOV_count);
            while (res.next()) {
                count = res.getInt(1);
            }
            System.out.println("[dbDriver] " + count);
        } catch (Exception se) {
            se.printStackTrace();
        }
       try {
           res = stmt.executeQuery(GET_IVANOV_SUM);
           while (res.next()) {
               sum= res.getDouble(1);
           }
           System.out.println("[dbDriver] " + sum);
       } catch (Exception se) {
           se.printStackTrace();
       }

        return "Ivanov has "+count+" fees for the sum of "+sum+".";
    }


    private void close() {
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException se2) {
            System.out.println(se2.getMessage());
        }

        try {
            if (pstmt != null)
                pstmt.close();
        } catch (SQLException se2) {
            System.out.println(se2.getMessage());
        }

        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        System.out.println("[dbDriver] Close test.db connection... Goodbye!");
    }

    public static void main(String[] args) {
        Driver db = new Driver();
       System.out.println( db.getFees().size());
        db.close();
    }
}
