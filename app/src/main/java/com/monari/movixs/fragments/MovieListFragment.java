package com.monari.movixs.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import com.monari.movixs.Adapters.MoviesListAdapter;
import com.monari.movixs.BuildConfig;
import com.monari.movixs.Constants;
import com.monari.movixs.R;
import com.monari.movixs.models.Result;
import com.monari.movixs.models.TMDBSearchMoviesResponse;
import com.monari.movixs.network.TMDBApi;
import com.monari.movixs.network.TMDBClient;
import com.monari.movixs.util.OnMovieSelectedListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieListFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.searchMovies)
    SearchView mSearchView;

    private MoviesListAdapter mAdapter;
    public ArrayList<Result> results = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;
    private OnMovieSelectedListener mOnMovieSelectedListener;





    public MovieListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        // Instructs fragment to include menu options:
        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);

        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_MOVIE_KEY, null);

        if (mRecentAddress != null) {
//            getMovies(mRecentAddress);
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnMovieSelectedListener = (OnMovieSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + e.getMessage());
        }
    }

    public void getMovies() {

        TMDBApi client = TMDBClient.getClient();

        Call<TMDBSearchMoviesResponse> call = client.getMovies(BuildConfig.TMDB_API_KEY,mSearchView.getQuery().toString(),1);

        call.enqueue(new Callback<TMDBSearchMoviesResponse>() {
            @Override
            public void onResponse(Call<TMDBSearchMoviesResponse> call, Response<TMDBSearchMoviesResponse> response) {


                if (response.isSuccessful()) {
                    results = response.body().getResults();
                    mAdapter = new MoviesListAdapter(getActivity(),results, mOnMovieSelectedListener);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

//                    showRestaurants();
                } else {
//                                showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<TMDBSearchMoviesResponse> call, Throwable t) {
//                        hideProgressBar();
//                        showFailureMessage();
            }

        });
    }

//    @Override
//    // Method is now void, menu inflater is now passed in as argument:
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        // Call super to inherit method from parent:
//        super.onCreateOptionsMenu(menu, inflater);
//
//        inflater.inflate(R.menu.menu_search, menu);
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                addToSharedPreferences(query);
////                getMovies(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    private void addToSharedPreferences(String moviee) {
        mEditor.putString(Constants.PREFERENCES_MOVIE_KEY, moviee).apply();
    }

}