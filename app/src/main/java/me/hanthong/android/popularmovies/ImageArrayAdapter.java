package me.hanthong.android.popularmovies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by peet29 on 29/9/2558.
 */
public class ImageArrayAdapter extends ArrayAdapter<MovieData> {
    private static final String LOG_TAG = ImageArrayAdapter.class.getSimpleName();


    private static class ViewHolder {
        ImageView moviePoster;
    }
    public ImageArrayAdapter(Activity context,List<MovieData> movieData) {

        super(context,0,movieData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieData movie = getItem(position);
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_image, parent, false);
            viewHolder.moviePoster = (ImageView) convertView.findViewById(R.id.list_image);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(getContext())
                .load(movie.getPosterPath())
                .placeholder(R.drawable.hold)
                .into(viewHolder.moviePoster);

        return convertView;
    }
}
