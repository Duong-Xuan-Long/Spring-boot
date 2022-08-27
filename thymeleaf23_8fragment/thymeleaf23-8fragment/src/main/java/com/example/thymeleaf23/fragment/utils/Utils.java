package com.example.thymeleaf23.fragment.utils;

import java.util.Random;

public class Utils {
    public static int randomNumber() {
        Random rd=new Random();
        return rd.nextInt(3);
    }
}
