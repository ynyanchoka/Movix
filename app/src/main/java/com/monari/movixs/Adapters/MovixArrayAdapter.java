package com.monari.movixs.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MovixArrayAdapter extends ArrayAdapter  {

    private Context mContext;    //required for many methods to run,
    private String[] mMovies;
    private String[] mGenres;
    private String[] mRatings;
    private String[] mYears;
    private String[] mOverview;



    public MovixArrayAdapter(Context mContext, int resource) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mMovies = mMovies;
        this.mOverview = mOverview;

    }

    @Override //Override some of ArrayAdapter’s methods and replace them with our own custom versions
    public Object getItem(int position) {
        String movie = mMovies[position];
        String overview = mOverview[position];

        return String.format( "\n" + movie + "\n" +"Genre:"+"\n"+ overview );

    }

    @Override
    public int getCount() {
        return mMovies.length;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = super.getView(position, convertView, parent);
//
//        // Here all your customization on the View
//
//        return view;
//    }
}
