package com.example.gymlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "GymLog";
    private static final Integer DB_VERSION = 1;


    private static final String[] TABLE_NAMES = {"exercises", "exercise_details", "histories"};
    private static final String[][] TABLE_COLUMNS = {
            {"exercise_id", "exercise_name", "muscle"},
            {"exercise_id", "history_id", "sets", "reps", "weight"},
            {"history_id", "date"}
    };

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table exercises
        db.execSQL(
                "CREATE TABLE exercises (" +
                "exercise_id INTEGER PRIMARY KEY, " +
                "exercise_name TEXT, " +
                "muscle TEXT);"
        );
        //Create table histories
        db.execSQL(
                "CREATE TABLE histories (" +
                        "history_id INTEGER PRIMARY KEY, " +
                        "date TEXT);"
        );
        //Create table exercise detail
        db.execSQL(
                "CREATE TABLE exercise_details (" +
                        "exercise_id INTEGER, " +
                        "history_id INTEGER, " +
                        "sets INTEGER, " +
                        "reps INTEGER, " +
                        "weight INTEGER, " +
                        "FOREIGN KEY(exercise_id) REFERENCES exercises(exercise_id), " +
                        "FOREIGN KEY(history_id) REFERENCES histories(history_id));"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        for(String table_name : TABLE_NAMES){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_name);
        }
        onCreate(sqLiteDatabase);
    }

    public void insert(String tableName, ContentValues cv){
        //Get database
        SQLiteDatabase db = getWritableDatabase();

        db.insertOrThrow(tableName, null, cv);

        db.close();
    }

    public int update(String tableName, String keyId, int id, ContentValues cv){
        SQLiteDatabase db = getWritableDatabase();

        return db.update(tableName, cv, keyId + " = ?", new String[] {String.valueOf(id)});
    }

    public int update(String tableName, String keyId1, String keyId2, int id1, int id2, ContentValues cv){
        SQLiteDatabase db = getWritableDatabase();

        return db.update(tableName, cv, keyId1 + " = ? AND " + keyId2 + " = ?", new String[] {String.valueOf(id1), String.valueOf(id2)});
    }

    public void delete(String tableName, String key_id, int id){
        SQLiteDatabase db = getWritableDatabase();

        //Delete data
        db.delete(tableName, key_id + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    public void delete(String tableName, String keyId1, String keyId2, int id1, int id2){
        SQLiteDatabase db = getWritableDatabase();

        //Delete data
        db.delete(tableName, keyId1 + " = ? AND " + keyId2 + " = ?", new String[] {String.valueOf(id1), String.valueOf(id2)});
        db.close();
    }

    public Cursor get(String tableName, String[] tableColumns, String keyId, int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(
                tableName,
                tableColumns, keyId + " = ?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if(cursor != null) cursor.moveToFirst();

        return cursor;
    }

    public Cursor get(String tableName, String[] tableColumns, String keyId1, String keyId2, int id1, int id2){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(
                tableName,
                tableColumns, keyId1 + " = ? AND " + keyId2 + " = ?",
                new String[] {String.valueOf(id1), String.valueOf(id2)}, null, null, null, null);

        if(cursor != null) cursor.moveToFirst();

        return cursor;
    }

    public Cursor getAllRecord(String tableName){
        SQLiteDatabase db = getWritableDatabase();

        //Execute query
        String selectAllQuery = "SELECT * FROM " + tableName;
        Cursor cursor = db.rawQuery(selectAllQuery, null);

        return cursor;
    }

    public void dropTable(String tableName){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    public void reset(){
        SQLiteDatabase db = getWritableDatabase();

        for(String table_name : TABLE_NAMES){
            db.execSQL("DROP TABLE IF EXISTS " + table_name);
        }

        onCreate(db);
    }

    public Cursor last(String tableName, String keyId){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + " ORDER BY " + keyId + " DESC LIMIT 1;", null);

        return cursor;
    }
}
