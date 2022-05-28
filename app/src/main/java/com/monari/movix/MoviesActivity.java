package com.monari.movix;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActivity extends AppCompatActivity {
    @BindView(R.id.listView) ListView mListView;
    private String[] movies = new String[] {"Red Notice", "How to Get Away with Murder",
            "Game of Thrones", "The Bat man", "Morbius", "Vikings",
            "Dark Winds", "Queen of The South", "Jane the Virgin", "Annabelle",
            "Lord of The Rings", "Grim Reaper", "Identity Thief",
            "Chimpmunks", "Peaky Blinders", "Stranger Things", "The Office"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, movies);
        mListView.setAdapter(adapter);

    }
}