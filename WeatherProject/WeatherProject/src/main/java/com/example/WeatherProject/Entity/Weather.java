package com.example.WeatherProject.Entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Weather {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String cityName;
    private Date startDate;
    private Date endDate;
    private String lat;
    private String lon;
    private String range;


}
