package com.chelex.phonebook.domain.dto;

import lombok.Data;

@Data
public class WeatherDTO {
    private double latitude;
    private double longitude;
    private double generationtimeMs;
    private int utcOffsetSeconds;
    private String timezone;
    private String timezoneAbbreviation;
    private double elevation;
    private CurrentWeather currentWeather;
    private DailyUnits dailyUnits;
    private Daily daily;

    @Data
    public static class CurrentWeather {
        private double temperature;
        private double windspeed;
        private double winddirection;
        private int weathercode;
        private int isDay;
        private String time;
    }

    @Data
    public static class DailyUnits {
        private String time;
        private String temperature2mMax;
        private String temperature2mMin;
        private String sunrise;
        private String sunset;
    }

    @Data
    public static class Daily {
        private String[] time;
        private double[] temperature2mMax;
        private double[] temperature2mMin;
        private String[] sunrise;
        private String[] sunset;
    }
}
