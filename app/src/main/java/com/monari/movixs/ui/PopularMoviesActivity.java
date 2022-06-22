package com.monari.movixs.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.monari.movixs.Adapters.PopularMoviesAdapter;
import com.monari.movixs.BuildConfig;
import com.monari.movixs.MoviesActivity;
import com.monari.movixs.R;
import com.monari.movixs.models.Result;
import com.monari.movixs.models.TMDBSearchMoviesResponse;
import com.monari.movixs.network.TMDBApi;
import com.monari.movixs.network.TMDBClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class PopularMoviesActivity extends AppCompatActivity {
    private Context mContext;

    private static final String TAG = PopularMoviesActivity.class.getSimpleName(); // returns the simple name of the underlying class as given in the source code.
    @BindView(R.id.errorTextViewPopular)
    TextView mErrorTextViewPopular;
    @BindView(R.id.progressBarPopular)
    ProgressBar mProgressBarPopular;
    @BindView(R.id.recyclerViewPopular)
    RecyclerView mRecyclerViewPopular;

    public List<Result> results;
    private PopularMoviesAdapter mAdapter;
    TMDBApi tmdbApi;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome " + user.getDisplayName());
                } else {

                }
            }
        };

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), MoviesActivity.class));
                        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(),FavoritesActivity.class));
                        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
                        return true;
                }
                return false;
            }
        });

        //movie popular

        tmdbApi = TMDBClient.getClient();

        retrofit2.Call<TMDBSearchMoviesResponse> call = tmdbApi.getPopularMovies(BuildConfig.TMDB_API_KEY,1);

        call.enqueue(new retrofit2.Callback<TMDBSearchMoviesResponse>() {
            @Override
            public void onResponse(retrofit2.Call<TMDBSearchMoviesResponse> call, retrofit2.Response<TMDBSearchMoviesResponse> response) {

                if (response.isSuccessful()) {
                    hideProgressBar();

                    results = response.body().getResults();
                    mAdapter = new PopularMoviesAdapter(PopularMoviesActivity.this, results);
                    mRecyclerViewPopular.setAdapter(mAdapter);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    mRecyclerViewPopular.setLayoutManager(layoutManager);
//                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PopularMoviesActivity.this);
//
//                    mRecyclerViewPopular.setLayoutManager(layoutManager);
                    mRecyclerViewPopular.setHasFixedSize(true);

                    showMovies();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<TMDBSearchMoviesResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
            }

        });


    }
    private void showFailureMessage() {
        mErrorTextViewPopular.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextViewPopular.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextViewPopular.setText("Something went wrong. Please try again later");
        mErrorTextViewPopular.setVisibility(View.VISIBLE);
    }

    private void showMovies() {
        mRecyclerViewPopular.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBarPopular.setVisibility(View.GONE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_popular, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(PopularMoviesActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}