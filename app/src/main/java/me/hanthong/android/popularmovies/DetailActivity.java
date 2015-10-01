package me.hanthong.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Intent intent = getIntent();
        MovieData movie = getDataFromIntent(intent);

        TextView movieName = (TextView) findViewById(R.id.text_movie_name);
        movieName.setText(movie.getMovieName());

        TextView movieReleaseDate = (TextView) findViewById(R.id.movie_release_date);
        movieReleaseDate.setText(movie.getReleaseDate());

        TextView movieVote = (TextView) findViewById(R.id.movie_rate);
        movieVote.setText(String.valueOf(movie.getVote()));

        TextView movieOverView = (TextView) findViewById(R.id.movie_plot);
        movieOverView.setText(movie.getOverView());

        ImageView poster = (ImageView) findViewById(R.id.image_movie_poster);
        Glide.with(this)
                .load(movie.getPosterPath())
                .placeholder(R.drawable.hold)
                .fitCenter()
                .into(poster);

    }

    private MovieData getDataFromIntent(Intent intent) {
        MovieData data = new MovieData();
        if (intent != null && intent.hasExtra("movie_name")) {
            String text = intent.getStringExtra("movie_name");
            data.setMovieName(text);
        }
        if (intent != null && intent.hasExtra("movie_poster")) {
            String text = intent.getStringExtra("movie_poster");
            data.setPosterPath(text);
        }
        if (intent != null && intent.hasExtra("movie_release_date")) {
            String text = intent.getStringExtra("movie_release_date");
            data.setReleaseDate(text);
        }
        if (intent != null && intent.hasExtra("movie_vote")) {
            String text = intent.getStringExtra("movie_vote");
            data.setVote(Double.valueOf(text));
        }
        if (intent != null && intent.hasExtra("movie_overview")) {
            String text = intent.getStringExtra("movie_overview");
            data.setOverView(text);
        }
        return data;
    }


}
