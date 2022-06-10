package com.monari.movixs.Adapters;

import android.content.Intent;
import android.view.View;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monari.movixs.Constants;
import com.monari.movixs.R;
import com.monari.movixs.models.Result;
import com.monari.movixs.ui.MoviesDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    View mView;
    Context mContext;

    public FirebaseMoviesViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindMovies(Result result) {
        ImageView movieImageView = (ImageView) mView.findViewById(R.id.movieImageView);
        TextView titleMovie = (TextView) mView.findViewById(R.id.titleMovie);
        TextView ratingMovies = (TextView) mView.findViewById(R.id.ratingMovies);
        TextView releaseDateTextview = (TextView) mView.findViewById(R.id.releaseDateTextview);


        Picasso.get().load("https://image.tmdb.org/t/p/w500"+result. getPosterPath()).into(movieImageView);
        titleMovie.setText(result.getTitle()) ;
        releaseDateTextview.setText(result.getReleaseDate());
        ratingMovies.setText(Double.toString(result.getVoteAverage()) + "/10");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Result> results = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MOVIES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    results.add(snapshot.getValue(Result.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MoviesDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("results", Parcels.wrap(results));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
