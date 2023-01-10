package com.example.gymlog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddRoutineActivity extends AppCompatActivity {

    Spinner spinner_exerciseList = findViewById(R.id.spinner_exercise);
    EditText editText_sets = findViewById(R.id.editTextNumber_sets);
    EditText editText_reps = findViewById(R.id.editTextNumber_reps);
    EditText editText_weight = findViewById(R.id.editTextNumber_weight);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        String exercise = spinner_exerciseList.getSelectedItem().toString();
        String sets = editText_sets.getText().toString();
        String reps = editText_reps.getText().toString();
        String weight = editText_weight.getText().toString();

        if(validate(exercise, sets, reps, weight)){

        }
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