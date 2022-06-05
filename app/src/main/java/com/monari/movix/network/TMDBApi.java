package com.monari.movix.network;



import com.monari.movix.models.TMDBSearchMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBApi {

    @GET("search/movie")
    Call<TMDBSearchMoviesResponse> getMovies(
            @Query("api_key") String api_key,
            @Query("query") String query,
            @Query("page") Integer page
    );

    @GET("search/tv")
    Call<TMDBSearchMoviesResponse> getTvShows(
            @Query("api_key") String api_key,
            @Query("query") String query,
            @Query("page") Integer page
    );

    @GET("movie/{movie_id}")
    Call<TMDBSearchMoviesResponse> getMoviesDetail(
            @Query("api_key") String api_key,
            @Query("query") String query
    );



}
