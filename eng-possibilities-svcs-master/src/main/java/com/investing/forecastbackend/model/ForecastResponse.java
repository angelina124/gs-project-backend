package com.investing.forecastbackend.model;

//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;

import java.util.List;
import java.util.Map;

//@Data
//@Getter
//@Setter
// TODO Use Lombok for Getters and Setters (commented above), Optionally you can generate them.
public class ForecastResponse {
    private Map<String, List<Double>> response;

    public Map<String, List<Double>> getResponse() {
        return response;
    }

    public void setResponse(Map<String, List<Double>> response) {
        this.response = response;
    }
}
