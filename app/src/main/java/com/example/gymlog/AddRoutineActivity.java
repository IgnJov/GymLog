package com.example.gymlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRoutineActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    Spinner spinner_exerciseList;
    EditText editText_sets;
    EditText editText_reps;
    EditText editText_weight;
    Button button_addRoutine;
    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        dbHelper = new DatabaseHelper(AddRoutineActivity.this);
        spinner_exerciseList = findViewById(R.id.spinner_exercise);
        editText_sets = findViewById(R.id.editTextNumber_sets);
        editText_reps = findViewById(R.id.editTextNumber_reps);
        editText_weight = findViewById(R.id.editTextNumber_weight);
        button_addRoutine = findViewById(R.id.button_addRoutine);
        currentDate = getIntent().getStringExtra("date");

        //Set spinner adapter
        ArrayList<Exercise> exerciseArrayList = Exercise.getAllRecord(AddRoutineActivity.this);
        ArrayList<String> arraylistOfExerciseName = new ArrayList<>();
        for (Exercise exercise:
             exerciseArrayList) {
            arraylistOfExerciseName.add(exercise.getExercise_name());
        }
        String[] arrayOfExerciseName = arraylistOfExerciseName.toArray(new String[arraylistOfExerciseName.size()]);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        arrayOfExerciseName);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner_exerciseList.setAdapter(spinnerArrayAdapter);

        button_addRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //History Record
                ArrayList<History> historyArrayList = History.getAllRecord(AddRoutineActivity.this);

                //Get input data
                String exercise = spinner_exerciseList.getSelectedItem().toString();
                if(exercise.isEmpty()){
                    Toast.makeText(AddRoutineActivity.this, "Please add exercise to exercise list", Toast.LENGTH_LONG).show();
                    return;
                }
                String sets = editText_sets.getText().toString();
                String reps = editText_reps.getText().toString();
                String weight = editText_weight.getText().toString();

                //Check input validation
                if(validate(exercise, sets, reps, weight)){
                    //Search history record
                    History selectedHistory = null;
                    for (History history:historyArrayList
                    ) {
                        if(history.getDate().equals(currentDate)){
                            selectedHistory = history;
                        }
                    }

                    if(selectedHistory == null){
                        //Add history data to database
                        History newHistory = new History(currentDate);
                        History.insert(AddRoutineActivity.this, newHistory);
                        newHistory = History.last(AddRoutineActivity.this);

                        //Get exercise data
                        Exercise selectedExercise = Exercise.getExerciseByName(AddRoutineActivity.this, exercise);

                        //Add Exercise detail data to database
                        ExerciseDetail newExerciseDetail = new ExerciseDetail(selectedExercise.getExercise_id(), newHistory.getHistory_id(), Integer.parseInt(reps), Integer.parseInt(sets), Integer.parseInt(weight));
                        ExerciseDetail.insert(AddRoutineActivity.this, newExerciseDetail);

                    }else{
                        //Get exercise data
                        Exercise selectedExercise = Exercise.getExerciseByName(AddRoutineActivity.this, exercise);
                        ExerciseDetail newExerciseDetail = new ExerciseDetail(selectedExercise.getExercise_id(), selectedHistory.getHistory_id(), Integer.parseInt(reps), Integer.parseInt(sets), Integer.parseInt(weight));
                        ExerciseDetail.insert(AddRoutineActivity.this, newExerciseDetail);
                    }

                    startActivity(new Intent(AddRoutineActivity.this, MainActivity.class));
                }
            }
        });
    }

    public boolean validate(String exercise, String sets, String reps, String weight){
        Boolean status = true;
        String stringToast = null;

        if(exercise.isEmpty()){
            stringToast = "Exercise";
            status = false;
        }else if(sets.isEmpty()){
            stringToast = "Sets";
            status = false;
        } else if(reps.isEmpty()){
            stringToast = "Reps";
            status = false;
        } else if(weight.isEmpty()){
            stringToast = "Weight";
            status = false;
        }

        if(!status) Toast.makeText(AddRoutineActivity.this, stringToast + " can't be empty", Toast.LENGTH_LONG).show();

        return status;
    }

}