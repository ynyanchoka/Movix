package com.monari.movixs.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import com.monari.movixs.fragments.MoviesDetailFragment;
import com.monari.movixs.models.Result;

import java.util.List;

public class MoviePagerAdapter extends FragmentPagerAdapter {

    private List<Result> mMovies;
    //constructor where we set the required FragmentManager and array list of restaurants we will be swiping through.
    public MoviePagerAdapter(@NonNull FragmentManager fm, int behavior, List<Result> movies) {
        super(fm, behavior);
        mMovies = movies;
    }

    @Override
    public Fragment getItem(int position) {// // returns an instance of the RestaurantDetailFragment for the restaurant in the position provided as an argument.
        return MoviesDetailFragment.newInstance(mMovies.get(position));
    }

    @Override
    public int getCount() { //determines how many restaurants are in our Array List
        return mMovies.size();
    }

    @Override
    public CharSequence getPageTitle(int position) { // updates the title that appears in the scrolling tabs at the top of the screen
        return mMovies.get(position).getTitle();
    }
}
