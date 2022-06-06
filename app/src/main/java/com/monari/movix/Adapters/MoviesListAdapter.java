package com.monari.movix.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.monari.movix.R;
import com.monari.movix.models.Result;
import com.monari.movix.models.ResultsTv;
import com.monari.movix.ui.MoviesDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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
//        @BindView(R.id.adultTextview)
//        TextView mAdult;

        private Context mContext;


        public MoviesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindMovies(Result result) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+result. getPosterPath()).into(mMovieImageView);
            mTitleMovie.setText(result.getTitle()) ;;
            mRating.setText(Double.toString(result.getVoteAverage()) + "/10");

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();// retrieve the position of the specific list item clicked
            Intent intent = new Intent(mContext, MoviesDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("movies", Parcels.wrap(mMovies));
            mContext.startActivity(intent);
        }
    }
}
