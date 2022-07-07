package com.example.WeatherProject.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class WeatherDto {

    private Long id;
    private String cityName;
    private Date startDate;
    private Date endDate;
    private Double lat;
    private Double lon;
    private String range;
}
