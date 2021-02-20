package com.investing.forecastbackend.model;

//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;

import java.util.Map;


//@Data
//@Getter
//@Setter
// TODO Use Lombok (commented above) for Getters and Setters, Optionally you can generate them.
public class ForecastRequest {
    private Map<String, Double> request;
}
