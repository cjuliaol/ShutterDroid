package com.example.thewizard.cjuliaol.shutterdroid.api;

import android.util.Base64;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by cjuliaol on 09/07/2015.
 */
// This gonna act as a singleton
public class ShutterStock {

    private static final RestAdapter ADAPTER = new RestAdapter.Builder()
            .setEndpoint("https://api.shutterstock.com")
            .setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    // Add credetials here
                    //client id:client secret
                    String authInfo="3fbdca331f73c946561b:a2fbd674d8b3a37275ab1b4432b9ea37f659aed6";
                    // Basic formating
                    String auth = "Basic "+ Base64.encodeToString(authInfo.getBytes(),Base64.NO_WRAP);
                    request.addHeader("Authorization", auth);
                }
            }).build();
    private static final ShutterStockService SERVICE = ADAPTER.create(ShutterStockService.class);

    public static void search(String query, Callback<List<Image>> callback){
        SERVICE.search(query, new ImageCallBack(callback));
    }

    public static void getRecent(String date, Callback<List<Image>> callback) {
        SERVICE.getRecent(date, new ImageCallBack(callback));
    }

    private static class ImageCallBack implements Callback<Response> {

        Callback<List<Image>> cb;

        ImageCallBack(Callback<List<Image>> cb) {
            this.cb = cb;
        }

        @Override
        public void success(Response response, retrofit.client.Response response2) {
               cb.success(response.data,response2);
        }

        @Override
        public void failure(RetrofitError error) {
             cb.failure(error);
        }
    }

}
