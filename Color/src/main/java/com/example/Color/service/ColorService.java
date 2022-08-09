package com.example.Color.service;

import com.example.Color.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ColorService {
    public String randomColor(int type){
        return  switch (type) {
            case 1->randomColorName();
            case 2->randomHexColor();
            case 3->randomRgbColor();
            default->throw new BadRequestException("Type khong hop le");
        };
    }
    public String randomColorName(){
        String []colorName={"red","blue","pink","yellow","green"};
        return colorName[(int)Math.floor(Math.random() * (colorName.length))];
    }
    public String randomHexColor(){
        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", rand_num);
        return colorCode;
    }
    public String randomRgbColor(){
        int r=(int)Math.floor(Math.random() * 256);
        int g=(int)Math.floor(Math.random() * 256);
        int b=(int)Math.floor(Math.random() * 256);
        return "rgb("+r+","+g+","+b+")";
    }
}
