package com.example.gymlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        EditText editTextExerciseName = findViewById(R.id.editTextExerciseName);
        Button buttonAddExercise = findViewById(R.id.buttonAddExercise);
        Spinner spinnerMuscle = findViewById(R.id.spinnerMuscle);

        String[] muscleList = {"Bicep", "Triceps", "Hamstring", "Glutes", "Chest", "Shoulder", "Calf", "Abs", "Back"};
        //Set spinner values
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, muscleList);
        spinnerMuscle.setAdapter(adapter);

        buttonAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get input values
                String exerciseName = editTextExerciseName.getText().toString();
                String muscle = spinnerMuscle.getSelectedItem().toString();

                //Create model
                Exercise newExercise = new Exercise(exerciseName, muscle);
                Exercise.insert(AddExerciseActivity.this, newExercise);

                Toast.makeText(AddExerciseActivity.this, "New Exercise Added", Toast.LENGTH_LONG);
                startActivity(new Intent(AddExerciseActivity.this, MainActivity.class));
            }
        });
    }


}