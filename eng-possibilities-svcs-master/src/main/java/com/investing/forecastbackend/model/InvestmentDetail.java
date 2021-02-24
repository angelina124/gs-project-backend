package com.investing.forecastbackend.model;
import java.util.*;


// TODO Model the data read from ../resources/data/investment-details.json
public class InvestmentDetail {
    String category;
    String minimum;
    String[] data;

    public InvestmentDetail(String cate, String min, String[] data) {
        this.category = cate;
        this.minimum = min;
        this.data = data;
    }
}
