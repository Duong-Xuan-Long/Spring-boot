package com.example.Bmi.bmiService;

import com.example.Bmi.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class BmiService {
    public double calculateBmi(double height, double weight) {
        if(height<=0||weight<=0){
            throw new BadRequestException("Khong thoa man");
        }
        double bmi=(weight)/(height*height);
        return bmi;
    }

}
