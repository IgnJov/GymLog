package com.example.gymlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class ExerciseDetail {
    private static final String TABLE_NAME = "exercise_details";
    private static final String KEY_EXERCISE_ID = "exercise_id";
    private static final String KEY_HISTORY_ID = "history_id";
    private static final String KEY_SETS = "sets";
    private static final String KEY_REPS = "reps";
    private static final String KEY_WEIGHT = "weight";

    private Integer exercise_id;
    private Integer history_id;
    private Integer reps;
    private Integer sets;
    private Integer weight;

    public ExerciseDetail(){}

    public ExerciseDetail(Integer history_id, Integer reps, Integer sets, Integer weight) {
        this.history_id = history_id;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
    }

    public ExerciseDetail(Integer exercise_id, Integer history_id, Integer reps, Integer sets, Integer weight) {
        this.exercise_id = exercise_id;
        this.history_id = history_id;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
    }

    public Integer getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Integer exercise_id) {
        this.exercise_id = exercise_id;
    }

    public Integer getHistory_id() {
        return history_id;
    }

    public void setHistory_id(Integer history_id) {
        this.history_id = history_id;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public static void insert(Context context, ExerciseDetail exerciseDetail){
        //Get DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //Set Content Values
        ContentValues cv = new ContentValues();
        cv.put(KEY_EXERCISE_ID, exerciseDetail.getExercise_id());
        cv.put(KEY_HISTORY_ID, exerciseDetail.getHistory_id());
        cv.put(KEY_SETS, exerciseDetail.getSets());
        cv.put(KEY_REPS, exerciseDetail.getReps());
        cv.put(KEY_WEIGHT, exerciseDetail.getWeight());

        dbHelper.insert(TABLE_NAME, cv);
    }

    public static int update(Context context, ExerciseDetail exerciseDetail){
        //Set database
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //Set Content Values
        ContentValues cv = new ContentValues();
        cv.put(KEY_EXERCISE_ID, exerciseDetail.getExercise_id());
        cv.put(KEY_HISTORY_ID, exerciseDetail.getHistory_id());
        cv.put(KEY_SETS, exerciseDetail.getSets());
        cv.put(KEY_REPS, exerciseDetail.getReps());
        cv.put(KEY_WEIGHT, exerciseDetail.getWeight());

        //Update data
        return dbHelper.update(TABLE_NAME, KEY_EXERCISE_ID, KEY_HISTORY_ID, exerciseDetail.getExercise_id(), exerciseDetail.getHistory_id(), cv);
    }

    public static void delete(Context context, ExerciseDetail exerciseDetail){
        //Set database
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //Delete data
        dbHelper.delete(TABLE_NAME, KEY_EXERCISE_ID, KEY_HISTORY_ID, exerciseDetail.getExercise_id(), exerciseDetail.getHistory_id());
    }

    public static ExerciseDetail get(Context context, int id1, int id2){
        //Set database
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        Cursor cursor = dbHelper.get(TABLE_NAME, new String[] {KEY_EXERCISE_ID, KEY_HISTORY_ID, KEY_SETS, KEY_REPS, KEY_WEIGHT}, KEY_EXERCISE_ID, KEY_HISTORY_ID, id1, id2);

        //Create model
        Integer exercise_id = cursor.getInt(0);
        Integer history_id = cursor.getInt(1);
        Integer sets = cursor.getInt(2);
        Integer reps = cursor.getInt(3);
        Integer weight = cursor.getInt(4);
        ExerciseDetail exerciseDetail = new ExerciseDetail(exercise_id, history_id, sets, reps, weight);

        return exerciseDetail;
    }

    public static ArrayList<ExerciseDetail> getAllRecord(Context context){
        //Set database
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //Create array list of model
        ArrayList<ExerciseDetail> modelList = new ArrayList<>();

        Cursor cursor = dbHelper.getAllRecord(TABLE_NAME);

        //Get all record
        if(cursor.moveToFirst()){
            do{
                Integer exercise_id = cursor.getInt(0);
                Integer history_id = cursor.getInt(1);
                Integer sets = cursor.getInt(2);
                Integer reps = cursor.getInt(3);
                Integer weight = cursor.getInt(4);
                ExerciseDetail exerciseDetail = new ExerciseDetail(exercise_id, history_id, sets, reps, weight);

                modelList.add(exerciseDetail);
            }while(cursor.moveToNext());
        }

        return modelList;
    }
}
