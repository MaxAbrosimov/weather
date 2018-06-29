package com.example.weather.service.api;

import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public interface WeatherApiService {

    JSONObject getWeatherByCity(String cityName, String metric) throws URISyntaxException, IOException, JSONException, HttpException;

}
