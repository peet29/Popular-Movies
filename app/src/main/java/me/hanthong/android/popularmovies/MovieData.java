package me.hanthong.android.popularmovies;

import java.util.Comparator;

/**
 * Created by peet29 on 28/9/2558.
 */
public class MovieData implements Comparable<MovieData> {

    private double movieID;
    private double popularity;
    private double vote;
    private String movieName;
    private String posterPath;
    private String overView;
    private String releaseDate;

    public MovieData(double id,double popNum,double voteNum,String name,String imagePath,String overview,String date ) {
        this.movieID = id;
        this.popularity = popNum;
        this.vote = voteNum;
        this.movieName = name;
        this.posterPath = imagePath;
        this.overView = overview;
        this.releaseDate = date;
    }

    public MovieData()
    {

    }

    public static class OrderByVote implements Comparator<MovieData> {

        @Override
        public int compare(MovieData o1, MovieData o2) {
            return o1.vote < o2.vote ? 1 : (o1.vote > o2.vote ? -1 : 0);
        }
    }


    /*
     * Sorting on popularity is natural sorting for Order.
     */
    @Override
    public int compareTo(MovieData o) {
        return this.popularity < o.popularity ? 1 : (this.popularity > o.popularity ? -1 : 0);
    }


    public double getMovieID()
    {
        return movieID;
    }
    public double getPopularity()
    {
        return popularity;
    }
    public double getVote()
    {
        return vote;
    }
    public String getMovieName()
    {
        return movieName;
    }
    public String getPosterPath()
    {
        return posterPath;
    }
    public String getOverView()
    {
        return overView;
    }
    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setMovieID(double movieID) {
        this.movieID = movieID;
    }

    public void setVote(double vote) {
        this.vote = vote;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
