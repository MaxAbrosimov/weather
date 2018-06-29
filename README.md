### Simple JAVA forecast API by city and date
using free openweathermap api

## Requirements
* Java 8
* Maven 3

**Step 1**: generate key [here](https://openweathermap.org/) 

**Step 2**: override weather.api.key in [application.properties](src/main/resources/application.properties)

**Step 3**: mvn clean install

**Step 4**: run [WeatherApplication](src/main/java/com/example/weather/WeatherApplication.java)

**Step 5** enjoy :)

## Test example:
http://localhost:{port}/weather/kiev?date={yyyy-MM-dd--HH:mm:ss}


