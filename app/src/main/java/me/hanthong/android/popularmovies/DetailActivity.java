package me.hanthong.android.popularmovies;

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
        Bundle data = getIntent().getExtras();
        MovieData movie = data.getParcelable("MovieData");

        TextView movieName = (TextView) findViewById(R.id.text_movie_name);
        TextView movieReleaseDate = (TextView) findViewById(R.id.movie_release_date);
        TextView movieVote = (TextView) findViewById(R.id.movie_rate);
        TextView movieOverView = (TextView) findViewById(R.id.movie_plot);
        ImageView poster = (ImageView) findViewById(R.id.image_movie_poster);

        if (movie != null) {
            movieName.setText(movie.getMovieName());
            movieReleaseDate.setText(movie.getReleaseDate());
            movieVote.setText(String.valueOf(movie.getVote()));
            movieOverView.setText(movie.getOverView());
            Glide.with(this)
                    .load(movie.getPosterPath())
                    .placeholder(R.drawable.hold)
                    .fitCenter()
                    .into(poster);
        }
    }

}
