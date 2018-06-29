package com.example.weather.converters;

import com.example.weather.dto.WeatherDto;
import com.example.weather.dto.WeatherResponseDto;
import org.joda.time.DateTime;
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
        // v1 will show only future date
        if (date.getTime() < (new DateTime().minusHours(1).toDate().getTime())) {
            return null;
        }
        WeatherResponseDto dto = new WeatherResponseDto();

        dto.city = ((JSONObject) data.get("city")).get("name").toString();

        JSONArray forecast = ((JSONArray) data.get("list"));
        List<WeatherDto> forecastDtos = new ArrayList<>(forecast.length());
        for (int i = 0; i < forecast.length(); i++) {
            WeatherDto someDto = new WeatherDto();
            JSONObject forecastElement = forecast.getJSONObject(i);
            someDto.temp = ((JSONObject) forecastElement.get("main")).get("temp").toString();

            DateFormat format = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
            someDto.date = format.parse(forecastElement.get("dt_txt").toString());
            someDto.data = ((JSONArray) forecastElement.get("weather")).getJSONObject(0).get("main").toString();
            forecastDtos.add(someDto);
        }
        dto.forecast = Collections.min(
                forecastDtos,
                (d1, d2) -> {
                    long diff1 = Math.abs(d1.date.getTime() - date.getTime());
                    long diff2 = Math.abs(d2.date.getTime() - date.getTime());
                    return Long.compare(Math.abs(diff1), Math.abs(diff2));
                }
        );
        return dto;
    }

}
