package org.weibeld.flicks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.weibeld.flicks.api.ApiResponseMovieList;
import org.weibeld.flicks.api.ApiService;
import org.weibeld.flicks.util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static org.weibeld.flicks.api.ApiResponseMovieList.Movie;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    MainActivity mActivity;
    Retrofit mRetrofit;
    ListView mListView;
    ArrayAdapter<Movie> mAdapter;
    List<Movie> mMovies;
    SwipeRefreshLayout mSwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialise member variables
        mActivity = this;
        mRetrofit = Util.setupRetrofit();
        mListView = (ListView) findViewById(R.id.lvMovies);
        mMovies = new ArrayList<>();
        mAdapter = new MovieAdapter(this, (ArrayList<Movie>) mMovies);
        mListView.setAdapter(mAdapter);

        // Set up "pull to refresh"
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        // Setup refresh listener which triggers new data loading
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                mAdapter.clear();
                getNowPlaying();
            }
        });
        // Configure the refreshing colors
        mSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start DetailActiviry and put
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra(getString(R.string.intent_extra_movie), mMovies.get(position));
                startActivity(intent);
            }
        });

        getNowPlaying();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dummy:
                return true;
        }
        return false;
    }

    private void getNowPlaying() {
        ApiService api = mRetrofit.create(ApiService.class);
        Call<ApiResponseMovieList> call = api.apiGetNowPlaying();
        call.enqueue(new Callback<ApiResponseMovieList>() {
            @Override
            public void onResponse(Call<ApiResponseMovieList> call, retrofit2.Response<ApiResponseMovieList> response) {
                int statusCode = response.code();
                ApiResponseMovieList body = response.body();
                List<Movie> movies = body.results;
                for (Movie movie : movies) {
                    Log.v(LOG_TAG, movie.title);
                }
                mAdapter.addAll(movies);
                mSwipeRefresh.setRefreshing(false);  // Remove the spinning referesh item
            }

            @Override
            public void onFailure(Call<ApiResponseMovieList> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public class MovieAdapter extends ArrayAdapter<Movie> {

        private final String LOG_TAG = MovieAdapter.class.getSimpleName();

        public MovieAdapter(Context context, ArrayList<Movie> items) {
            super(context, 0, items);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Movie movie = getItem(position);
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
                viewHolder = createNewViewHolder(convertView);
                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tvTitle.setText(movie.title);
            viewHolder.tvOverview.setText(movie.overview);
            if (Util.isPortrait(mActivity))
                Util.loadImage(mActivity, Util.TYPE_POSTER, ApiService.POSTER_SIZE_W185, movie.posterPath, viewHolder.ivImage);
            else
                Util.loadImage(mActivity, Util.TYPE_BACKDROP, ApiService.BACKDROP_SIZE_W780, movie.backdropPath, viewHolder.ivImage);

            return convertView;
        }

        private ViewHolder createNewViewHolder(View convertView) {
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
            return viewHolder;
        }

        private class ViewHolder {
            TextView tvTitle;
            TextView tvOverview;
            ImageView ivImage;
        }
    }
}
