package com.example.thewizard.cjuliaol.shutterdroid.api;

import java.util.List;

/**
 * Created by cjuliaol on 10/07/2015.
 */
 class Response {
    // Not @SerializedName because field matches with JSON
    public List<Image> data;
}
