package MovixArrayAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class MovixArrayAdapter extends ArrayAdapter  {

    private Context mContext;    //required for many methods to run,
    private String[] mMovies;
    private String[] mGenres;
    private String[] mRatings;
    private String[] mYears;
    private String[] mOverviews;



    public MovixArrayAdapter( Context mContext, int resource, String[] mMovies, String[] mGenres, String[] mRatings, String[] mYears, String[] mOverviews) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mMovies = mMovies;
        this.mGenres = mGenres;
        this.mRatings = mRatings;
        this.mYears = mYears;
        this.mOverviews = mOverviews;

    }

    @Override //Override some of ArrayAdapter’s methods and replace them with our own custom versions
    public Object getItem(int position) {
        String movie = mMovies[position];
        String genre = mGenres[position];
        String rating = mRatings[position];
        String year = mYears[position];
        String overview = mOverviews[position];

        return String.format( movie + "\n" +"Genre: "+genre + "\n"+ "Rating:"+rating+ "\n"+ "Year:"+ year+"\n"+ overview);//\n  create a new line
//        "Name: " + name + "\n" +"User Name: " + userName+"\n" +"Email: " + userEmail

    }

    @Override
    public int getCount() {
        return mMovies.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        // Here all your customization on the View

        return view;
    }
}
