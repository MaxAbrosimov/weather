package com.example.weather.exceptions;

import java.io.FileNotFoundException;

public class DataNotFoundException extends FileNotFoundException {

    public DataNotFoundException(String s) {
        super(s);
    }
}
