package com.example.day8pokemon.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {
    
    // @Autowired
    // MyRepo myrepo;

    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";

    private String apiKey;

    @PostConstruct
    public void init() {
        apiKey = System.getenv("OPEN_WEATHER_MAP");
    }

    public void getWeather(String city) {
       UriComponentsBuilder.fromUriString(WEATHER_URL)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .toUriString();
    }
    
}
