package com.example.weather.converters;

import com.example.weather.dto.WeatherResponseDto;
import com.example.weather.dto.WeatherResponseDto.WeatherDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Component
public class WeatherConverter {

    public WeatherResponseDto convert(JSONObject data, Date date) throws JSONException, ParseException {

        WeatherResponseDto dto = new WeatherResponseDto();
        dto.city = ((JSONObject) data.get("city")).get("name").toString();
        dto.forecast = Collections.min(
                convertForecasts(((JSONArray) data.get("list"))),
                (d1, d2) -> {
                    long diff1 = Math.abs(d1.date.getTime() - date.getTime());
                    long diff2 = Math.abs(d2.date.getTime() - date.getTime());
                    return Long.compare(Math.abs(diff1), Math.abs(diff2));
                }
        );

        return dto;
    }

    private List<WeatherDto> convertForecasts(JSONArray forecast) throws JSONException, ParseException {
        List<WeatherDto> forecastDtos = new ArrayList<>(forecast.length());
        for (int i = 0; i < forecast.length(); i++) {
            WeatherDto dto = new WeatherDto();
            JSONObject forecastElement = forecast.getJSONObject(i);
            dto.temp = ((JSONObject) forecastElement.get("main")).get("temp").toString();

            DateFormat format = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
            dto.date = format.parse(forecastElement.get("dt_txt").toString());
            dto.data = ((JSONArray) forecastElement.get("weather")).getJSONObject(0).get("main").toString();
            forecastDtos.add(dto);
        }
        return forecastDtos;
    }

}
