package com.example.gymlog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Exercise exercise1 = new Exercise("Leg Press", "Leg");
        Exercise exercise2 = new Exercise("Bench Press", "Chest");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragmentContainerView, new HistoryFragment()).commit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.historyFragment:{
                    manager.beginTransaction().replace(R.id.fragmentContainerView, new HistoryFragment()).commit();
                    break;
                }

                case R.id.exerciseFragment:{
                    manager.beginTransaction().replace(R.id.fragmentContainerView, new exerciseFragment()).commit();
                    break;
                }
            }

            return true;
        });
    }
}