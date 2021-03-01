package com.investing.forecastbackend.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.investing.forecastbackend.model.ForecastRequest;
import com.investing.forecastbackend.model.ForecastResponse;
import org.junit.Test;

import com.investing.forecastbackend.model.InvestmentDetail;

public class InvestingForecastServiceTest {
    //private InvestingForecastService seb;
    @Test
    public void testInvestingForecastServiceTest() {
        InvestingForecastService seb = new InvestingForecastService();
        List<InvestmentDetail> a = seb.getInvestmentOptions();

        //System.out.println("result " + a.size());
        for (InvestmentDetail det : a) {
            //System.out.println(det.getCategory());
            //System.out.println(det.getMinimum());
            for (Double d : det.getData()) {
                System.out.println(d);
            }
        }
    }

    @Test
    public void testInvestingAlgorithmTestNormal() {
        InvestingForecastService seb = new InvestingForecastService();
        Map<String, Double> req = new HashMap<>();
        req.put("Energy", 50.0);
        req.put("Pharmaceuticals", 20.0);
        ForecastRequest fRequest = new ForecastRequest();
        fRequest.setRequest(req);
        ForecastResponse fResponse = seb.getInvestmentOptions(fRequest);

        for (Double d : fResponse.getResponse()) {
            System.out.println(d);
        }
    }

    @Test
    public void testInvestingAlgorithmTestPercentageMin() {
        InvestingForecastService seb = new InvestingForecastService();
        Map<String, Double> req = new HashMap<>();
        req.put("Energy", 0.0);
        req.put("Pharmaceuticals", 20.0);
        ForecastRequest fRequest = new ForecastRequest();
        fRequest.setRequest(req);
        ForecastResponse fResponse = seb.getInvestmentOptions(fRequest);

        for (Double d : fResponse.getResponse()) {
            System.out.println(d);
        }
    }

    @Test
    public void testInvestingAlgorithmTestPercentageExceed() {
        InvestingForecastService seb = new InvestingForecastService();
        Map<String, Double> req = new HashMap<>();
        req.put("Energy", 50.0);
        req.put("Pharmaceuticals", 100.0);
        ForecastRequest fRequest = new ForecastRequest();
        fRequest.setRequest(req);
        ForecastResponse fResponse = seb.getInvestmentOptions(fRequest);

        for (Double d : fResponse.getResponse()) {
            System.out.println(d);
        }
    }

    @Test
    public void testCalculateCAGRTest() {
        InvestingForecastService seb = new InvestingForecastService();
        //Map<String, Double> cagr = seb.calculateCAGR();
        //double x = cagr.get("Energy");
        //System.out.println(x);
    }
}
