package com.chelex.phonebook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BaliWeatherService {

    private static final String WEATHER_URI =
            "https://api.open-meteo.com/v1/forecast?"
                    + "latitude={latitude}&longitude={longitude}&daily={daily}&current_weather={current_weather}"
                    + "&timezone={timezone}&past_days={past_days}&forecast_days={forecast_days}";

    public static final Float LATITUDE = -8.5098f;
    public static final Float LONGITUDE = 115.2654f;
    public static final Integer DAYS_BEFORE_AND_AFTER = 3;


    private final RestTemplate restTemplate;

    @SuppressWarnings("checkstyle:MagicNumber")
    public void logWeather() {
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("latitude", LATITUDE);
        uriParams.put("longitude", LONGITUDE);
        uriParams.put("daily", "temperature_2m_max,temperature_2m_min,sunrise,sunset");
        uriParams.put("current_weather", true);
        uriParams.put("timezone", "GMT");
        uriParams.put("past_days", DAYS_BEFORE_AND_AFTER);
        uriParams.put("forecast_days", DAYS_BEFORE_AND_AFTER);
        String forObject = restTemplate.getForObject(WEATHER_URI,
                String.class, uriParams);
        log.info(forObject);
    }
}
