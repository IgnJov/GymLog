package com.example.gymlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Exercise {
    private static final String TABLE_NAME = "exercises";
    private static final String KEY_ID = "exercise_id";
    private static final String KEY_NAME = "exercise_name";
    private static final String KEY_MUSCLE = "muscle";

    private Integer exercise_id;
    private String exercise_name;
    private String muscle;

    public Exercise(){}

    public Exercise(String exercise_name, String muscle) {
        this.exercise_name = exercise_name;
        this.muscle = muscle;
    }

    public Exercise(Integer exercise_id, String exercise_name, String muscle) {
        this.exercise_id = exercise_id;
        this.exercise_name = exercise_name;
        this.muscle = muscle;
    }

    public Integer getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Integer exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public static void insert(Context context, Exercise exercise){
        //Get DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //Set Content Values
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, exercise.getExercise_name());
        cv.put(KEY_MUSCLE, exercise.getMuscle());

        dbHelper.insert(TABLE_NAME, cv);
    }

    public static int update(Context context, Exercise exercise){
        //Set database
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //Set Content Values
        ContentValues cv= new ContentValues();
        cv.put(KEY_NAME, exercise.getExercise_name());
        cv.put(KEY_MUSCLE, exercise.getMuscle());

        //Update data
        return dbHelper.update(TABLE_NAME, KEY_ID, exercise.getExercise_id(), cv);
    }

    public static void delete(Context context, Exercise exercise){
        //Set database
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //Delete data
        dbHelper.delete(TABLE_NAME, KEY_ID, exercise.getExercise_id());
    }

    public static Exercise get(Context context, int id){
        //Set database
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        Cursor cursor = dbHelper.get(TABLE_NAME, new String[] {KEY_ID, KEY_NAME, KEY_MUSCLE}, KEY_ID, id);

        //Create model
        Integer exercise_id = cursor.getInt(0);
        String exercise_name = cursor.getString(1);
        String muscle = cursor.getString(2);
        Exercise exercise = new Exercise(exercise_id, exercise_name, muscle);

        return exercise;
    }

    public static ArrayList<Exercise> getAllRecord(Context context){
        //Set database
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //Create array list of model
        ArrayList<Exercise> modelList = new ArrayList<>();

        Cursor cursor = dbHelper.getAllRecord(TABLE_NAME);

        //Get all record
        if(cursor.moveToFirst()){
            do{
                Integer exercise_id = cursor.getInt(0);
                String exercise_name = cursor.getString(1);
                String muscle = cursor.getString(2);
                Exercise exercise = new Exercise(exercise_id, exercise_name, muscle);

                modelList.add(exercise);
            }while(cursor.moveToNext());
        }

        return modelList;
    }

    public static Exercise last(Context context){
        //Get DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        Cursor cursor = dbHelper.last(TABLE_NAME, KEY_ID);

        Exercise exercise = null;

        if(cursor.moveToFirst()){
            Integer exercise_id = cursor.getInt(0);
            String exercise_name = cursor.getString(1);
            String muscle = cursor.getString(2);
            exercise = new Exercise(exercise_id, exercise_name, muscle);
        }

        return exercise;
    }

    public static Exercise getExerciseByName(Context context, String exercise_name){
        ArrayList<Exercise> exerciseArrayList = getAllRecord(context);

        for(Exercise exercise : exerciseArrayList){
            if(exercise.getExercise_name().equals(exercise_name)){
                return exercise;
            }
        }

        return null;
    }
}
