package com.monari.movix.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monari.movix.R;
import com.monari.movix.models.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder> {
    private List<Result> mMovies;
    private Context mContext;

    public MoviesListAdapter(Context context, List<Result> movies) {
        mContext = context;
        mMovies = movies;
    }


    @Override
    public MoviesListAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list_item, parent, false);
        MoviesViewHolder viewHolder = new MoviesViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MoviesListAdapter.MoviesViewHolder holder, int position) {
        holder.bindMovies(mMovies.get(position));
//        ((MoviesViewHolder) holder).vote_average.setText(mMovies.get(i)getVoteAverage());

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movieImageView)
        ImageView mMovieImageView;
        @BindView(R.id.titleMovie)
        TextView mTitleMovie;
        @BindView(R.id.genre)
        TextView mGenre;
        @BindView(R.id.ratingMovies)
        TextView mRating;

        private Context mContext;


        public MoviesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindMovies(Result result) {
            mTitleMovie.setText(result.getTitle());
            mGenre.setText("Release date: "+result.getReleaseDate());
            mRating.setText("Rating: " + result.getAdult() + "/10");
        }
    }
}
