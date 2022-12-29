package com.example.gymlog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Exercise exercise1 = new Exercise("Leg Press", "Leg");
        Exercise exercise2 = new Exercise("Bench Press", "Chest");
    }
}