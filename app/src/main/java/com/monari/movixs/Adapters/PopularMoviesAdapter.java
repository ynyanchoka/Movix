package com.monari.movixs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.monari.movixs.R;
import com.monari.movixs.models.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder> {
    private List<Result> mMovies;
    private Context mContext;

    public PopularMoviesAdapter(Context context, List<Result> movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public PopularMoviesAdapter.PopularMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_list_item, parent, false);
        PopularMoviesViewHolder viewHolder = new PopularMoviesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PopularMoviesAdapter.PopularMoviesViewHolder holder, int position) {
        holder.bindPopularMovies(mMovies.get(position));

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class PopularMoviesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.popularImageView)
        ImageView mPopularImageView;
//        @BindView(R.id.titleMoviePopular)
//        TextView mTitleMoviePopular;
//        @BindView(R.id.ratingMoviesPopular) TextView mRatingMoviesPopular;
//        @BindView(R.id.releaseDatePopular) TextView mReleaseDatePopular;

        private Context mContext;

        public PopularMoviesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindPopularMovies(Result result) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+result. getPosterPath()).into(mPopularImageView);
//            mTitleMoviePopular.setText(result.getTitle()) ;
//            mReleaseDatePopular.setText(result.getReleaseDate());
//            mRatingMoviesPopular.setText(Double.toString(result.getVoteAverage()) + "/10");

        }

    }
}
