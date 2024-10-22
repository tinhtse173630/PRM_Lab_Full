package com.exercise.lab9;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    // Constructor for Database helper
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // This method is called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    // This method is called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.close(); // Close the database when upgrading
    }

    // Execute a query that doesn't return a result (e.g., CREATE, INSERT, UPDATE, DELETE)
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();    // Get the database in writable mode
        database.execSQL(sql);                              // Execute the SQL statement
    }

    // Execute a query that returns a result (e.g., SELECT)
    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();    // Get the database in readable mode
        return database.rawQuery(sql, null);    // Execute the SQL query and return the result as a Cursor
    }
}
