
package com.monari.movixs.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class TMDBSearchMoviesResponse {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private ArrayList<Result> results = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TMDBSearchMoviesResponse() {
    }

    /**
     * 
     * @param totalResults
     * @param totalPages
     * @param page
     * @param results
     */
    public TMDBSearchMoviesResponse(Integer page, ArrayList<Result> results, Integer totalPages, Integer totalResults) {
        super();
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

}
