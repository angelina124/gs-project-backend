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

import java.util.*;
import java.io.FileReader;

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
            JSONObject jsonObject = (JSONObject)investmentObject;
            JSONArray investments = (JSONArray)jsonObject.get("Investments");

            for (int j = 0; j < investments.size(); j++) {

                JSONObject investment = (JSONObject) investments.get(j);

                JSONArray dataArray = (JSONArray)investment.get("data");
                Double[] data = new Double[dataArray.size()];

                for (int i = 0; i < dataArray.size(); i++) {
                    data[i] = Double.parseDouble((String) dataArray.get(i));
                }
                InvestmentDetail investDetail = new InvestmentDetail((String)investment.get("category"),
                        Double.parseDouble((String)investment.get("minimum")), data);

                listInvestmentDetail.add(investDetail);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return listInvestmentDetail;
    }

    public ForecastResponse getInvestmentOptions(final ForecastRequest request) {
        // TODO write algorithm to calculate investment forecast from request configuration
        List<Double> resp = new ArrayList<>();
        ForecastResponse response = new ForecastResponse();
        double totalReturn = 0.0;
        int year = 1;
        double principal = 0.0;

        while (year <= 10) {
            for (String sector : request.getRequest().keySet()) {
                //need to check if percentage meets sector minimum
                double percentage = request.getRequest().get(sector);
                principal = totalReturn * percentage * 0.01;

                if (year == 1) {
                    principal = 0.01 * percentage * 10000;
                }

                if (sector.equals("Energy")) {
                    totalReturn += principal * Math.pow(1 + 0.048341, year);

                } else if (sector.equals("Technology")) {
                    totalReturn += principal * Math.pow(1 + -0.081196, year);

                } else if (sector.equals("Financial Services")) {
                    totalReturn += principal * Math.pow(1 + -0.134428, year);

                } else if (sector.equals("Real Estate")) {
                    totalReturn += principal * Math.pow(1 + -0.033362, year);

                } else if (sector.equals("Pharmaceuticals")) {
                    totalReturn += principal * Math.pow(1 + 0.000000, year);

                } else if (sector.equals("Airline")) {
                    totalReturn += principal * Math.pow(1 + -0.020054, year);

                } else if (sector.equals("Retail")) {
                    totalReturn += principal * Math.pow(1 + 0.091558, year);

                } else if (sector.equals("Gaming")) {
                    totalReturn += principal * Math.pow(1 + -0.016982, year);
                }
            //return = principal(1 + CAGR/1)^year
            }
            year++;
            resp.add(totalReturn);
        }
        response.setResponse(resp);
        return response;
    }
}

