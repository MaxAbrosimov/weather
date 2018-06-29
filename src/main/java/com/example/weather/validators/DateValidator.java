package com.example.weather.validators;

import com.example.weather.exceptions.IllegalDateException;
import org.joda.time.DateTime;

import java.util.Date;

public class DateValidator {

    /**
     * https://openweathermap.org api allows only current weather + forecast for next 5 days,
     * past forecasts aren`t free :)
     * @param date - selected date
     */
    public static void validate(Date date) {

        if (date.getTime() < (new DateTime().minusHours(1).toDate().getTime())) {
            throw new IllegalDateException("Selected date is gone.");
        }

        if (date.getTime() > (new DateTime().plusDays(5).toDate().getTime())) {
            throw new IllegalDateException("Forecast is not ready yet.");
        }
    }

}
