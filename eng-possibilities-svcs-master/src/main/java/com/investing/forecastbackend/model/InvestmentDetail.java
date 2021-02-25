package com.investing.forecastbackend.model;
import java.util.*;


// TODO Model the data read from ../resources/data/investment-details.json
public class InvestmentDetail {
    private String category;
    private String minimum;
    private String[] data;

    public InvestmentDetail(String cate, String min, String[] data) {
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

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
