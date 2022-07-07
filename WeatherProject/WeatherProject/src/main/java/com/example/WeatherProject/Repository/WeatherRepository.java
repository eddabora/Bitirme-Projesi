package com.example.WeatherProject.Repository;

import com.example.WeatherProject.Dto.WeatherDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class WeatherRepository implements JpaRepository<WeatherDto, Long> {

}


