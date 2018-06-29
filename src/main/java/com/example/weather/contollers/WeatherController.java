package com.example.weather.contollers;

import com.example.weather.dto.WeatherDto;
import com.example.weather.dto.WeatherResponseDto;
import com.example.weather.service.WeatherService;
import org.apache.http.HttpException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @GetMapping(value = "/city/{cityName}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<WeatherResponseDto> greeting(
            @PathVariable String cityName,
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd' 'HH:mm:ss") Date date
    ) throws IOException, JSONException, URISyntaxException, HttpException, ParseException {
        return new ResponseEntity<>(weatherService.getWeatherByCity(cityName, date), OK);
    }

}
