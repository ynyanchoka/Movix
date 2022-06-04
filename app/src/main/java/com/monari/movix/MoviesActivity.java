package com.monari.movix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

import com.monari.movix.Adapters.MoviesListAdapter;
import com.monari.movix.models.Result;
import com.monari.movix.models.TMDBSearchMoviesResponse;
import com.monari.movix.network.TMDBApi;
import com.monari.movix.network.TMDBClient;
import com.monari.movix.ui.SearchResultsActivity;

import kotlin.collections.ArrayDeque;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {

    private static final String TAG = MoviesActivity.class.getSimpleName(); // returns the simple name of the underlying class as given in the source code.
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.searchMovies) SearchView mSearchView;
    @BindView(R.id.searchMoviesButton) Button mSearchMoviesButton;
    TMDBApi tmdbApi;

    public List<Result> results;

    private MoviesListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);





        tmdbApi = TMDBClient.getClient();
        mSearchMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit2.Call<TMDBSearchMoviesResponse> call = tmdbApi.getMovies(BuildConfig.TMDB_API_KEY,mSearchView.getQuery().toString(),1);
                call.enqueue(new retrofit2.Callback<TMDBSearchMoviesResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<TMDBSearchMoviesResponse> call, retrofit2.Response<TMDBSearchMoviesResponse> response) {

                        hideProgressBar();

                        results = response.body().getResults();
                        mAdapter = new MoviesListAdapter(MoviesActivity.this, results);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MoviesActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        showMovies();

                    }


                    @Override
                    public void onFailure(Call<TMDBSearchMoviesResponse> call, Throwable t) {

                        Log.i(TAG, "onFailure: show something ",t );
                        t.printStackTrace();
                        hideProgressBar();
                        showFailureMessage();
                        showUnsuccessfulMessage();

                    }
                });

                //tvshows
                call = tmdbApi.getTvShows(BuildConfig.TMDB_API_KEY, mSearchView.getQuery().toString(), 1);
                call.enqueue(new retrofit2.Callback<TMDBSearchMoviesResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<TMDBSearchMoviesResponse> call, retrofit2.Response<TMDBSearchMoviesResponse> response) {

                        hideProgressBar();

                        results = response.body().getResults();
                        mAdapter = new MoviesListAdapter(MoviesActivity.this, results);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MoviesActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        showMovies();

                    }


                    @Override
                    public void onFailure(Call<TMDBSearchMoviesResponse> call, Throwable t) {

                        Log.i(TAG, "onFailure: show something ",t );
                        t.printStackTrace();
                        hideProgressBar();
                        showFailureMessage();
                        showUnsuccessfulMessage();

                    }
                });


            }
        });



//        TMDBApi client = TMDBClient.getClient(); //creating a client object and using it to make a request to the TMDB API
//        retrofit2.Call<TMDBSearchMoviesResponse> call = client.getMovies(BuildConfig.TMDB_API_KEY, mSearchView.getQuery().toString(),1);
//
//        call.enqueue(new retrofit2.Callback<TMDBSearchMoviesResponse>() {
//            @Override
//            public void onResponse(retrofit2.Call<TMDBSearchMoviesResponse> call, retrofit2.Response<TMDBSearchMoviesResponse> response) {
//                hideProgressBar();
//                if(response.isSuccessful())
//                 {
//                    results = response.body().getResults();
//                    mAdapter = new MoviesListAdapter(MoviesActivity.this, results);
//                    mRecyclerView.setAdapter(mAdapter);
//                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MoviesActivity.this);
//                    mRecyclerView.setLayoutManager(layoutManager);
//                    mRecyclerView.setHasFixedSize(true);
//
//
//                    showMovies();
//                } else{
//                    showUnsuccessfulMessage();                }
//
//            }
//
//            @Override
//            public void onFailure(Call<TMDBSearchMoviesResponse> call, Throwable t) {
//                Log.i(TAG, "onFailure: show something ",t );
//                t.printStackTrace();
//                hideProgressBar();
//                showFailureMessage();
//
//            }
//
//        });

    }


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
}