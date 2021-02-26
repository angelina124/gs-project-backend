package com.investing.forecastbackend.model;
import java.util.*;


// TODO Model the data read from ../resources/data/investment-details.json
public class InvestmentDetail {
    private String category;
    private Double minimum;
    private Double[] data;

    public InvestmentDetail(String cate, Double min, Double[] data) {
        this.category = cate;
        this.minimum = min;
        this.data = data;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public Double[] getData() {
        return data;
    }

    public void setData(Double[] data) {
        this.data = data;
    }
}
