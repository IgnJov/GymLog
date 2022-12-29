package com.example.gymlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class History {
    private static final String TABLE_NAME = "histories";
    private static final String KEY_ID = "history_id";
    private static final String KEY_DATE = "date";

    private Integer history_id;
    private String date;

    public History(){}

    public History(String date) {
        this.date = date;
    }

    public History(Integer history_id, String date) {
        this.history_id = history_id;
        this.date = date;
    }

    public Integer getHistory_id() {
        return history_id;
    }

    public void setHistory_id(Integer history_id) {
        this.history_id = history_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static void insert(Context context, History history){
        //Get DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //Set Content Values
        ContentValues cv = new ContentValues();
        cv.put(KEY_DATE,history.getDate());

        dbHelper.insert(TABLE_NAME, cv);
    }

    public static int update(Context context, History history){
        //Set database
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //Set Content Values
        ContentValues cv = new ContentValues();
        cv.put(KEY_DATE, history.getDate());

        //Update data
        return dbHelper.update(TABLE_NAME, KEY_ID, history.getHistory_id(), cv);
    }

    public static void delete(Context context, History history){
        //Set database
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //Delete data
        dbHelper.delete(TABLE_NAME, KEY_ID, history.getHistory_id());
    }

    public static History get(Context context, int id){
        //Set database
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        Cursor cursor = dbHelper.get(TABLE_NAME, new String[] {KEY_ID, KEY_DATE}, KEY_ID, id);

        //Create model
        Integer history_id = cursor.getInt(0);
        String date = cursor.getString(1);
        History history = new History(history_id, date);

        return history;
    }

    public static ArrayList<History> getAllRecord(Context context){
        //Set database
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //Create array list of model
        ArrayList<History> modelList = new ArrayList<>();

        Cursor cursor = dbHelper.getAllRecord(TABLE_NAME);

        //Get all record
        if(cursor.moveToFirst()){
            do{
                Integer history_id = cursor.getInt(0);
                String date = cursor.getString(1);
                History history = new History(history_id, date);

                modelList.add(history);
            }while(cursor.moveToNext());
        }

        return modelList;
    }

    public static History last(Context context){
        //Get DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        Cursor cursor = dbHelper.last(TABLE_NAME, KEY_ID);

        History history = null;

        if(cursor.moveToFirst()){
            Integer history_id = cursor.getInt(0);
            String date = cursor.getString(1);
            history = new History(history_id, date);
        }

        return history;
    }
}
