package me.hanthong.android.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Bind(R.id.text_movie_name)
    TextView movieName;
    @Bind(R.id.movie_release_date)
    TextView movieReleaseDate;
    @Bind(R.id.movie_rate)
    TextView movieVote;
    @Bind(R.id.movie_plot)
    TextView movieOverView;
    @Bind(R.id.image_movie_poster)
    ImageView poster;

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
        ButterKnife.bind(this);


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
