package com.example.WeatherProject.Controller;

import com.example.WeatherProject.Dto.WeatherDto;
import com.example.WeatherProject.Service.WeatherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor

public class WeatherController {
    String error;
    boolean isValid;
    private String weather;

    private final WeatherServiceImpl weatherService;


    @GetMapping("/{cityName}")// latitude and longitude
    public @ResponseBody Object findLatLon(@PathVariable("cityName") final String countryName, @RequestBody WeatherDto weatherDto) {
        String response= weatherService.findLatLon(countryName,weatherDto);
        return response;
    }

    @PostMapping("/cities") //Save Cities
    public ResponseEntity<?> saveCountry(@RequestBody WeatherDto weatherDto) throws Exception {
       weatherService.save(weatherDto);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/lon={lon}&lat={lat}")// Air pollution
    public  String  findAirPollution(@PathVariable("lon") final String lon, @PathVariable("lat") final String lat,@RequestBody WeatherDto weatherDto) {
        String pollution= String.valueOf(weatherService.findAirPollution(lon,lat,weatherDto));
        return pollution;
    }




}
