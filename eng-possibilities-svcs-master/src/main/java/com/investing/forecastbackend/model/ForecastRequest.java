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

    public Map<String, Double> getRequest() {
        return request;
    }

    public void setRequest(Map<String, Double> request) {
        this.request = request;
    }
}
