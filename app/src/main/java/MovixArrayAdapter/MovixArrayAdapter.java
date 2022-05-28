package MovixArrayAdapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class MovixArrayAdapter extends ArrayAdapter  {

    private Context mContext;    //required for many methods to run,
    private String[] mMovies;
    private String[] mGenres;
    private String[] mYears;
    private String[] mOverviews;
    private String[] mRatings;

    public MovixArrayAdapter( Context mContext, int resource, String[] mMovies, String[] mGenres, String[] mYears, String[] mOverviews, String[] mRatings) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mMovies = mMovies;
        this.mGenres = mGenres;
        this.mYears = mYears;
        this.mOverviews = mOverviews;
        this.mRatings = mRatings;
    }

    @Override //Override some of ArrayAdapter’s methods and replace them with our own custom versions
    public Object getItem(int position) {
        String movie = mMovies[position];
        String genre = mGenres[position];
        String year = mYears[position];
        String overview = mOverviews[position];
        String rating = mRatings[position];
        return String.format("%s \nGenre: %s", movie, overview);//\n  create a new line

    }

    @Override
    public int getCount() {
        return mMovies.length;
    }
}
