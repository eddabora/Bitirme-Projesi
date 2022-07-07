package com.example.WeatherProject.Service;

import com.example.WeatherProject.Dto.WeatherDto;
import org.springframework.http.ResponseEntity;

public interface IWeatherService {
    String findLatLon(String countryName, WeatherDto weatherDto);
    ResponseEntity<String> findAirPollution(String lat, String log, WeatherDto weatherDto);
    ResponseEntity<WeatherDto> deleteWeather(Long id);


}
