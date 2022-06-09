package com.monari.movixs.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);
        ButterKnife.bind(this);

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
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(),FavoritesActivity.class));
                        overridePendingTransition(0,0);
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
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PopularMoviesActivity.this);
                    mRecyclerViewPopular.setLayoutManager(layoutManager);
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
}