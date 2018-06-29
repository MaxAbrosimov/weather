package com.example.weather.dto;

import java.util.Date;

public class WeatherResponseDto {

    public String city;

    public WeatherDto forecast;

    public static class WeatherDto {

        public Date date;

        public String temp;

        public String data;
    }

}
