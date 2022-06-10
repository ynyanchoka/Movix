package com.monari.movixs.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.monari.movixs.Adapters.FirebaseMoviesViewHolder;
import com.monari.movixs.Constants;
import com.monari.movixs.MoviesActivity;
import com.monari.movixs.R;
import com.monari.movixs.models.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesActivity extends AppCompatActivity {

    private DatabaseReference mMovieReference;
    private FirebaseRecyclerAdapter<Result, FirebaseMoviesViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ButterKnife.bind(this);

        mMovieReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MOVIES);
        setUpFirebaseAdapter();
        hideProgressBar();
        showMovies();



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


    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Result> options =
                new FirebaseRecyclerOptions.Builder<Result>()
                        .setQuery(mMovieReference, Result.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Result, FirebaseMoviesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseMoviesViewHolder firebaseMovieViewHolder, int position, @NonNull Result result) {
                firebaseMovieViewHolder.bindMovies(result);
            }

            @NonNull
            @Override
            public FirebaseMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list_item, parent, false);
                return new FirebaseMoviesViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

    private void showMovies() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}