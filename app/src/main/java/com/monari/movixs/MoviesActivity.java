package com.monari.movixs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monari.movixs.Adapters.MoviesListAdapter;
import com.monari.movixs.models.Result;
import com.monari.movixs.models.TMDBSearchMoviesResponse;
import com.monari.movixs.network.TMDBApi;
import com.monari.movixs.network.TMDBClient;
import com.monari.movixs.ui.FavoritesActivity;
import com.monari.movixs.ui.PopularMoviesActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    private static final String TAG = MoviesActivity.class.getSimpleName(); // returns the simple name of the underlying class as given in the source code.
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.searchMovies) SearchView mSearchView;
    @BindView(R.id.searchMoviesButton) Button mSearchMoviesButton;

    TMDBApi tmdbApi;

    public List<Result> results;
    private Result mMovies;
    private MoviesListAdapter mAdapter;



    private DatabaseReference mSearchedMovieReference;
    private ValueEventListener mSearchedMovieReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedMovieReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_MOVIE);
        mSearchedMovieReference.addValueEventListener(new ValueEventListener() { //attach listener

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot movieSnapshot : dataSnapshot.getChildren()) {
                    String query = movieSnapshot.getValue().toString();
                    Log.d("Movies updated", "Movie: " + query); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_MOVIE_KEY, null);

        if(mRecentAddress != null){
            fetchMovies(mRecentAddress);
        }
        mSearchMoviesButton.setOnClickListener(this);




        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.search);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
                        return true;
                    case R.id.search:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), PopularMoviesActivity.class));
                        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
                        return true;
                }
                return false;
            }
        });






        tmdbApi = TMDBClient.getClient();
        mSearchMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit2.Call<TMDBSearchMoviesResponse> call = tmdbApi.getMovies(BuildConfig.TMDB_API_KEY,mSearchView.getQuery().toString(),1);
                call.enqueue(new retrofit2.Callback<TMDBSearchMoviesResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<TMDBSearchMoviesResponse> call, retrofit2.Response<TMDBSearchMoviesResponse> response) {

                        hideProgressBar();
                        if (response.isSuccessful()) {

                        results = response.body().getResults();
                        mAdapter = new MoviesListAdapter(MoviesActivity.this, results);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MoviesActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        showMovies();
                        hideFailureMessage();
                        } else {
                            showUnsuccessfulMessage();
                            hideShowMovies();
                        }
                    }



                    @Override
                    public void onFailure(Call<TMDBSearchMoviesResponse> call, Throwable t) {

                        Log.i(TAG, "onFailure: show something ",t );
                        t.printStackTrace();
                        hideProgressBar();
                        showFailureMessage();

                    }
                });

                //tvshows
//                call = tmdbApi.getTvShows(BuildConfig.TMDB_API_KEY, mSearchView.getQuery().toString(), 1);
//                call.enqueue(new retrofit2.Callback<TMDBSearchMoviesResponse>() {
//                    @Override
//                    public void onResponse(retrofit2.Call<TMDBSearchMoviesResponse> call, retrofit2.Response<TMDBSearchMoviesResponse> response) {
//
//                        hideProgressBar();
//                        if (response.isSuccessful()) {
//
//                        results = response.body().getResults();
//                        mAdapter = new MoviesListAdapter(MoviesActivity.this, results);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MoviesActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//
//                        showMovies();
//                        } else {
//                            showUnsuccessfulMessage();
//                        }
//                    }
//
//
//
//                    @Override
//                    public void onFailure(Call<TMDBSearchMoviesResponse> call, Throwable t) {
//
//                        Log.i(TAG, "onFailure: show something ",t );
//                        t.printStackTrace();
//                        hideProgressBar();
//                        showFailureMessage();
//                        showUnsuccessfulMessage();
//
//                    }
//                });

            }
        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        ButterKnife.bind(this);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                addToSharedPreferences(query);
//                fetchMovies(query);
//                return false;
//            }
//
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                return false;
//            }
//        });
//
//        return true;
//    }

    @Override
    public void onClick(View v) {
        if (v == mSearchMoviesButton) {
            String query = mSearchView.getQuery().toString();
            saveMovieToFirebase(query);
            if(!(query).equals("")) {
                addToSharedPreferences(query);
            }
            Intent intent = new Intent(MoviesActivity.this, MoviesActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    }

    public void saveMovieToFirebase(String query) {
        mSearchedMovieReference.push().setValue(query);
    }

    private void addToSharedPreferences(String query) {
        mEditor.putString(Constants.PREFERENCES_MOVIE_KEY, query).apply();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedMovieReference.removeEventListener(mSearchedMovieReferenceListener);
//    }


    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showMovies() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void hideFailureMessage() {
        mErrorTextView.setVisibility(View.GONE);
    }

    private void hideShowMovies() {
        mRecyclerView.setVisibility(View.GONE);
    }

    private void fetchMovies(String query){
        TMDBApi client = TMDBClient.getClient();
        Call<TMDBSearchMoviesResponse> call = client.getMovies(BuildConfig.TMDB_API_KEY,mSearchView.getQuery().toString(),1);
        call.enqueue(new Callback<TMDBSearchMoviesResponse>() {
            @Override
            public void onResponse(Call<TMDBSearchMoviesResponse> call, Response<TMDBSearchMoviesResponse> response) {

                hideProgressBar();

                hideProgressBar();
                if (response.isSuccessful()) {

                    results = response.body().getResults();
                    mAdapter = new MoviesListAdapter(MoviesActivity.this, results);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MoviesActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showMovies();
                    hideFailureMessage();
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


}