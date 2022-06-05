package com.monari.movix.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.monari.movix.R;
import com.monari.movix.models.ResultsTv;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowsListAdapter extends RecyclerView.Adapter<TvShowsListAdapter.TvShowViewHolder> {

    private List<ResultsTv>mTvShows;
    private Context mContext;

    public TvShowsListAdapter(Context context, List<ResultsTv> tvShows) {
        mContext = context;
        mTvShows = tvShows;
    }


    @Override
    public TvShowsListAdapter.TvShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list_item, parent, false);
        TvShowsListAdapter.TvShowViewHolder viewHolder = new TvShowsListAdapter.TvShowViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(TvShowsListAdapter.TvShowViewHolder holder, int position) {
        holder.bindTvShows(mTvShows.get(position));



    }

    @Override
    public int getItemCount() {
        return mTvShows.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movieImageView)
        ImageView mMovieImageView;
        @BindView(R.id.titleMovie)
        TextView mTitleMovie;
        @BindView(R.id.ratingMovies)
        TextView mRating;
        @BindView(R.id.releaseDateTextview)
        TextView mReleaseDateTextview;

        private Context mContext;


        public TvShowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindTvShows(ResultsTv result) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+result. getPosterPath()).into(mMovieImageView);
            mTitleMovie.setText(result.getName());
            mRating.setText("Rating: " + result.getVoteAverage().toString() + "/5");
            mReleaseDateTextview.setText("Release date: "+result.getFirstAirDate());
//            mRating.setText("Rating: " + result.getAdult() + "/10");
        }
    }
}
