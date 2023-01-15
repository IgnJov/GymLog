package com.example.gymlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.inflateMenu(R.menu.bottom_nav);

        Fragment selectedFragment = new HistoryListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new HistoryListFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.bottomNav_history){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new HistoryListFragment()).commit();
                }else if(item.getItemId() == R.id.bottomNav_exercise){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new ExerciseListFragment()).commit();
                }

                return true;
            }
        });
    }
}