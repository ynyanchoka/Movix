package com.monari.movix;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import Adapters.MovixArrayAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActivity extends AppCompatActivity {
    @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.popularMovies)
    TextView mPopularMovies;


    private String[] movies = new String[] {"The Matrix", "Star Wars-The Empire Strike Back",
            "The Lord of the Rings: The Two Towers", "Inception", "Fight Club", "Forrest Gump",
            "The Good, the Bad and the Ugly", "The Lord of the Rings: The Fellowship of the Ring", "Pulp Fiction", "The Lord of the Rings: The Return of the King",
            "Schindler's List", "12 Angry Men", "The Godfather: Part II",
            "The Dark Knight", "The Godfather", "The Shawshank Redemption"};

    private String[] genres = new String[] {"Sci-Fi","Sci-Fi", "Sci-Fi","Sci-Fi,Adventure", "Drama", "Romance,Drama", "Western", "Adventure,Drama",
    "Crime,Drama", "Action,Drama", "Drama,History", "Crime,Drama", "Crime, Drama", "Action,Drama,Crime", "Crime,Drama", "Drama"};
    private String[] ratings = new String[] {"8.7","8.7","8.7","8.7","8.7","8.8", "8.8", "8.8", "8.9", "9.0", "9.0", "9.0", "9.0", "9.0", "9.2", "9.2"};
    private String [] years = new String []{"Year:1999","Year:1980","2002","2010","1999", "1994", "1966", "2001", "1994","2003", "1993", "1957", "1974", "2008", "1972", "1994"};
    private String[] overviews = new String []{
            "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
            "After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued across the galaxy by Darth Vader and bounty hunter Boba Fett.",
            "While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron's new ally, Saruman, and his hordes of Isengard.",
            "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
            "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.",
            "The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold from the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.",
            "A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery." ,
            "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
            "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
            "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
            "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.",
            "The jury in a New York City murder trial is frustrated by a single member whose skeptical caution forces them to more carefully consider the evidence before jumping to a hasty verdict.",
            "The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.",
            "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
            "The aging patriarch of an organized crime dynasty in postwar New York City transfers control of his clandestine empire to his reluctant youngest son.",
            "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        MovixArrayAdapter adapter = new MovixArrayAdapter(this, android.R.layout.simple_list_item_1, movies, genres,ratings,years,overviews);
        mListView.setAdapter(adapter);

//
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, movies);
//        mListView.setAdapter(adapter);

    }
}