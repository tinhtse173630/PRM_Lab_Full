package com.exercise.mssql_project;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class DbUtils {

    public static Connection makeConnection() {
        Connection conn = null;
        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionString = "jdbc:jtds:sqlserver://10.0.2.2:1433;databaseName=demo_prm_db";
            conn = DriverManager.getConnection(connectionString, "sa", "12345");

        } catch (Exception e) {
            Log.e("ERROR", Objects.requireNonNull(e.getMessage()));
        }
        return conn;
    }
}
