package com.investing.forecastbackend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.investing.forecastbackend.model.ForecastRequest;

import org.json.simple.*;
import org.json.simple.parser.*;


import java.io.File;

import com.investing.forecastbackend.model.ForecastResponse;
import com.investing.forecastbackend.model.InvestmentDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvestingForecastService {

    public List<InvestmentDetail> getInvestmentOptions() {
        // TODO read investment options from investment-details.json
        JSONParser parser = new JSONParser();

        //Object jObj = parser.parse(new FileReader("/Users/sheonmwapinza/OneDrive - PennO365/JAVAWORKSPACE/GS–Project/eng-possibilities-svcs-master/src/main/resources/data/investment-details.json"));
        ///Users/ishapalakurthy/Downloads/GSOfficial/gs-project-backend/eng-possibilities-svcs-master/src/main/resources/data
        List<InvestmentDetail> listInvestmentDetail = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File resource = new ClassPathResource("data/investment-details.json").getFile();
            Object investmentObject = parser.parse(new FileReader(resource));
            JSONObject jsonObject = (JSONObject) investmentObject;
            JSONArray investments = (JSONArray) jsonObject.get("Investments");

            for (int j = 0; j < investments.size(); j++) {

                JSONObject investment = (JSONObject) investments.get(j);

                JSONArray dataArray = (JSONArray) investment.get("data");
                Double[] data = new Double[dataArray.size()];

                for (int i = 0; i < dataArray.size(); i++) {
                    data[i] = Double.parseDouble((String) dataArray.get(i));
                }
                InvestmentDetail investDetail = new InvestmentDetail((String) investment.get("category"),
                        Double.parseDouble((String) investment.get("minimum")), data);

                listInvestmentDetail.add(investDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInvestmentDetail;
    }

    //not sure how to handle all the exceptions in frontend
    public ForecastResponse getInvestmentOptions(final ForecastRequest request) {
        // TODO write algorithm to calculate investment forecast from request configuration
        Map<String, Double> minimumsMap = makeMinList();
        Map<String, Double> cagRates = calculateCAGR();
        List<Double> resp = new ArrayList<>();
        ForecastResponse response = new ForecastResponse();
        double totalReturn = 0.0;
        int year = 1;
        double principal = 0.0;
        double totalPercent = 0.0;

        try {
            for (String sector : request.getRequest().keySet()) {
                //checks if percentage invested meets sector minimum
                double percentage = request.getRequest().get(sector);
                double minPercentInvest = minimumsMap.get(sector);
                if (percentage < minPercentInvest) {
                    throw new Exception("Sector minimum is not attained. " +
                            "Must invest at least " + minPercentInvest + " percent into " + sector);
                }
                totalPercent += request.getRequest().get(sector);
            }
            if (totalPercent > 100.0) {
                throw new Exception("Total invested percentage may not exceed 100%");
            }

            while (year <= 10) {
                for (String sector : request.getRequest().keySet()) {
                    double percentage = request.getRequest().get(sector);
                    principal = totalReturn * percentage * 0.01;
                    if (year == 1) {
                        principal = 0.01 * percentage * 10000;
                    }

                    double cagr = cagRates.get(sector);
                    totalReturn += compound(principal, cagr, year);
                    //return = principal(1 + CAGR/1)^year
                }
                year++;
                resp.add(totalReturn);
            }
            response.setResponse(resp);
            return response;
        } catch(Exception e) {
            e.printStackTrace(); //need to do something else here? do we need to place try catch differently?
        }
        return response;
    }

    private Map<String, Double> calculateCAGR() {
        Map<String, Double> cagRates = new HashMap<>();
        List<InvestmentDetail> investmentDetailList = getInvestmentOptions();
        for (InvestmentDetail detail : investmentDetailList) {
            double cagr = Math.pow(((detail.getData()[detail.getData().length - 1]) / detail.getData()[0]), (1/9.0)) - 1;
            cagRates.put(detail.getCategory(), cagr);
        }
        return cagRates;
    }

    private Map<String,Double> makeMinList() {
        Map<String, Double> minimums = new HashMap<>();
        List<InvestmentDetail> investmentDetailList = getInvestmentOptions();
        for (InvestmentDetail detail : investmentDetailList) {
            minimums.put(detail.getCategory(), detail.getMinimum());
        }
        return minimums;
    }

    private double compound(double principal, double cagr, int year) {
        return principal * Math.pow(1.0 + cagr, year);
    }
}

