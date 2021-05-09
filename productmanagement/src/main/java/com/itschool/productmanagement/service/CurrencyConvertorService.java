package com.itschool.productmanagement.service;

import org.springframework.stereotype.Component;

@Component
public class CurrencyConvertorService {

    public double convert(double sum, String from, String to){
        double convertedValue = 0;
        if (from.equals("lei") && to.equals("euro")){
            convertedValue = sum /4.8;
        }
        else {
            convertedValue = sum * 4.8;
        }
       return convertedValue;
    }
}
