package com.example.thewizard.cjuliaol.shutterdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.thewizard.cjuliaol.shutterdroid.api.Image;
import com.example.thewizard.cjuliaol.shutterdroid.api.ImagesAdapter;
import com.example.thewizard.cjuliaol.shutterdroid.api.ShutterStock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShutterListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private static final String TAG = "ShutterListActivity";
    private List<Image> mImages;
    private ImagesAdapter mAdapter;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shutter_list);

        mImages = new ArrayList<>();

        RecyclerView imagesListView = (RecyclerView) findViewById(R.id.images_list);
        imagesListView.setLayoutManager( new GridLayoutManager(this,3));
        mAdapter = new ImagesAdapter(this,mImages);
        imagesListView.setAdapter(mAdapter);



        ShutterStock.getRecent(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), new Callback<List<Image>>() {
            @Override
            public void success(List<Image> images, Response response) {

                Log.d(TAG,"getRecent");
                mImages.clear();
                mImages.addAll(images);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG,"getRecent Error "+ error.getMessage());
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shutter_list, menu);

        mSearchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
        mSearchView.setOnQueryTextListener(this);

        return true;
    }

    // To implement setOnQueryTextListener (SearchView.OnQueryTextListener)
    @Override
    public boolean onQueryTextSubmit(String query) {

        ShutterStock.search(query, new Callback<List<Image>>() {
            @Override
            public void success(List<Image> images, Response response) {
                mImages.clear();
                mImages.addAll(images);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
              Log.d(TAG,"search error "+error.getMessage());
            }
        });
        return true;
    }
    // To implement setOnQueryTextListener (SearchView.OnQueryTextListener)
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_view) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
