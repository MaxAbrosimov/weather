package com.example.weather.service;

import com.example.weather.converters.WeatherConverter;
import com.example.weather.dto.WeatherResponseDto;
import com.example.weather.service.api.WeatherApiService;
import com.example.weather.validators.DateValidator;
import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;

@Repository
public class WeatherService {

    private WeatherApiService weatherApiService;
    private WeatherConverter weatherConverter;

    @Autowired
    public WeatherService(WeatherApiService weatherApiService, WeatherConverter weatherConverter) {
        this.weatherApiService = weatherApiService;
        this.weatherConverter = weatherConverter;
    }

    public WeatherResponseDto getWeatherByCity(String cityName, Date date) throws IOException, JSONException, URISyntaxException, HttpException, ParseException {
        Date onDate = date == null ? new Date() : date;
        DateValidator.validate(onDate);
        // TODO receive "metric" by user IP or etc, not hardcoded
        JSONObject myResponse = weatherApiService.getWeatherByCity(cityName, "metric");
        return weatherConverter.convert(myResponse, onDate);
    }

}
