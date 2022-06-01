package com.monari.movix;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBApi {
    @GET("search/movie")
    Call<TMDBSearchMoviesResponse> getRestaurants(
            @Query("title") String title,
            @Query("release_date") String release_date,
            @Query("vote_average") Double vote_average,
            @Query("overview") String overview,
            @Query("poster_path") String poster_path,
            @Query("genre_ids") Integer genre_ids,
            @Query("id") Integer id,
            @Query("adult") boolean adult




    );
}
