package com.example.thewizard.cjuliaol.shutterdroid.api;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by cjuliaol on 09/07/2015.
 */

//
// Add the paths
// Define GET, POST, PUT, DELETE here
 interface ShutterStockService {

    @GET("/v2/images/search")
    public void search(@Query("query") String query, Callback<Response> callback);

    @GET("/v2/images/search")
    public void getRecent(@Query("added_date_start") String query, Callback<Response> callback);

}
