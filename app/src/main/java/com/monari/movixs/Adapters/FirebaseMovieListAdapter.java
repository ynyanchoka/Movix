package com.monari.movixs.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.monari.movixs.Constants;
import com.monari.movixs.R;
import com.monari.movixs.fragments.MoviesDetailFragment;
import com.monari.movixs.models.Result;
import com.monari.movixs.ui.MoviesDetailActivity;
import com.monari.movixs.util.ItemTouchHelperAdapter;
import com.monari.movixs.util.OnStartDragListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseMovieListAdapter extends FirebaseRecyclerAdapter<Result, FirebaseMoviesViewHolder> implements ItemTouchHelperAdapter {

    private Query mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Result> mMovies = new ArrayList<>();
    private int mOrientation;



    public FirebaseMovieListAdapter(FirebaseRecyclerOptions<Result> options,
                                    Query ref,
                                    OnStartDragListener onStartDragListener,
                                    Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mMovies.add(dataSnapshot.getValue(Result.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot,@Nullable  String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot,@Nullable  String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onBindViewHolder(@NonNull final FirebaseMoviesViewHolder firebaseMoviesViewHolder, int position, @NonNull Result result) {
        firebaseMoviesViewHolder.bindMovies(result);

        mOrientation = firebaseMoviesViewHolder.itemView.getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createDetailFragment(0);
        }

        firebaseMoviesViewHolder.mMovieImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(firebaseMoviesViewHolder);
                }
                return false;
            }
        });

        firebaseMoviesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int itemPosition = firebaseMoviesViewHolder.getAdapterPosition();
                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                    createDetailFragment(itemPosition);
                } else {
                    Intent intent = new Intent(mContext, MoviesDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                    intent.putExtra(Constants.EXTRA_KEY_MOVIES, Parcels.wrap(mMovies));
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @NonNull
    @Override
    public FirebaseMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item_drag, parent, false);
        return new FirebaseMoviesViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mMovies, fromPosition, toPosition);//update the order of our mRestaurants ArrayList
        notifyItemMoved(fromPosition, toPosition);
        setIndexInFirebase();
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mMovies.remove(position);
        getRef(position).removeValue();

    }

    private void setIndexInFirebase() {
        for (Result movie : mMovies) {
            int index = mMovies.indexOf(movie);
            DatabaseReference ref = getRef(index);
            movie.setIndex(Integer.toString(index));
            ref.setValue(movie);
        }
    }

    @Override
    public void stopListening() {
        super.stopListening();
        mRef.removeEventListener(mChildEventListener);
    }

    private void createDetailFragment(int position) {
        MoviesDetailFragment detailFragment = MoviesDetailFragment.newInstance(mMovies, position);
        // Gathers necessary components to replace the FrameLayout in the layout with the RestaurantDetailFragment:
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        //  Replaces the FrameLayout with the RestaurantDetailFragment:
        ft.replace(R.id.restaurantDetailContainer, detailFragment);
        // Commits these changes:
        ft.commit();
    }
}
