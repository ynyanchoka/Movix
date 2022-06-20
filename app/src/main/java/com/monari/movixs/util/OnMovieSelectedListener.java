package com.monari.movixs.util;


import com.monari.movixs.models.Result;

import java.util.ArrayList;

public interface OnMovieSelectedListener {
    public void onMovieSelected(Integer position, ArrayList<Result> results);
}
