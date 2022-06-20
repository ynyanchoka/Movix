package com.monari.movixs.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.monari.movixs.Constants;
import com.monari.movixs.R;
import com.monari.movixs.fragments.MoviesDetailFragment;
import com.monari.movixs.models.Result;
import com.monari.movixs.ui.MoviesDetailActivity;
import com.monari.movixs.util.OnMovieSelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Result> mMovies  = new ArrayList<>();
    private Context mContext;
    private OnMovieSelectedListener mOnMovieSelectedListener;


    public MoviesListAdapter(Context context, ArrayList<Result> movies) {
        mContext = context;
        mMovies = movies;

    }


    @Override
    public MoviesListAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list_item, parent, false);
        MoviesViewHolder viewHolder = new MoviesViewHolder(view,mMovies, mOnMovieSelectedListener);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(MoviesListAdapter.MoviesViewHolder holder, int position) {
        holder.bindMovies(mMovies.get(position));



    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    //listner

    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movieImageView)
        ImageView mMovieImageView;
        @BindView(R.id.titleMovie)
        TextView mTitleMovie;
        @BindView(R.id.releaseDateTextview)
        TextView mReleaseDateTextview;
        @BindView(R.id.ratingMovies)
        TextView mRating;


        private Context mContext;
        private int mOrientation;
        private ArrayList<Result> mMovies = new ArrayList<>();
        private OnMovieSelectedListener mMovieSelectedListener;



        public MoviesViewHolder(View itemView,ArrayList<Result> results, OnMovieSelectedListener movieSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();


            // Determines the current orientation of the device:
            mOrientation = itemView.getResources().getConfiguration().orientation;
            mMovies = results;
            mMovieSelectedListener = movieSelectedListener;


            // Checks if the recorded orientation matches Android's landscape configuration.
            // if so, we create a new DetailFragment to display in our special landscape layout:
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
            itemView.setOnClickListener(this);
        }

        // Takes position of restaurant in list as parameter:
        private void createDetailFragment(int position) {
            // Creates new RestaurantDetailFragment with the given position:
            MoviesDetailFragment detailFragment = MoviesDetailFragment.newInstance(mMovies, position);
            // Gathers necessary components to replace the FrameLayout in the layout with the RestaurantDetailFragment:
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            //  Replaces the FrameLayout with the RestaurantDetailFragment:
            ft.replace(R.id.restaurantDetailContainer, detailFragment);
            // Commits these changes:
            ft.commit();
        }

        public void bindMovies(Result result) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+result. getPosterPath()).into(mMovieImageView);
            mTitleMovie.setText(result.getTitle()) ;
            mReleaseDateTextview.setText(result.getReleaseDate());
            mRating.setText(Double.toString(result.getVoteAverage()) + "/10");

        }

        @Override
        public void onClick(View v) {
            // Determines the position of the restaurant clicked:
            int itemPosition = getLayoutPosition();
            mMovieSelectedListener.onMovieSelected(itemPosition, mMovies);

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, MoviesDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_MOVIES, Parcels.wrap(mMovies));
                mContext.startActivity(intent);
            }
//            int itemPosition = getLayoutPosition();// retrieve the position of the specific list item clicked
//            Intent intent = new Intent(mContext, MoviesDetailActivity.class);
//            intent.putExtra("position", itemPosition);
//            intent.putExtra("movies", Parcels.wrap(mMovies));
//            mContext.startActivity(intent);
        }
    }
}
