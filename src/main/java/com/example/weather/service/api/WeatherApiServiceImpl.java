package com.example.weather.service.api;

import com.example.weather.exceptions.DataNotFoundException;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

@Service
public class WeatherApiServiceImpl implements WeatherApiService{

    @Value("${weather.api.host}")
    String host;

    @Value("${weather.api.forecastPath}")
    String forecastPath;

    @Value("${weather.api.key}")
    String weatherApiKey;

    @Override
    public JSONObject getWeatherByCity(String cityName, String metric) throws IOException, JSONException, URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder()
                .setScheme("https");
        uriBuilder
                .setHost(host)
                .setPath(forecastPath)
                .addParameter("APPID", weatherApiKey)
                .addParameter("units", metric)
                .addParameter("cnt", "60")
                .addParameter("q", cityName);
        HttpURLConnection con = (HttpURLConnection) uriBuilder.build().toURL().openConnection();
        con.setRequestMethod("GET");
        return convertResponse(con);

    }

    private JSONObject convertResponse(HttpURLConnection con) throws IOException, JSONException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return new JSONObject(response.toString());
        } catch (FileNotFoundException e) {
            throw new DataNotFoundException("Nothing found, please check data correctness");
        }
    }


}
