package me.hanthong.android.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private String apiKey;
    private String posterURL;
    private JSONObject movieJSON;
    private ArrayList<MovieData> movieList;
    private ImageArrayAdapter imageAdapter;

    public MainActivityFragment() {
        movieList = new ArrayList<>();
    }


    @Override
    public void onStart() {
        super.onStart();
        apiKey = getResources().getString(R.string.api_key);
        posterURL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + apiKey;
        if (movieJSON == null) {
            DownloadJson();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        int sortMode = getRateSetting();
        sortMovie(sortMode);
        Log.d("Setting", "Sort");
        imageAdapter.notifyDataSetChanged();
        Log.d("Grid","redraw Grid");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        imageAdapter = new ImageArrayAdapter(getActivity(), movieList);
        GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        gridview.setAdapter(imageAdapter);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra("MovieData", movieList.get(position));
                startActivity(intent);
            }
        });

        return rootView;
    }

    private int getRateSetting() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortMode = prefs.getString(getString(R.string.pref_sorting_key), getString(R.string.pref_sorting_default));
        return Integer.valueOf(sortMode);
    }

    private void sortMovie(int sort)
    {
        // popularity
        if(sort == 0)
        {
            Collections.sort(movieList);

        }else {
         //vote_average
            Collections.sort(movieList,new MovieData.OrderByVote());
        }

    }

    /**
     * Download Json from internet use Volley
     */
    private void DownloadJson() {
        final RequestQueue mRequestQueue;

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = Volley.newRequestQueue(getContext());


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, posterURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        movieJSON = response;
                        Log.d("JSON", movieJSON.toString());
                        Log.d("JSON", "Download complete!");
                        mRequestQueue.stop();

                        try {
                            FillJsonData();
                        } catch (JSONException | NullPointerException e) {
                            Log.d("JSON", "Fail to load JSON");
                        } finally {
                            Log.d("JSON", "Done fill");
                        }
                        sortMovie(getRateSetting());
                        imageAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        mRequestQueue.stop();


                    }
                });

        mRequestQueue.add(jsObjRequest);
    }


    /**
     * Fill json data to movieList so it don't need json object anymore
     *
     * @throws JSONException
     * @throws NullPointerException
     */
    private void FillJsonData() throws JSONException, NullPointerException {
        int jsonSize = movieJSON.getJSONArray("results").length();
        for (int i = 0; i < jsonSize; i++) {
            double id = GetJsonData.getNumber(i, movieJSON, "id");
            double popularity = GetJsonData.getNumber(i, movieJSON, "popularity");
            double vote = GetJsonData.getNumber(i, movieJSON, "vote_average");
            String name = GetJsonData.getString(i, movieJSON, "original_title");
            String posterPath = GetJsonData.getPicURL(i, movieJSON);
            String overView = GetJsonData.getString(i, movieJSON, "overview");
            String releaseDate = GetJsonData.getString(i, movieJSON, "release_date");
            MovieData movie = new MovieData(id, popularity, vote, name, posterPath, overView, releaseDate);
            movieList.add(movie);
        }
    }

}
