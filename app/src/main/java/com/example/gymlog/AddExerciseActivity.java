package com.example.gymlog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class AddExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        EditText editText_exerciseName = findViewById(R.id.editText_exerciseName);
        Spinner spinner_muscle = findViewById(R.id.spinner_muscle);
        Button button_addExercise = findViewById(R.id.button_addExercise);

        ArrayList<String> muscleList = new ArrayList<>(Arrays.asList("Hamstring", "Glutes", "Shoulder", "Bicep", "Triceps", "Chest", "Calf", "Lats"));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_checked_layout, muscleList);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spinner_muscle.setAdapter(adapter);

        button_addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exerciseName = editText_exerciseName.getText().toString();
                String muscle = spinner_muscle.getSelectedItem().toString();

                if(validate(exerciseName, muscle)){
                    Exercise newExercise = new Exercise(exerciseName, muscle);
                    Exercise.insert(AddExerciseActivity.this, newExercise);
                    Toast.makeText(AddExerciseActivity.this, "Successfully add new exercise", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean validate(String exerciseName, String muscle){
        Boolean status = true;
        String stringToast = null;

        if(exerciseName.isEmpty()){
            stringToast = "Exercise Name";
            status = false;
        }else if(muscle.isEmpty()){
            stringToast = "Muscle";
            status = false;
        }

        if(!status) Toast.makeText(AddExerciseActivity.this, stringToast + " can't be empty", Toast.LENGTH_LONG).show();

        return status;
    }
}