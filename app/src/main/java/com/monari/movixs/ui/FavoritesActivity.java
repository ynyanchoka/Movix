package com.monari.movixs.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.monari.movixs.MoviesActivity;
import com.monari.movixs.R;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.favorites);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), MoviesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favorites:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),PopularMoviesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}