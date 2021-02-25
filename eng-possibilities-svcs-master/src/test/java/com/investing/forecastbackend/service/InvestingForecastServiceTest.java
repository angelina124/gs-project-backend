package com.investing.forecastbackend.service;
import java.util.List;

import org.junit.Test;

import com.investing.forecastbackend.model.InvestmentDetail;
public class InvestingForecastServiceTest {
    //private InvestingForecastService seb;
    @Test
    public void testInvestingForecastServiceTest(){
        InvestingForecastService seb = new InvestingForecastService();
        List<InvestmentDetail> a =  seb.getInvestmentOptions();

        //System.out.println("result " + a.size());
        for(InvestmentDetail det:a) {
            System.out.println(det.getCategory());
        }

    }

}
